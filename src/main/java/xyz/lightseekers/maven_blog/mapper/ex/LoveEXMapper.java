package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.LoveEX;

import java.util.List;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 15:06
 */
@Repository
public interface LoveEXMapper {
    /**
     * 获取所有的点赞者
     * @return 所有的点赞者
     */
    List<LoveEX> selectAll();
}
