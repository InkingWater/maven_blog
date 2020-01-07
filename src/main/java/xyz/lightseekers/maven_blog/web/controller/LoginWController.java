package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
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

    @GetMapping("/randomImg")
    public Message randomImg(int lenth){
        return MessageUtil.success(loginWService.RandomImgCode(lenth));
    }

}
