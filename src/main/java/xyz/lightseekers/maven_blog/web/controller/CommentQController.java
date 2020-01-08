package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Comment;
import xyz.lightseekers.maven_blog.service.ICommentQService;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Repository("CommentQController")
@Api(description = "评论系统")
public class CommentQController {

    @Autowired
    private ICommentQService commentService;

    @GetMapping("/selectAll")
    public Message selectAll() {
        return MessageUtil.success(commentService.selectAll());
    }

    @PostMapping("/insertToBlogOrComment")
    @ApiOperation(value = "回复博客，或回复评论")
    public Message insertToBlogOrComment(HttpServletRequest request,Comment comment) {
        setMessage(comment,request);
        return MessageUtil.success(commentService.insertToBlogOrComment(comment));
    }

    @GetMapping("/selectByBlogIdByC")
    public Message selectByBlogIdByC(int id) {
        return MessageUtil.success(commentService.selectByBlogIdByC(id));
    }

    @GetMapping("/selectByBlogId")
    public Message selectByBlogId(int blogId) {
        return MessageUtil.success(commentService.selectByBlogId(blogId));
    }

    @GetMapping("/deleteByCommentId")
    public Message deleteByCommentId(int id) {
        return MessageUtil.success(commentService.deleteByCommentId(id));
    }

    @GetMapping("/selectLikeBlogTitleByC")
    public Message selectLikeBlogTitleByC(String blogTitle) {
        return MessageUtil.success(commentService.selectLikeBlogTitleByC(blogTitle));
    }

    private void setMessage(Comment comment, HttpServletRequest request){
        comment.setIp(BaiDuUtil.getIpAddr(request));
        Map<String, Object> longitudeAndLatitude = BaiDuUtil.getLongitudeAndLatitude(BaiDuUtil.getIpAddr(request));
        comment.setLongitude(Double.valueOf(longitudeAndLatitude.get("longitude").toString()));
        comment.setLatitude(Double.valueOf(longitudeAndLatitude.get("latitude").toString()));
    }

    @GetMapping("/deleteCommentByBatch")
    @ApiOperation(value = "批量删除")
    public Message deleteCommentByBatch(int ids[]){
        return MessageUtil.success(commentService.deleteCommentByBatch(ids));
    }
}
