package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.BlogEX;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 15:50 2019/12/31
 * @Modified User:
 */
@Repository
public interface BlogEXMapper {
    /**
     * 根据博客的id 获取返回的作者名字，类别等
     * @param id 博客的id
     * @return 作者名字，类别，内容等
     */
    BlogEX selectById(int id);

    /**
     * 获取全部的博客，但是没有博客的标题
     * @return 全部的博客，但是没有博客的标题
     */
    BlogEX selectAllNoContent();
}
