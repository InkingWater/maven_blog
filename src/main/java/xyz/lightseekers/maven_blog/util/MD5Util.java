package xyz.lightseekers.maven_blog.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/4 10:31
 */
public class MD5Util {
    /**
     * 对密码进行Md5加密
     * @param password 密码
     * @return Md5加密的结果
     */
    public static String createPassword(String password){
        String passwordMD5 = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            passwordMD5 = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            return passwordMD5;
        }
    }
}
