package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.Blog;
import xyz.lightseekers.maven_blog.bean.ex.BlogDataEX;
import xyz.lightseekers.maven_blog.bean.ex.BlogCountEX;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeEX;
import xyz.lightseekers.maven_blog.bean.ex.BlogWithoutArticleEX;

import java.util.Date;
import java.util.List;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 15:50 2019/12/31
 * @Modified User:
 */
@Repository
public interface BlogEXMapper {
    /**
     * 根据博客的id 获取返回的作者名字，类别等
     *
     * @param id 博客的id
     * @return 作者名字，类别，内容等
     */
    BlogDataEX selectUserById(int id);

    /**
     * 获取全部的博客，但是没有博客的标题
     *
     * @return 全部的博客，但是没有博客的标题
     */
    BlogDataEX selectAllNoContent();

    int insert(Blog blog) throws RuntimeException;

    List<BlogUserTypeEX> selectAll() throws RuntimeException;

    List<BlogWithoutArticleEX> selectAllWithoutArticle() throws RuntimeException;

    BlogUserTypeEX selectById(int id) throws RuntimeException;

    List<BlogUserTypeEX> selectByBlogTitle(String titleWord) throws RuntimeException;

    List<BlogUserTypeEX> selectBetweenDate(Date lastDate, Date nextDate) throws RuntimeException;

    List<BlogWithoutArticleEX> selectByTypeId(int typeId) throws RuntimeException;

    List<BlogUserTypeEX> selectToday() throws RuntimeException;

    List<BlogCountEX> selectDayCountByMonth(Date calendar_30, Date calendar) throws RuntimeException;

    List<BlogWithoutArticleEX> selectByVisitor() throws RuntimeException;
}
