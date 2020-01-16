package xyz.lightseekers.maven_blog.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;
import xyz.lightseekers.maven_blog.service.IMessageService;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;


@RestController
@RequestMapping("/message")
@Api(description = "留言板模块-何恺越")

public class MessageController {
    @Autowired
    private IMessageService messageService;

    @PostMapping("/insertMessage")
    @ApiOperation("添加留言")
    public Message insertMessage(HttpServletRequest request, xyz.lightseekers.maven_blog.bean.Message message) {
        BaiDuUtil.setMessage(request, message);
        return MessageUtil.success(messageService.insertMessage(message));
    }

    @GetMapping("/deleteMessage")
    @ApiOperation("删除留言")
    public Message deleteMessage(int id) {
        return MessageUtil.success(messageService.deleteMessage(id));
    }

    @GetMapping("/selectMessage")
    @ApiOperation("查找某个用户留言")
    public Message selectMessage(String name) {
        List<MessageEX> list = messageService.selectByName(name);
        return MessageUtil.success(list);
    }

    @GetMapping("/deleteMessageP")
    @ApiOperation("批量删除留言")
    public Message deleteMessageP(int[] id) {
        return MessageUtil.success(messageService.deleteMessages(id));
    }

    @GetMapping("/downloadMessage")
    @ApiOperation("下载")
    public void downloadMessage(HttpServletResponse response) throws IOException {
        messageService.download(response);
    }

}
