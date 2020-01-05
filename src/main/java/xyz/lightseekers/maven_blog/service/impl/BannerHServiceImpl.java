package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Banner;
import xyz.lightseekers.maven_blog.mapper.BannerMapper;
import xyz.lightseekers.maven_blog.service.BannerHService;


import java.util.List;

@Service
public class BannerHServiceImpl implements BannerHService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public void saveorUpdate(Banner banner) throws RuntimeException {
        if (banner == null) {
            throw new RuntimeException();
        }
        //如果传入的参数没有ID，则添加数据
        else if (banner.getId() != null) {
            bannerMapper.insert(banner);
        }
        //如果传入的参数有ID，则根据主键ID修改数据
        else {
            bannerMapper.updateFlag(banner);
        }

    }

    @Override
    public List<Banner> findT() throws RuntimeException {
        Banner banner = new Banner();
        List<Banner> list = bannerMapper.selectKy(banner);
        return list;
    }


}
