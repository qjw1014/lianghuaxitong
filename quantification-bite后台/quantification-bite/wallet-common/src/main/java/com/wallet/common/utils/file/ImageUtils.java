package com.wallet.common.utils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wallet.common.config.Config;
import com.wallet.common.constant.Constants;
import com.wallet.common.utils.StringUtils;

/**
 * 图片处理工具类
 *
 * @author wallet
 */
public class ImageUtils
{
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);
    
    public static byte[] getImage(String imagePath)
    {
        InputStream is = getFile(imagePath);
        try
        {
            return IOUtils.toByteArray(is);
        }
        catch (Exception e)
        {
            log.error("图片加载异常 {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }
    
    public static InputStream getFile(String imagePath)
    {
        try
        {
            byte[] result = readFile(imagePath);
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        }
        catch (Exception e)
        {
            log.error("获取图片异常 {}", e);
        }
        return null;
    }
    
    /**
     * 读取文件为字节数据
     * 
     * @param key 地址
     * @return 字节数据
     */
    public static byte[] readFile(String url)
    {
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        try
        {
            if (url.startsWith("http"))
            {
                // 网络地址
                URL urlObj = new URL(url);
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setConnectTimeout(30 * 1000);
                urlConnection.setReadTimeout(60 * 1000);
                urlConnection.setDoInput(true);
                in = urlConnection.getInputStream();
            }
            else
            {
                // 本机地址
                String localPath = Config.getProfile();
                String downloadPath = localPath + StringUtils.substringAfter(url, Constants.RESOURCE_PREFIX);
                in = new FileInputStream(downloadPath);
            }
            return IOUtils.toByteArray(in);
        }
        catch (Exception e)
        {
            log.error("获取文件路径异常 {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(baos);
        }
    }
    
    /**
     * 检验上传的图片后缀
     * 
     * @param fileName
     * @return
     */
    public static boolean checkImage(String fileName)
    {
        Pattern p = Pattern.compile(".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$");
        Matcher matcher = p.matcher(fileName.toLowerCase());
        if (matcher.find())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 检验上传的pdf后缀
     * 
     * @param fileName
     * @return
     */
    public static boolean checkFile(String fileName)
    {
        Pattern p = Pattern.compile(".+(.PDF|.pdf)$");
        Matcher matcher = p.matcher(fileName.toLowerCase());
        if (matcher.find())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 检验上传的文件后缀
     * 
     * @param fileName
     * @return
     */
    public static boolean checkDocFile(String fileName)
    {
        Pattern p = Pattern.compile(".+(|.doc|.DOC|.docx|.DOCX)$");
        Matcher matcher = p.matcher(fileName.toLowerCase());
        if (matcher.find())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 检验上传的Excel后缀
     * 
     * @param fileName
     * @return
     */
    public static boolean checkExcel(String fileName)
    {
        Pattern p = Pattern.compile(".+(.xls|.XLS|.xlsx|.XLSX)$");
        Matcher matcher = p.matcher(fileName.toLowerCase());
        if (matcher.find())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
