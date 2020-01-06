package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.BlogEXQ;
import xyz.lightseekers.maven_blog.bean.ex.CommentUserBlogEXQ;

import java.util.List;

@Repository
public interface CommentEXQMapper {

    List<CommentUserBlogEXQ> selectAll() throws RuntimeException;

    List<CommentUserBlogEXQ> selectByBlogIdByC(int BlogId) throws RuntimeException;

    List<CommentUserBlogEXQ> selectByBlogId(int BlogId) throws RuntimeException;

    List<BlogEXQ> selectLikeBlogTitleByC(String blogTitle) throws RuntimeException;
}
