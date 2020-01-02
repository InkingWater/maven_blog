package xyz.lightseekers.maven_blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.lightseekers.maven_blog.bean.RoleAuth;
import xyz.lightseekers.maven_blog.bean.RoleAuthExample;

public interface RoleAuthMapper {
    long countByExample(RoleAuthExample example);

    int deleteByExample(RoleAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuth record);

    int insertSelective(RoleAuth record);

    List<RoleAuth> selectByExample(RoleAuthExample example);

    RoleAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleAuth record, @Param("example") RoleAuthExample example);

    int updateByExample(@Param("record") RoleAuth record, @Param("example") RoleAuthExample example);

    int updateByPrimaryKeySelective(RoleAuth record);

    int updateByPrimaryKey(RoleAuth record);
}