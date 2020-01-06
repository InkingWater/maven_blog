package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.ex.MessageEX;
import xyz.lightseekers.maven_blog.mapper.MessageMapper;
import xyz.lightseekers.maven_blog.mapper.ex.MessageEXMapper;
import xyz.lightseekers.maven_blog.service.MessageHService;

import java.util.List;


@Service
public class MessageHServiceImpl implements MessageHService {

    @Autowired
    private MessageEXMapper messageEXMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void addMessage(xyz.lightseekers.maven_blog.bean.Message message) throws RuntimeException {

        messageMapper.insert(message);

    }

    /**
     * 根据id删除留言
     * @param id
     * @throws RuntimeException
     */
    @Override
    public void deleteMessage(int id) throws RuntimeException {
        messageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据用户名查找相应留言,不输入会查找全部留言
     * @param name
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<MessageEX> selectM(String name) throws RuntimeException {
        if ((name==null||"".equals(name))) {
            return findMessage();
        }else if(!"".equals(name))
        {//前者为空 后者不为空
            name="%"+name+"%";
            return messageEXMapper.selectByName(name);
        }
        return null;
    }

    /**
     * 查找全部留言
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<MessageEX> findMessage() throws RuntimeException {
        List<MessageEX> list = messageEXMapper.selectAll();
        return list;
    }


}
