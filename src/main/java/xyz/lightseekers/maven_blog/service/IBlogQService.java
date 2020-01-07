package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Blog;
import xyz.lightseekers.maven_blog.bean.ex.BlogCountByMonth;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeEXQ;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeWithoutArticleEXQ;

import java.util.Date;
import java.util.List;

public interface IBlogQService {
    int deleteById(int id) throws RuntimeException;

    int update(Blog blog) throws RuntimeException;

    List<BlogUserTypeEXQ> searchByBlogTitle(String titleWord) throws RuntimeException;

    int insert(Blog blog) throws RuntimeException;

    List<BlogUserTypeEXQ> selectAll() throws RuntimeException;

    List<BlogUserTypeWithoutArticleEXQ> selectAllWithoutArticle() throws RuntimeException;

    BlogUserTypeEXQ selectById(int id) throws RuntimeException;

    List<BlogUserTypeEXQ> selectBetweenDate(Date lastDate, Date nextDate) throws RuntimeException;

    List<BlogUserTypeWithoutArticleEXQ> selectByTypeId(int typeId) throws RuntimeException;

    List<BlogUserTypeEXQ> selectToday() throws RuntimeException;

    List<BlogCountByMonth> selectDayCountByMonth() throws RuntimeException;
}
