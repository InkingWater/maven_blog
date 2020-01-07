package xyz.lightseekers.maven_blog.service;

import java.util.Map;

public interface LoginWService {
    Map<String,String> RandomImgCode(int lengh) throws RuntimeException;
}
