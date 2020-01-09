package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Authority;
import xyz.lightseekers.maven_blog.bean.ex.AuthorityEX;

import java.util.List;

public interface IAuthorityService {

    int addOrUpdate(Authority authority) throws RuntimeException;

    int deleteById(int id) throws RuntimeException;

    List<AuthorityEX> selectAll() throws RuntimeException;

    List<Authority> selectByName(String word) throws RuntimeException;
}
