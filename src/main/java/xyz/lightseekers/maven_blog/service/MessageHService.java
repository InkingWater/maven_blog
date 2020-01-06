package xyz.lightseekers.maven_blog.service;


import xyz.lightseekers.maven_blog.bean.Message;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;

import java.util.List;


public interface MessageHService {

    void addMessage(Message message) throws RuntimeException;
    void deleteMessage(int id) throws RuntimeException;
    List<MessageEX> selectM(String name) throws RuntimeException;
    List<MessageEX> findMessage() throws RuntimeException;
}
