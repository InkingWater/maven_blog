package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Banner;

import java.util.List;


public interface IBannerHService {
    void saveOrUpdate(Banner banner) throws RuntimeException;
    List<Banner> selectByFlag() throws RuntimeException;
}