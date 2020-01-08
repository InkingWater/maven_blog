package xyz.lightseekers.maven_blog.service;

import java.util.Map;

public interface ILoginWService {
    Map<String,String> randomImgCode(int lengh) throws RuntimeException;
    String sendEmail(String address) throws RuntimeException;
}
