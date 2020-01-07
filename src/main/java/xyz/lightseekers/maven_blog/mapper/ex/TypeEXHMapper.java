package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.Type;

import java.util.List;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/7 14:26
 */
@Repository
public interface TypeEXHMapper {
    List<Type> selectByName(String key);
}
