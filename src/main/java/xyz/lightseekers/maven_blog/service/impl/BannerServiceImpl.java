package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.lightseekers.maven_blog.bean.Banner;
import xyz.lightseekers.maven_blog.bean.BannerExample;
import xyz.lightseekers.maven_blog.mapper.BannerMapper;
import xyz.lightseekers.maven_blog.mapper.ex.BannerEXMapper;
import xyz.lightseekers.maven_blog.service.IBannerService;
import xyz.lightseekers.maven_blog.util.MessageUtil;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BannerServiceImpl implements IBannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private BannerEXMapper bannerEXMapper;

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String UPLOAD_PATH_PREFIX = "static/image/banner/";
    public final static String LOOK_PATH_PREFIX = "/image/banner/";

    @Override
    public int saveOrUpdate(Banner banner, MultipartFile uploadFile, HttpServletRequest request) throws RuntimeException {
        if (uploadFile == null) {
            //如果传入的参数有ID，则根据主键ID修改数据
            return bannerEXMapper.updateFlag(banner);
        }
        String filePath = null;
        if (uploadFile.isEmpty()) {
            //返回选择文件提示
            return 0;
        }
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        File file = new File(realPath);
        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
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
        if (banner == null) {
            throw new RuntimeException();
        } else if (banner.getId() == null) {
            //如果传入的参数没有ID，则添加数据
            return bannerMapper.insert(banner);
        } else {
            //如果传入的参数有ID，则根据主键ID修改数据
            return bannerEXMapper.updateFlag(banner);
        }

    }

    @Override
    public List<Banner> selectByFlag() throws RuntimeException {
        Banner banner = new Banner();
        List<Banner> list = bannerEXMapper.selectByFlag();
        return list;
    }

    @Override
    public List<Banner> selectAll() throws RuntimeException {
        BannerExample exp = new BannerExample();
        List<Banner> list = bannerMapper.selectByExample(exp);
        return list;
    }

    @Override
    public int deleteById(int id) throws RuntimeException {
        bannerMapper.deleteByPrimaryKey(id);
        return bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String deleteBanners(int[] id) throws RuntimeException {
        for (int i = 0; i < id.length; i++) {
            deleteById(id[i]);
        }
        return id.toString();
    }


}
