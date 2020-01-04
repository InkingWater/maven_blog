package xyz.lightseekers.maven_blog.mapper.ex;

import org.springframework.stereotype.Repository;
import xyz.lightseekers.maven_blog.bean.ex.AuthorityEX;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 15:02 2019/12/31
 * @Modified User:
 */
@Repository
public interface AuthorityEXMapper {
    /**
     * 根据权限的id获取当前的权限和当前权限的子权限
     * @param id 权限的id
     * @return 当前的权限和当前权限的子权限
     */
    AuthorityEX selectChildren(int id);
}
