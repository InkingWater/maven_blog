package xyz.lightseekers.maven_blog.web.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Visitor;
import xyz.lightseekers.maven_blog.service.impl.VisitorServiceImpl;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static xyz.lightseekers.maven_blog.util.BaiDuUtil.getLongitudeAndLatitude;

@RestController
@RequestMapping("visitor")
@Api(description = "访问管理")
public class VisitorController {

    @Autowired
    private VisitorServiceImpl visitorService;

    @GetMapping("/selectAllVisitor")
    @ApiOperation(value = "查找全部的visitor信息")
    public Message selectAllVisitor( ){
        return MessageUtil.success( visitorService.selectAllVisitor());
    }


    @GetMapping("/selectAllByUrl")
    @ApiOperation(value = "查找某一个url的访问记录")
    public Message selectAllByUrl(String url){
        return MessageUtil.success(visitorService.selectAllByURl(url));
    }

    @GetMapping("/selectAllByIp")
    @ApiOperation(value = "/查找指定ip的访问记录")
    public Message selectAllByIp(String ip){
        return MessageUtil.success(visitorService.selectAllByIp(ip));
    }


    @GetMapping("/deleteById")
    @ApiOperation(value = "删除记录")
    public Message deleteById(int id){
        return MessageUtil.success(visitorService.deleteById(id));
    }


    @PostMapping("/insert")
    @ApiOperation(value = "插入新的记录")
    public Message insert(HttpServletRequest request){
      //  request.getRequestURI();
        Visitor visitor = new Visitor();
        String ip = getIpAddr(request);
        visitor.setIp(ip);
        visitor.setUrl(request.getRequestURI());
        visitor.setLatitude((double)getLongitudeAndLatitude(ip).get("latitude"));
        visitor.setLongitude((double)getLongitudeAndLatitude(ip).get("longitude"));
        visitor.setAddress((String.valueOf(getLongitudeAndLatitude(ip).get("address"))));
     //  System.out.println( getLongitudeAndLatitude("192.168.255.1"));
        return MessageUtil.success(visitorService.insert(visitor));
    }


    @PostMapping("/update")
    @ApiOperation(value = "更新新的数据")
    public Message update(Visitor visitor,HttpServletRequest request){
        String ip = getIpAddr(request);
        visitor.setIp(ip);
        visitor.setUrl(request.getRequestURI());
        visitor.setLatitude((double)getLongitudeAndLatitude(ip).get("latitude"));
        visitor.setLongitude((double)getLongitudeAndLatitude(ip).get("longitude"));
        visitor.setAddress((String.valueOf(getLongitudeAndLatitude(ip).get("address"))));
        return MessageUtil.success(visitorService.update(visitor));
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



}
