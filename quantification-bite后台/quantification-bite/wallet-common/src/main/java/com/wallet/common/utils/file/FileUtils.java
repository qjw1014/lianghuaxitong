package com.wallet.common.utils.file;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.wallet.common.utils.StringUtils;

/**
 * 文件处理工具类
 * 
 * @author wallet
 */
public class FileUtils extends org.apache.commons.io.FileUtils
{
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";
    
    /**
     * 输出指定文件的byte数组
     * 
     * @param filePath 文件路径
     * @param os 输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os)
        throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 删除文件
     * 
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
    /**
     * 文件名称验证
     * 
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename)
    {
        return filename.matches(FILENAME_PATTERN);
    }
    
    /**
     * 检查文件是否可下载
     * 
     * @param resource 需要下载的文件
     * @return true 正常 false 非法
     */
    public static boolean checkAllowDownload(String resource)
    {
        // 禁止目录上跳级别
        if (StringUtils.contains(resource, ".."))
        {
            return false;
        }
        
        // 检查允许下载的文件规则
        if (ArrayUtils.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, FileTypeUtils.getFileType(resource)))
        {
            return true;
        }
        
        // 不在允许下载的文件规则
        return false;
    }
    
    /**
     * 下载文件名重新编码
     * 
     * @param request 请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
        throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
    
    /**
     * 下载文件名重新编码
     *
     * @param response 响应对象
     * @param realFileName 真实文件名
     * @return
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName)
        throws UnsupportedEncodingException
    {
        String percentEncodedFileName = percentEncode(realFileName);
        
        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=").append(percentEncodedFileName).append(";").append("filename*=").append("utf-8''").append(percentEncodedFileName);
        
        response.setHeader("Content-disposition", contentDispositionValue.toString());
    }
    
    /**
     * 百分号编码工具方法
     *
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串
     */
    public static String percentEncode(String s)
        throws UnsupportedEncodingException
    {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }
    
    public static MultipartFile fileToMultipartFile(File file)
    {
        FileItem fileItem = createFileItem(file);
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        return multipartFile;
    }
    
    private static FileItem createFileItem(File file)
    {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem("textField", "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try
        {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1)
            {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return item;
    }
    
    /**
     * 创建一个新文件，如果存在则报错
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static File createFile(String filePath, String fileName)
        throws RuntimeException
    {
        File file = null;
        if (filePath == null)
        {
            return file;
        }
        else
        {
            filePath = filePath + File.separator + fileName;
        }
        return createFile(filePath);
    }
    
    /**
     * 创建一个新文件(含路径)，如果存在则报错
     *
     * @param fileName 含有路径的文件名
     * @return
     */
    public static File createFile(String fileName)
        throws RuntimeException
    {
        File f = new File(fileName);
        if (f.exists())
        {
            return f;
        }
        else
        {
            try
            {
                File fileFolder = f.getParentFile();
                if (!fileFolder.exists())
                {
                    fileFolder.mkdirs();
                }
                f.createNewFile();
            }
            catch (IOException ie)
            {
                // System.out.println("文件" + fileName + "创建失败：" + ie.getMessage());
                throw new RuntimeException("FILE_CREATE_ERROR");
            }
        }
        return f;
    }
    
    public static JSONObject splite(String path, int num)
        throws Exception
    {
        JSONObject data = new JSONObject();
        URL url = null;
        InputStream in = null;
        try
        {
            url = new URL(path);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            conn.setDoInput(true);
            conn.connect();
            in = conn.getInputStream(); // 得到网络返回的输入流
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        byte[] buffer = new byte[num];
        int length = 0;
        LinkedList<JSONObject> list = new LinkedList<JSONObject>();
        int len;
        JSONObject json = new JSONObject();
        while ((len = in.read(buffer)) != -1)
        {
            byte[] b = new byte[len];
            System.arraycopy(buffer, 0, b, 0, len);
            json = new JSONObject();
            json.put("length", len);
            
            // System.out.println(b.length);
            StringBuffer bufferStr = new StringBuffer();
            for (int i = 0; i < b.length; i++)
            {
                bufferStr.append(byte2Hex(b[i]));
                if (i < b.length - 1)
                {
                    bufferStr.append(" ");
                }
            }
            
            length += len;
            json.put("buffer", bufferStr.toString());
            list.add(json);
        }
        in.close();
        data.put("data", list);
        data.put("length", length);
        data.put("lastLength", length % num);
        
        return data;
    }
    
    public static void joint(LinkedList<Chunk> aList, String aFileName)
        throws Exception
    {
        FileOutputStream out = new FileOutputStream(aFileName);
        for (Chunk c : aList)
        {
            out.write(c.buffer, 0, c.length);
        }
        out.close();
    }
    
    class Chunk
    {
        public Chunk(byte[] aBuffer, int aLength)
        {
            buffer = aBuffer;
            length = aLength;
        }
        
        public byte[] buffer;
        
        public int length;
    }
    
    private static String byte2Hex(byte bytes)
    {
        
        String temp = null;
        temp = Integer.toHexString(bytes & 0xFF);
        if (temp.length() == 1)
        {
            // 1得到一位的进行补0操作
            temp = "0" + temp;
        }
        return temp;
    }
    
    public static void main(String[] args)
        throws Exception
    {
        splite("http://weirui0755.com/img/kernel_update.bin", 128);
    }
    
    /**
     * 根据文件后缀获取下载类型
     * 
     * @param fileEndPrifix
     * @return
     */
    public static String getContentType(String fileEndPrifix)
    {
        String prefix = StringUtils.substringAfterLast(fileEndPrifix, ".").toLowerCase();
        String contentype = "multipart/form-data";
        if ("pdf".equalsIgnoreCase(prefix))
        {
            contentype = "application/pdf;charset=UTF-8";
        }
        else if ("doc".equalsIgnoreCase(prefix) || "docx".equalsIgnoreCase(prefix))
        {
            contentype = "application/msword;charset=UTF-8";
        }
        else if ("xls".equalsIgnoreCase(prefix) || "xlsx".equalsIgnoreCase(prefix))
        {
            contentype = "application/vnd.ms-excel;charset=UTF-8";
        }
        else if ("ppt".equalsIgnoreCase(prefix))
        {
            contentype = "application/vnd.ms-powerpoint;charset=UTF-8";
        }
        else if ("jpeg".equalsIgnoreCase(prefix) || "jpe".equalsIgnoreCase(prefix) || "jpg".equalsIgnoreCase(prefix))
        {
            contentype = "image/jpeg";
        }
        else if ("png".equalsIgnoreCase(prefix))
        {
            contentype = "image/png";
        }
        else if ("gif".equalsIgnoreCase(prefix))
        {
            contentype = "image/gif";
        }
        else if ("txt".equalsIgnoreCase(prefix))
        {
            contentype = "text/plain";
        }
        else if ("avi".equalsIgnoreCase(prefix))
        {
            contentype = "video/x-msvideo";
        }
        else if ("movie".equalsIgnoreCase(prefix))
        {
            contentype = "video/x-sgi-movie";
        }
        else if ("zip".equalsIgnoreCase(prefix))
        {
            contentype = "application/octet-stream; charset=UTF-8";
        }
        return contentype;
    };
}
