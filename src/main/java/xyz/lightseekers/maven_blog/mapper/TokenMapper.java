package xyz.lightseekers.maven_blog.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.lightseekers.maven_blog.bean.Token;
import xyz.lightseekers.maven_blog.bean.TokenExample;

public interface TokenMapper {
    long countByExample(TokenExample example);

    int deleteByExample(TokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Token record);

    int insertSelective(Token record);

    List<Token> selectByExample(TokenExample example);

    Token selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByExample(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}