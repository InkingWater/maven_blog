package xyz.lightseekers.maven_blog.service;

import java.util.Map;

public interface ILoginService {
    Map<String, String> randomImgCode(int length) throws RuntimeException;

    String sendEmail(String address) throws RuntimeException;
}
