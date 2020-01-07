package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lightseekers.maven_blog.bean.Banner;
import xyz.lightseekers.maven_blog.service.IBannerHService;

import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import java.util.List;

@RestController
@RequestMapping("/banner")
@Api(description = "图片管理")
public class BannerHController {
    @Autowired
    private IBannerHService bannerHService;

    @PostMapping("/insertImg")
    @ApiOperation("添加图片")
    public Message insertImg(Banner banner)
    {
        bannerHService.saveOrUpdate(banner);
        return MessageUtil.success();
    }

    @PostMapping("updateImg")
    @ApiOperation("更新")
    public Message updateImg(Banner banner)
    {

        bannerHService.saveOrUpdate(banner);
        return  MessageUtil.success();
    }

    @GetMapping("/selectImg")
    @ApiOperation("查找可用图片")
    public Message selectImg()
    {
        List<Banner> list = bannerHService.selectByFlag();
        return MessageUtil.success(list);
    }

}
