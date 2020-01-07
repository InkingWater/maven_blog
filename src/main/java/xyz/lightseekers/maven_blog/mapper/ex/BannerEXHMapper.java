package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.Banner;

import java.util.List;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/7 14:31
 */
@Repository
public interface BannerEXHMapper {
    int updateFlag(Banner banner);
    List<Banner> selectByFlag();
}
