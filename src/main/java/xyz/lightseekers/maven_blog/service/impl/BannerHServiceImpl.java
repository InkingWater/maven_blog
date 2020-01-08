package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Banner;
import xyz.lightseekers.maven_blog.bean.BannerExample;
import xyz.lightseekers.maven_blog.mapper.BannerMapper;
import xyz.lightseekers.maven_blog.mapper.ex.BannerEXHMapper;
import xyz.lightseekers.maven_blog.service.IBannerHService;



import java.util.List;

@Service
public class BannerHServiceImpl implements IBannerHService {
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private BannerEXHMapper bannerEXHMapper;

    @Override
    public int saveOrUpdate(Banner banner) throws RuntimeException {
        if (banner == null) {
            throw new RuntimeException();
        }
        //如果传入的参数没有ID，则添加数据
        else if (banner.getId() == null) {
            return bannerMapper.insert(banner);
        }
        //如果传入的参数有ID，则根据主键ID修改数据
        else {
            return bannerEXHMapper.updateFlag(banner);
        }

    }

    @Override
    public List<Banner> selectByFlag() throws RuntimeException {
        Banner banner = new Banner();
        List<Banner> list = bannerEXHMapper.selectByFlag();
        return list;
    }

    @Override
    public List<Banner> selectAll() throws RuntimeException {
        BannerExample exp = new BannerExample();
        List<Banner> list = bannerMapper.selectByExample(exp);
        return list;
    }

    @Override
    public int deleteByid(int id) throws RuntimeException {
        bannerMapper.deleteByPrimaryKey(id);
        return bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String deleteBannerP(int[] id) throws RuntimeException {
        for (int i=0;i<id.length;i++){
            deleteByid(id[i]);
        }
        return id.toString();
    }


}
