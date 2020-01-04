package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Blog;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeEXQ;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeWithoutArticleEXQ;
import xyz.lightseekers.maven_blog.mapper.BlogMapper;
import xyz.lightseekers.maven_blog.mapper.ex.BlogEXQMapper;
import xyz.lightseekers.maven_blog.service.IBlogService;

import java.util.Date;
import java.util.List;
@Service
public class BlogServiceImpl implements IBlogService {

    @Autowired
    private BlogEXQMapper blogEXQMapper;
    @Autowired
    private BlogMapper blogMapper;


    @Override
    public int deleteById(int id) throws RuntimeException {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Blog blog) throws RuntimeException {
        blog.setDate(new Date());
        return blogMapper.updateByPrimaryKey(blog);
    }

    @Override
    public List<BlogUserTypeEXQ> searchByBlogTitle(String titleWord) throws RuntimeException {
        titleWord = "%" + titleWord + "%";
        return blogEXQMapper.searchByBlogTitle(titleWord);
    }

    @Override
    public int insert(Blog blog) throws RuntimeException {
        blog.setDate(new Date());
        return blogEXQMapper.insert(blog);
    }

    @Override
    public List<BlogUserTypeEXQ> selectAll() throws RuntimeException {
        return blogEXQMapper.selectAll();
    }

    @Override
    public List<BlogUserTypeWithoutArticleEXQ> selectAllWithoutArticle() throws RuntimeException {
        return blogEXQMapper.selectAllWithoutArticle();
    }

    @Override
    public BlogUserTypeEXQ selectById(int id) throws RuntimeException {
        return blogEXQMapper.selectById(id);
    }

    @Override
    public List<BlogUserTypeEXQ> selectBetweenDate(Date lastDate, Date nextDate) throws RuntimeException {
        return blogEXQMapper.selectBetweenDate(lastDate,nextDate);
    }

    @Override
    public List<BlogUserTypeEXQ> selectByToday(Date today) throws RuntimeException {
        return null;
    }

    @Override
    public List<BlogUserTypeWithoutArticleEXQ> selectByTypeId(int typeId) throws RuntimeException {
        return blogEXQMapper.selectByTypeId(typeId);
    }
}
