package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.lightseekers.maven_blog.service.ILoginService;
import xyz.lightseekers.maven_blog.service.IUserService;
import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
@Api(description = "登录模块-翁承煜")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IUserService userService;

    @ApiOperation("随机生成验证码并转换为base64格式")
    @GetMapping("/randomImg")
    @ApiImplicitParam(name = "length", value = "验证码长度", paramType = "query", dataType = "int")
    public Message randomImg(Integer length) {
        return MessageUtil.success(loginService.randomImgCode(length == null ? 4 : length));
    }

    @ApiOperation("向用户邮箱发送验证码")
    @GetMapping("/sendEmail")
    public Message sendEmail(String address) {
        return MessageUtil.success(loginService.sendEmail(address));
    }

    @ApiOperation("人脸登录")
    @PostMapping("/loginByFace")
    public Message loginByFace(Integer id,MultipartFile uploadFile, HttpServletRequest request){
        return MessageUtil.success(userService.loginFace(uploadFile,request,id));
    }

}
