package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;

import java.util.List;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 15:57
 */
@Repository
public interface MessageEXMapper {
    /**
     * 获取所有的留言
     *
     * @return 所有的留言
     */
    List<MessageEX> selectAll();

    List<MessageEX> selectByName(String name);
}
