package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Comment;
import xyz.lightseekers.maven_blog.bean.CommentExample;
import xyz.lightseekers.maven_blog.bean.ex.BlogEXQ;
import xyz.lightseekers.maven_blog.bean.ex.CommentUserBlogEXQ;
import xyz.lightseekers.maven_blog.mapper.CommentMapper;
import xyz.lightseekers.maven_blog.mapper.ex.CommentEXQMapper;
import xyz.lightseekers.maven_blog.service.ICommentQService;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class CommentQServiceImpl implements ICommentQService {

    @Autowired
    private CommentEXQMapper commentEXQMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private HttpServletRequest request;


    @Override
    public int insertToBlog(Comment comment) throws RuntimeException {
        return commentMapper.insert(comment);
    }

    @Override
    public int insertToComment(Comment comment) throws RuntimeException {
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteByCommentId(int id) throws RuntimeException {
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo(id);
        commentMapper.countByExample(example);
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CommentUserBlogEXQ> selectAll() throws RuntimeException {
        return commentEXQMapper.selectAll();
    }

    @Override
    public List<CommentUserBlogEXQ> selectByBlogIdByC(int id) throws RuntimeException {
        return commentEXQMapper.selectByBlogIdByC(id);
    }

    @Override
    public List<CommentUserBlogEXQ> selectByBlogId(int BlogId) throws RuntimeException {
        return commentEXQMapper.selectByBlogId(BlogId);
    }

    @Override
    public List<BlogEXQ> selectLikeBlogTitleByC(String blogTitle) throws RuntimeException {
        blogTitle = "%" + blogTitle + "%";
        return commentEXQMapper.selectLikeBlogTitleByC(blogTitle);
    }
}
