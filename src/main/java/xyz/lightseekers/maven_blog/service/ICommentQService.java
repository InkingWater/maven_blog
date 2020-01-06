package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Comment;
import xyz.lightseekers.maven_blog.bean.ex.BlogEXQ;
import xyz.lightseekers.maven_blog.bean.ex.CommentUserBlogEXQ;

import java.util.List;

public interface ICommentQService {
    int insertToBlog(Comment comment) throws RuntimeException;

    int insertToComment(Comment comment) throws RuntimeException;

    int deleteByCommentId(int id) throws RuntimeException;

    List<CommentUserBlogEXQ> selectAll() throws RuntimeException;

    List<CommentUserBlogEXQ> selectByBlogIdByC(int id) throws RuntimeException;

    List<CommentUserBlogEXQ> selectByBlogId(int BlogId) throws RuntimeException;

    List<BlogEXQ> selectLikeBlogTitleByC(String blogTitle) throws RuntimeException;
}
