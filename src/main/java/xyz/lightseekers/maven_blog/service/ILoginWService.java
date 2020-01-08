package xyz.lightseekers.maven_blog.service;

import java.util.Map;

public interface ILoginWService {
    Map<String,String> RandomImgCode(int lengh) throws RuntimeException;
    String SendEmail(String address) throws RuntimeException;
}
