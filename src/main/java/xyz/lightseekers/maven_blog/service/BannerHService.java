package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Banner;

import java.util.List;


public interface BannerHService {
    void saveorUpdate(Banner banner) throws RuntimeException;
    List<Banner> findT() throws RuntimeException;

}
