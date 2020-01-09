package xyz.lightseekers.maven_blog.service;


import xyz.lightseekers.maven_blog.bean.Message;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface IMessageService {

    int insertMessage(Message message) throws RuntimeException;

    int deleteMessage(int id) throws RuntimeException;

    List<MessageEX> selectByName(String name) throws RuntimeException;

    List<MessageEX> selectAll() throws RuntimeException;

    String deleteMessages(int[] id) throws RuntimeException;

    void download(HttpServletResponse response) throws RuntimeException;
}
