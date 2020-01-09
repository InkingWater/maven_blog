package xyz.lightseekers.maven_blog.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.lightseekers.maven_blog.bean.Banner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface IBannerService {
    int saveOrUpdate(Banner banner, MultipartFile uploadFile, HttpServletRequest request) throws RuntimeException;

    List<Banner> selectByFlag() throws RuntimeException;

    List<Banner> selectAll() throws RuntimeException;

    int deleteById(int id) throws RuntimeException;

    String deleteBanners(int id[]) throws RuntimeException;

}
