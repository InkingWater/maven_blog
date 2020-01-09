package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.BlogCommentEX;
import xyz.lightseekers.maven_blog.bean.ex.CommentEX;
import xyz.lightseekers.maven_blog.bean.ex.CommentUserBlogEX;

import java.util.List;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 14:34
 */
@Repository
public interface CommentEXMapper {
    /**
     * 根据博客的id获取全部的评论（层级的方式返回）
     *
     * @param blogId 博客的id
     * @return 当前博客全部的评论（层级的方式返回）
     */
    List<CommentEX> selectDataByBlogId(int blogId);

    List<CommentUserBlogEX> selectAll() throws RuntimeException;

    List<CommentUserBlogEX> selectByBlogIdLevel(int BlogId) throws RuntimeException;

    List<CommentUserBlogEX> selectByBlogId(int BlogId) throws RuntimeException;

    List<BlogCommentEX> selectLikeTitleLevel(String blogTitle) throws RuntimeException;
}
