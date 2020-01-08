package xyz.lightseekers.maven_blog.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {

    public static String SentEmail(String address,String code){
        HtmlEmail email=new HtmlEmail();
        email.setHostName("smtp.qq.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");
        try {
            email.addTo(address);//收信人邮箱
            email.setFrom("873485523@qq.com","抽象工作室");//发信人邮箱 和姓名
            email.setAuthentication("873485523@qq.com","epdnyqngfdrpbcji");//发送人的邮箱的用户名和授权码
            email.setSubject("验证码");//设置发送主题
            email.setMsg("验证码为："+code);//设置发送内容
            email.send();//进行发送
            return "发送成功";
        } catch (EmailException e) {
            e.printStackTrace();
            return "发送失败";
        }
    }


    public static void main(String[] args){
        HtmlEmail email=new HtmlEmail();
        email.setHostName("smtp.qq.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");
        try {

                    email.addTo("418055608@qq.com");//收信人邮箱
                    email.setFrom("873485523@qq.com","aa");//发信人邮箱 和姓名
                    email.setAuthentication("873485523@qq.com","epdnyqngfdrpbcji");//发送人的邮箱的用户名和授权码
                    email.setSubject("测试");//设置发送主题
                    email.setMsg("nmsl");//设置发送内容
                    email.send();//进行发送
            System.out.println("发送成功");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
