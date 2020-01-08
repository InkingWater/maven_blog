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
import xyz.lightseekers.maven_blog.service.IBannerHService;

import xyz.lightseekers.maven_blog.util.Message;
import xyz.lightseekers.maven_blog.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
@Api(description = "图片管理")
public class BannerHController {
    @Autowired
    private IBannerHService bannerHService;

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String UPLOAD_PATH_PREFIX = "static/image/banner/";
    public final static String LOOK_PATH_PREFIX="/image/banner/";


    @PostMapping("/insertImg")
    @ApiOperation("添加图片")
    public Message insertImg(Banner banner, MultipartFile uploadFile, HttpServletRequest request)
    {
        String filePath=null;
        if(uploadFile.isEmpty()){
            //返回选择文件提示
            return MessageUtil.success("请选择上传文件");
        }
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        File file = new File(realPath);
        if(!file.isDirectory()){
            //递归生成文件夹
            file.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            banner.setImg(LOOK_PATH_PREFIX + newName);
            filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + banner.getImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MessageUtil.success(bannerHService.saveOrUpdate(banner));
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

    @GetMapping("/selectAll")
    @ApiOperation("查找全部图片")
    public Message selectAll()
    {
        List<Banner> list = bannerHService.selectAll();
        return MessageUtil.success(list);
    }

    @GetMapping("/deleteBannerP")
    @ApiOperation("批量删除图片")
    public Message deleteBannerP(int[] id)
    {
        return MessageUtil.success(bannerHService.deleteBannerP(id));
    }
}
