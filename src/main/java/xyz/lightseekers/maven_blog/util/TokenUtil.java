package xyz.lightseekers.maven_blog.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/4 9:51
 */
public class TokenUtil {
    /**
     * 根据用户名和时间戳获取token
     * @param username 用户名
     * @param time 时间戳
     * @return null或token值
     */
    public static String createToken(String username, String time) {
        String need = username + time;
        String token = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(token.getBytes());
            token = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }
}
