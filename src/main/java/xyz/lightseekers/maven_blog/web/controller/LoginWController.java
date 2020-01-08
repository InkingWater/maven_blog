package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.service.ILoginWService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

@RestController
@RequestMapping("/login")
@Api(description = "登录")
public class LoginWController {

    @Autowired
    private ILoginWService loginWService;

    @ApiOperation("随机生成验证码并转换为base64格式")
    @GetMapping("/randomImg")
    @ApiImplicitParam(name = "lenth",value = "验证码长度",paramType = "query",dataType = "int")
    public Message randomImg(int lenth){
        return MessageUtil.success(loginWService.randomImgCode(lenth));
    }

    @ApiOperation("向用户邮箱发送验证码")
    @GetMapping("/sendEmail")
    public Message sendEmail(String address){
        return MessageUtil.success(loginWService.sendEmail(address));
    }

}
