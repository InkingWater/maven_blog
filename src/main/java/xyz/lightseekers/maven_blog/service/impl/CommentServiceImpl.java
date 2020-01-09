package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Comment;
import xyz.lightseekers.maven_blog.bean.CommentExample;
import xyz.lightseekers.maven_blog.bean.ex.BlogCommentEX;
import xyz.lightseekers.maven_blog.bean.ex.CommentUserBlogEX;
import xyz.lightseekers.maven_blog.mapper.CommentMapper;
import xyz.lightseekers.maven_blog.mapper.ex.CommentEXMapper;
import xyz.lightseekers.maven_blog.service.ICommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentEXMapper commentEXMapper;

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public int insertToBlogOrComment(Comment comment) throws RuntimeException {
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteCommentByBatch(int[] ids) throws RuntimeException {
        for (int i = 0; i < ids.length; i++) {
            commentMapper.deleteByPrimaryKey(ids[i]);
        }
        return ids.length;
    }

    @Override
    public int deleteByCommentId(int id) throws RuntimeException {
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo(id);
        commentMapper.countByExample(example);
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CommentUserBlogEX> selectAll() throws RuntimeException {
        return commentEXMapper.selectAll();
    }

    @Override
    public List<CommentUserBlogEX> selectByBlogIdLevel(int id) throws RuntimeException {
        return commentEXMapper.selectByBlogIdLevel(id);
    }

    @Override
    public List<CommentUserBlogEX> selectByBlogId(int BlogId) throws RuntimeException {
        return commentEXMapper.selectByBlogId(BlogId);
    }

    @Override
    public List<BlogCommentEX> selectLikeTitleLevel(String blogTitle) throws RuntimeException {
        blogTitle = "%" + blogTitle + "%";
        return commentEXMapper.selectLikeTitleLevel(blogTitle);
    }
}
