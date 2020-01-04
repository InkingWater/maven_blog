package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Blog;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeEXQ;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeWithoutArticleEXQ;
import xyz.lightseekers.maven_blog.service.IBlogService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("BlogQController")
@Api(description = "博客管理")
public class BlogQController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/selectAll")
    @ApiOperation(value = "查询全部博客信息")
    public Message selectAll() {
        List<BlogUserTypeEXQ> list = blogService.selectAll();
        return MessageUtil.success(list);
    }

    @GetMapping("/selectAllWithoutArticle")
    @ApiOperation(value = "查询除博客内容之外的全部博客信息")
    public Message selectAllWithoutArticle() {
        List<BlogUserTypeWithoutArticleEXQ> list = blogService.selectAllWithoutArticle();
        return MessageUtil.success(list);
    }

    @GetMapping("/selectById")
    @ApiOperation(value = "通过Id查询全部博客信息")
    @ApiImplicitParam(name = "id", value = "编号Id", required = true)
    public Message selectById(int id) {
        return MessageUtil.success(blogService.selectById(id));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "创建一条新博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "博客编号"),
            @ApiImplicitParam(name = "userId", value = "博主Id"),
            @ApiImplicitParam(name = "title", value = "博客标题"),
            @ApiImplicitParam(name = "date", value = "发表时间"),
            @ApiImplicitParam(name = "love", value = "点赞数"),
            @ApiImplicitParam(name = "visitor", value = "访问量"),
            @ApiImplicitParam(name = "typeId", value = "博客栏目Id"),
            @ApiImplicitParam(name = "article", value = "博客内容")
    })
    public Message insert(Blog blog) {
        return MessageUtil.success(blogService.insert(blog));
    }

    @GetMapping("/searchByBlogTitle")
    @ApiOperation(value = "通过博客标题模糊查询博客的全部信息")
    @ApiImplicitParam(name = "titleWord", value = "博客标题", required = true)
    public Message searchByBlogTitle(String titleWord) {
        return MessageUtil.success(blogService.searchByBlogTitle(titleWord));
    }

    @GetMapping("/deleteById")
    @ApiOperation(value = "通过Id删除某一条博客")
    @ApiImplicitParam(name = "id", value = "编号Id", required = true)
    public Message deleteById(int id) {
        return MessageUtil.success(blogService.deleteById(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新某一条博客的内容，同时更新时间戳")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "博客编号"),
            @ApiImplicitParam(name = "userId", value = "博主Id"),
            @ApiImplicitParam(name = "title", value = "博客标题"),
            @ApiImplicitParam(name = "date", value = "发表时间"),
            @ApiImplicitParam(name = "love", value = "点赞数"),
            @ApiImplicitParam(name = "visitor", value = "访问量"),
            @ApiImplicitParam(name = "typeId", value = "博客栏目Id"),
            @ApiImplicitParam(name = "article", value = "博客内容")
    })
    public Message update(Blog blog) {
        return MessageUtil.success(blogService.update(blog));
    }

    @GetMapping("/selectBetweenDate")
    @ApiOperation(value = "查询某一段时间内的所有博客信息")
//    @ApiImplicitParams({
////            @ApiImplicitParam(name = "lastDate",value = "起始时间",dataType = "date"),
////            @ApiImplicitParam(name = "nextDate",value = "截止时间",dataType = "date")
////    })
    public Message selectBetweenDate(Date lastDate, Date nextDate) {
        return MessageUtil.success(blogService.selectBetweenDate(lastDate, nextDate));
    }

    @GetMapping("/selectByTypeId")
    @ApiOperation(value = "查询某一栏目的全部博客信息")
//    @ApiImplicitParam(name = "typeId", value = "栏目编号Id", dataType = "Integer",required = true)
    public Message selectByTypeId(int typeId){
        return MessageUtil.success(blogService.selectByTypeId(typeId));
    }
}
