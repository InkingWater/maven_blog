package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Blog;
import xyz.lightseekers.maven_blog.bean.ex.BlogCountEX;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeEX;
import xyz.lightseekers.maven_blog.bean.ex.BlogWithoutArticleEX;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public interface IBlogService {
    int deleteById(int id) throws RuntimeException;

    int update(Blog blog) throws RuntimeException;

    List<BlogUserTypeEX> searchByBlogTitle(String titleWord) throws RuntimeException;

    int insert(Blog blog) throws RuntimeException;

    List<BlogUserTypeEX> selectAll() throws RuntimeException;

    List<BlogWithoutArticleEX> selectAllWithoutArticle() throws RuntimeException;

    BlogUserTypeEX selectById(int id) throws RuntimeException;

    List<BlogUserTypeEX> selectBetweenDate(Date lastDate, Date nextDate) throws RuntimeException;

    List<BlogWithoutArticleEX> selectByTypeId(int typeId) throws RuntimeException;

    List<BlogUserTypeEX> selectToday() throws RuntimeException;

    List<BlogCountEX> selectDayCountByMonth() throws RuntimeException;

    int deleteByBatch(int ids[]) throws RuntimeException;

    List<BlogWithoutArticleEX> selectByVisitor() throws RuntimeException;

    void download(HttpServletResponse response) throws RuntimeException;
}
