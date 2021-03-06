package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Blog;
import xyz.lightseekers.maven_blog.bean.ex.BlogUserTypeEX;
import xyz.lightseekers.maven_blog.bean.ex.BlogWithoutArticleEX;
import xyz.lightseekers.maven_blog.service.IBlogService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;
import xyz.lightseekers.maven_blog.util.ZipUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/blog")
@Api(description = "博客模块-王秋润")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/selectAll")
    @ApiOperation(value = "查询全部博客信息")
    public Message selectAll() {
        List<BlogUserTypeEX> list = blogService.selectAll();
        return MessageUtil.success(list);
    }

    @GetMapping("/selectAllWithoutArticle")
    @ApiOperation(value = "查询除博客内容之外的全部博客信息")
    public Message selectAllWithoutArticle() {
        List<BlogWithoutArticleEX> list = blogService.selectAllWithoutArticle();
        return MessageUtil.success(list);
    }

    @GetMapping("/selectById")
    @ApiOperation(value = "通过Id查询全部博客信息")
    public Message selectById(int id) {
        return MessageUtil.success(blogService.selectById(id));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "创建一条新博客")
    public Message insert(Blog blog) {
        return MessageUtil.success(blogService.insert(blog));
    }

    @GetMapping("/searchByBlogTitle")
    @ApiOperation(value = "通过博客标题模糊查询博客的全部信息")
    public Message searchByBlogTitle(String titleWord) {
        return MessageUtil.success(blogService.searchByBlogTitle(titleWord));
    }

    @GetMapping("/deleteById")
    @ApiOperation(value = "通过Id删除某一条博客")
    public Message deleteById(int id) {
        return MessageUtil.success(blogService.deleteById(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新某一条博客的内容，同时更新时间戳")
    public Message update(Blog blog) {
        return MessageUtil.success(blogService.update(blog));
    }

    @GetMapping("/selectBetweenDate")
    @ApiOperation(value = "查询某一段时间内的所有博客信息")
    public Message selectBetweenDate(Date lastDate, Date nextDate) {
        return MessageUtil.success(blogService.selectBetweenDate(lastDate, nextDate));
    }

    @GetMapping("/selectByTypeId")
    @ApiOperation(value = "查询某一栏目的全部博客信息")
    public Message selectByTypeId(int typeId) {
        return MessageUtil.success(blogService.selectByTypeId(typeId));
    }

    @GetMapping("/selectToday")
    @ApiOperation(value = "查询今天发布的全部博客信息")
    public Message selectToday() {
        return MessageUtil.success(blogService.selectToday());
    }

    @GetMapping("/selectDayCountByMonth")
    @ApiOperation(value = "查询最近一个月的博客信息")
    public Message selectDayCountByMonth() {
        return MessageUtil.success(blogService.selectDayCountByMonth());
    }

    @GetMapping("/deleteByBatch")
    @ApiOperation(value = "批量删除")
    public Message deleteByBatch(int ids[]) {
        return MessageUtil.success(blogService.deleteByBatch(ids));
    }

    @GetMapping("/selectByVisitor")
    @ApiOperation(value = "查询访客量最多的十条数据")
    public Message selectByVisitor() {
        return MessageUtil.success(blogService.selectByVisitor());
    }

    @GetMapping("/downloadBlog")
    @ApiOperation(value = "下载博客信息压缩包")
    public void download(HttpServletResponse response) throws IOException {
        blogService.download(response);
    }


}
