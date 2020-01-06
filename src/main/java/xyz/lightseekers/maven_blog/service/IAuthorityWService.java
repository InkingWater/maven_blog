package xyz.lightseekers.maven_blog.service;

import xyz.lightseekers.maven_blog.bean.Authority;
import xyz.lightseekers.maven_blog.bean.ex.AuthorityEX;

import java.util.List;

public interface IAuthorityWService {

    int addOrUpdate(Authority authority) throws RuntimeException;
    int deleteById(int id) throws RuntimeException;
    List<AuthorityEX> selectAll() throws RuntimeException;
    List<Authority> search(String word)throws RuntimeException;
}
