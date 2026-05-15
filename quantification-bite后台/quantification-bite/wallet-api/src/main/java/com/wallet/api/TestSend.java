package com.wallet.api;


import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.stereotype.Component;

@Component
public class TestSend {
    public  void  testSendMail(String mailContent,String headContent) throws GeneralSecurityException {
        // 收件人电子邮箱
        String to = "771547056@qq.com";

        // 发件人电子邮箱
        String from = "838882374@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  // QQ邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("838882374@qq.com", "tlgrbitzjkdabfde"); //发件人邮件用户名、授权码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject(headContent);

            // 设置消息体
            message.setText(mailContent);

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from 838882374@qq.com");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void main(String [] args) throws GeneralSecurityException {
        TestSend testSend = new TestSend();
        testSend.testSendMail("","");
    }
}
