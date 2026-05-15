package com.wallet.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.wallet.common.utils.JsonUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wallet.common.exception.CustomException;
import com.wallet.file.domain.FtpLink;
import com.wallet.file.service.IFtpService;

@Service
public class FtpServiceImpl implements IFtpService
{
    private static final Logger logger = LoggerFactory.getLogger(FtpServiceImpl.class);
    
    private static String LOCAL_CHARSET = "GBK";
    
    // FTP协议里面，规定文件名编码为iso-8859-1
    private static String SERVER_CHARSET = "ISO-8859-1";
    
    @Override
    public String uploadFile(MultipartFile file, String catalogue, FtpLink ftpLink)
    {
        logger.info("========ftp文件开始上传======================》");
        String url = null;
        FTPClient ftp = new FTPClient();
        // 给图片重新设定名字
        String fileName = file.getOriginalFilename();
        logger.info("========fileName:{}", fileName);
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        logger.info("========suffix:{}", suffix);
        String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        fileName = uuid + suffix;
        logger.info("========fileName:{}", fileName);
        try
        {
            ftp.connect(ftpLink.getFtpHost(), ftpLink.getFtpPort());// 设置地址和端口号
            ftp.login(ftpLink.getFtpUserName(), ftpLink.getFtpPassword());// 用户名和密码
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);// 上传文件类型 二进制文件
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {// 检查连接是否有效
                logger.info("===================ftp连接异常error");
                throw new CustomException("Ftp上传链接失败");
            }
            
            InputStream inputStream = file.getInputStream();
            try
            {
                ftp.makeDirectory(File.separator + catalogue);// 自定义目录
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(File.separator + catalogue);
            ftp.storeFile(fileName, inputStream);// 关键代码,把流持久化到硬盘上
            inputStream.close();
            url = File.separator + catalogue + File.separator + fileName;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error("上传图片异常",e);
        }
        finally
        {
            if (ftp.isConnected())
            {
                try
                {
                    // 退出登录
                    ftp.logout();
                    // 关闭连接
                    ftp.disconnect();
                }
                catch (IOException e)
                {
                }
            }
        }
        return url;
    }

    @Override
        public String uploadStream(String fileName, InputStream inputStream , String catalogue, FtpLink ftpLink)
    {
        logger.info("========ftp文件开始上传======================》");
        String url = null;
        FTPClient ftp = new FTPClient();
        // 给图片重新设定名字
        logger.info("========fileName:{}", fileName);
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        logger.info("========suffix:{}", suffix);
        String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        fileName = uuid + suffix;
        logger.info("========fileName:{}", fileName);
        try
        {
            ftp.connect(ftpLink.getFtpHost(), ftpLink.getFtpPort());// 设置地址和端口号
            ftp.login(ftpLink.getFtpUserName(), ftpLink.getFtpPassword());// 用户名和密码
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);// 上传文件类型 二进制文件
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {// 检查连接是否有效
                logger.info("===================ftp连接异常error");
                throw new CustomException("Ftp上传链接失败");
            }
            try
            {
                ftp.makeDirectory(File.separator + catalogue);// 自定义目录
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(File.separator + catalogue);
            ftp.storeFile(fileName, inputStream);// 关键代码,把流持久化到硬盘上
            inputStream.close();
            url = File.separator + catalogue + File.separator + fileName;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error("上传图片异常",e);
        }
        finally
        {
            if (ftp.isConnected())
            {
                try
                {
                    // 退出登录
                    ftp.logout();
                    // 关闭连接
                    ftp.disconnect();
                }
                catch (IOException e)
                {
                }
            }
        }
        return url;
    }




    /**
     * 删除文件 未测试
     * 

     * @return
     */
    @Override
    public boolean deleteFile(FtpLink ftpLink, String pathname)
    {
        boolean flag = false;
        FTPClient ftp = new FTPClient();
        String filename = org.apache.commons.lang3.StringUtils.substringAfterLast(pathname, "/");
        logger.info("+==========================filename:" + filename);
        pathname = pathname.substring(0, pathname.lastIndexOf("/"));
        logger.info("+==========================pathname:" + pathname);
        try
        {
            ftp.connect(ftpLink.getFtpHost(), ftpLink.getFtpPort());// 设置地址和端口号
            ftp.login(ftpLink.getFtpUserName(), ftpLink.getFtpPassword());// 用户名和密码
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);// 上传文件类型 二进制文件
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {// 检查连接是否有效
                logger.info("===================ftp连接异常error");
                throw new CustomException("Ftp删除链接失败");
            }
            // 验证FTP服务器是否登录成功
            int replyCode = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode))
            {
                return flag;
            }
            // 切换FTP目录
            ftp.changeWorkingDirectory(pathname);
            // 设置上传文件的类型为二进制类型
            if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON")))
            {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                LOCAL_CHARSET = "UTF-8";
            }
            ftp.setControlEncoding(LOCAL_CHARSET);
            ftp.enterLocalPassiveMode();// 设置被动模式
            ftp.setFileType(FTP.BINARY_FILE_TYPE);// 设置传输的模式
            // 对中文名称进行转码
            filename = new String(filename.getBytes(LOCAL_CHARSET), SERVER_CHARSET);
            ftp.dele(filename);
            flag = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (ftp.isConnected())
            {
                try
                {
                    // 退出登录
                    ftp.logout();
                    // 关闭连接
                    ftp.disconnect();
                }
                catch (IOException e)
                {
                }
            }
        }
        return flag;
    }
    
}
