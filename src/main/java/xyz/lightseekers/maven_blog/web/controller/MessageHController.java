package xyz.lightseekers.maven_blog.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;
import xyz.lightseekers.maven_blog.service.MessageHService;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/message")
@Api(description = "留言板")

public class MessageHController {
    @Autowired
    private MessageHService messageHService;

    @PostMapping("/addMessage")
    @ApiOperation("添加留言")
    public Message addMessage(HttpServletRequest request, xyz.lightseekers.maven_blog.bean.Message message)
    {

        Map<String,Object> json= BaiDuUtil.getLongitudeAndLatitude(getIpAddr(request));
        message.setIp(getIpAddr(request));
        message.setLatitude(Double.parseDouble((json.get("latitude")).toString()));
        message.setLongitude(Double.parseDouble((json.get("longitude").toString())));
        messageHService.addMessage(message);
        return MessageUtil.success();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }

    @GetMapping("/deleteMessage")
    @ApiOperation("删除留言")
    public Message deleteMessage(int id)
    {
        messageHService.deleteMessage(id);
        return MessageUtil.success();
    }

    @GetMapping("/findMessage")
    @ApiOperation("查找留言")
    public  Message findMessage(String name)
    {
        List<MessageEX> list=messageHService.selectM(name);
        return MessageUtil.success(list);
    }
}
