package xyz.lightseekers.maven_blog.service;


import xyz.lightseekers.maven_blog.bean.Message;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;

import java.util.List;


public interface IMessageHService {

    int insertMessage(Message message) throws RuntimeException;
    int deleteMessage(int id) throws RuntimeException;
    List<MessageEX> selectByName(String name) throws RuntimeException;
    List<MessageEX> selectAll() throws RuntimeException;
    String deleteMessageP(int[] id) throws RuntimeException;
}
