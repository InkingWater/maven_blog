package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Banner;

import java.util.List;


public interface IBannerHService {
    int saveOrUpdate(Banner banner) throws RuntimeException;
    List<Banner> selectByFlag() throws RuntimeException;
    List<Banner> selectAll() throws RuntimeException;
    int deleteByid(int id) throws RuntimeException;

    String deleteBannerP(int id[]) throws RuntimeException;

}
