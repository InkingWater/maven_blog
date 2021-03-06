package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Type;

import java.util.List;

public interface ITypeService {

    int deleteById(int id) throws RuntimeException;

    int saveOrUpdate(Type type) throws RuntimeException;

    List<Type> selectAll() throws RuntimeException;

    List<Type> selectByKey(String key) throws RuntimeException;

    String deleteTypes(int[] id) throws RuntimeException;
}
