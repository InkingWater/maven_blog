package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Comment;
import xyz.lightseekers.maven_blog.bean.ex.BlogCommentEX;
import xyz.lightseekers.maven_blog.bean.ex.CommentUserBlogEX;

import java.util.List;

public interface ICommentService {
    int insertToBlogOrComment(Comment comment) throws RuntimeException;

    int deleteByCommentId(int id) throws RuntimeException;

    int deleteCommentByBatch(int ids[]) throws RuntimeException;

    List<CommentUserBlogEX> selectAll() throws RuntimeException;

    List<CommentUserBlogEX> selectByBlogIdLevel(int id) throws RuntimeException;

    List<CommentUserBlogEX> selectByBlogId(int BlogId) throws RuntimeException;

    List<BlogCommentEX> selectLikeTitleLevel(String blogTitle) throws RuntimeException;
}
