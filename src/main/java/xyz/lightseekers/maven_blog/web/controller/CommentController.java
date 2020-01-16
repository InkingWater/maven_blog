package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Comment;
import xyz.lightseekers.maven_blog.service.ICommentService;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comment")
@Api(description = "评论模块-王秋润")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/selectAll")
    @ApiOperation(value = "获取全部评论信息——不按层级返回")
    public Message selectAll() {
        return MessageUtil.success(commentService.selectAll());
    }

    @PostMapping("/insertToBlogOrComment")
    @ApiOperation(value = "回复博客 或 回复评论")
    public Message insertToBlogOrComment(HttpServletRequest request, Comment comment) {
        BaiDuUtil.setComment(request, comment);
        return MessageUtil.success(commentService.insertToBlogOrComment(comment));
    }

    @GetMapping("/selectByBlogIdLevel")
    @ApiOperation(value = "查询指定博客Id的所有评论——按层级返回")
    public Message selectByBlogIdLevel(int id) {
        return MessageUtil.success(commentService.selectByBlogIdLevel(id));
    }

    @GetMapping("/selectByBlogId")
    @ApiOperation(value = "查询指定博客Id的所有评论——不按层级返回")
    public Message selectByBlogId(int blogId) {
        return MessageUtil.success(commentService.selectByBlogId(blogId));
    }

    @GetMapping("/deleteByCommentId")
    @ApiOperation(value = "通过评论id删除评论及子评论")
    public Message deleteByCommentId(int id) {
        return MessageUtil.success(commentService.deleteByCommentId(id));
    }

    @GetMapping("/selectLikeBlogTitleLevel")
    @ApiOperation(value = "根据评论的博客名进行模糊查找获取评论信息")
    public Message selectLikeBlogTitleLevel(String blogTitle) {
        return MessageUtil.success(commentService.selectLikeTitleLevel(blogTitle));
    }

    @GetMapping("/deleteCommentByBatch")
    @ApiOperation(value = "批量删除")
    public Message deleteCommentByBatch(int ids[]) {
        return MessageUtil.success(commentService.deleteCommentByBatch(ids));
    }
}
