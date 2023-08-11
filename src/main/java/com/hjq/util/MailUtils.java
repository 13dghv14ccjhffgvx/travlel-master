package com.hjq.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-29  17:14
 * @Description: 发邮件工具类
 * @Version: 1.0
 */
public class MailUtils {
    private static final String USER = "2136585216@qq.com"; //发件人称号，同邮箱地址
    private static final String PASSWORD = "fispazelnnsocafd"; //发件人邮箱密码,如果是qq邮箱可以使用客户端授权码

    /**
     * 发送带有验证信息的邮件
     * @param to 收件人邮箱
     * @param text 邮件正文
     * @param title 标题
     * @return
     */
    public static boolean sendMail(String to,String text,String title){
        try {
            final Properties properties = new Properties();
            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.host","smtp.qq.com");

            //发件人的账户
            properties.put("mail.user",USER);
            //发件人的密码
            properties.put("mail.password",PASSWORD);

            //构建授权信息，用于进行SMTP身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //用户名、密码
                    String username = properties.getProperty("mail.user");
                    String password = properties.getProperty("mail.password");
                    return new javax.mail.PasswordAuthentication(username,password);
                }
            };
            //使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(properties,authenticator);
            //创建邮件信息
            MimeMessage message = new MimeMessage(mailSession);
            //设置发件人
            String username = properties.getProperty("mail.user");
            InternetAddress fromAddress = new InternetAddress(username);
            message.setFrom(fromAddress);

            //设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO,toAddress);

            //设置邮件标题
            message.setSubject(title);

            //设置邮件的内容体
            message.setContent(text,"text/html;charset=UTF-8");
            //发送邮件
            Transport.send(message);
            System.out.println("MailUtil激活邮件已经成功发送!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        MailUtils.sendMail("aa13434691125@126.com","你好，这是一封测试邮件，无需回复。","测试邮件");
        System.out.println("发送成功");
    }
}
