package xyz.lightseekers.maven_blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.lightseekers.maven_blog.bean.Banner;
import xyz.lightseekers.maven_blog.service.IBannerService;

import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
@Api(description = "图片模块-何恺越")
public class BannerController {
    @Autowired
    private IBannerService bannerService;

    @PostMapping("/insertImg")
    @ApiOperation("添加图片")
    public Message insertImg(Banner banner, MultipartFile uploadFile, HttpServletRequest request) {
        return MessageUtil.success(bannerService.saveOrUpdate(banner, uploadFile, request));
    }

    @PostMapping("updateImg")
    @ApiOperation("更新")
    public Message updateImg(Banner banner) {
        bannerService.saveOrUpdate(banner, null, null);
        return MessageUtil.success();
    }

    @GetMapping("/selectImg")
    @ApiOperation("查找可用图片")
    public Message selectImg() {
        List<Banner> list = bannerService.selectByFlag();
        return MessageUtil.success(list);
    }

    @GetMapping("/selectAll")
    @ApiOperation("查找全部图片")
    public Message selectAll() {
        List<Banner> list = bannerService.selectAll();
        return MessageUtil.success(list);
    }

    @GetMapping("/deleteBanners")
    @ApiOperation("批量删除图片")
    public Message deleteBanners(int[] id) {
        return MessageUtil.success(bannerService.deleteBanners(id));
    }
}
