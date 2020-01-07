package xyz.lightseekers.maven_blog.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;
import xyz.lightseekers.maven_blog.service.IMessageHService;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/message")
@Api(description = "留言板")

public class MessageHController {
    @Autowired
    private IMessageHService messageHService;

    @PostMapping("/insertMessage")
    @ApiOperation("添加留言")
    public Message insertMessage(HttpServletRequest request, xyz.lightseekers.maven_blog.bean.Message message)
    {
        String ipAddr = BaiDuUtil.getIpAddr(request);
        Map<String,Object> json= BaiDuUtil.getLongitudeAndLatitude(ipAddr);
        message.setIp(ipAddr);
        message.setLatitude(Double.parseDouble((json.get("latitude")).toString()));
        message.setLongitude(Double.parseDouble((json.get("longitude").toString())));
        return MessageUtil.success(messageHService.insertMessage(message));
    }

    @GetMapping("/deleteMessage")
    @ApiOperation("删除留言")
    public Message deleteMessage(int id)
    {
        return MessageUtil.success(messageHService.deleteMessage(id));
    }

    @GetMapping("/selectMessage")
    @ApiOperation("查找留言")
    public  Message selectMessage(String name)
    {
        List<MessageEX> list= messageHService.selectByName(name);
        return MessageUtil.success(list);
    }
}
