package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Type;

import java.util.List;

public interface TypeHService {

    void deleteByid(int id) throws RuntimeException;
    void saveOrUpdate(Type type) throws RuntimeException;
    List<Type> findType() throws RuntimeException;
    List<Type> MHfind(String key) throws RuntimeException;

}
