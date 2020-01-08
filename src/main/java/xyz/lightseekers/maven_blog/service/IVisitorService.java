package xyz.lightseekers.maven_blog.service;


import xyz.lightseekers.maven_blog.bean.Visitor;

import java.util.List;

public interface IVisitorService {

    List<Visitor> selectAllVisitor() throws RuntimeException;

    List<Visitor> selectAllByURl(String url) throws RuntimeException;

    List<Visitor> selectAllByIp(String ip) throws RuntimeException;

    int deleteById(int id) throws RuntimeException;

    int insert( Visitor visitor ) throws RuntimeException;

    int update( Visitor visitor) throws RuntimeException;

    int deleteByBatch(int[] ids ) throws RuntimeException;
}
