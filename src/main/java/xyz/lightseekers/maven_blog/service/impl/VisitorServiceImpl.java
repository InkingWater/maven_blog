package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Visitor;
import xyz.lightseekers.maven_blog.bean.VisitorExample;
import xyz.lightseekers.maven_blog.mapper.VisitorMapper;
import xyz.lightseekers.maven_blog.service.IVisitorService;

import java.util.List;


@Service
public class VisitorServiceImpl implements IVisitorService {


    @Autowired
    private VisitorMapper visitorMapper;

    /**
     * 查找所有的visitor记录
     * @return visitor的集合
     * @throws RuntimeException
     */
    @Override
    public List<Visitor> selectAllVisitor() throws RuntimeException {
        VisitorExample visitorExample = new VisitorExample();
        return visitorMapper.selectByExample(visitorExample);
    }

    /**
     * 查找某个url的访问记录
     * @param url
     * @return 访问该url的visitor集合
     * @throws RuntimeException
     */
    @Override
    public List<Visitor> selectAllByURl(String url) throws RuntimeException {
        VisitorExample visitorExample =new VisitorExample();
        visitorExample.createCriteria().andUrlEqualTo(url);
        return visitorMapper.selectByExample(visitorExample);
    }


    /**
     * 查找某一个ip的访问记录
     * @param ip
     * @return 访问该ip的访问者记录
     * @throws RuntimeException
     */
    @Override
    public List<Visitor> selectAllByIp(String ip) throws RuntimeException {
        VisitorExample visitorExample =new VisitorExample();
        visitorExample.createCriteria().andIpEqualTo(ip);
        return visitorMapper.selectByExample(visitorExample);
    }

    /**
     * 通过id删除访问记录
     * @param id
     * @return
     * @throws RuntimeException
     */
    @Override
    public int deleteById(int id) throws RuntimeException {
        return visitorMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入访问记录
     * @param visitor visitor对象
     * @return insert的成功与否的值
     * @throws RuntimeException
     */
    @Override
    public int insert(Visitor visitor) throws RuntimeException {
        return visitorMapper.insert(visitor);
    }

    /**
     * 修改访问记录
     * @param visitor
     * @return update成功与否的值
     * @throws RuntimeException
     */
    @Override
    public int update(Visitor visitor) throws RuntimeException {
        return visitorMapper.updateByPrimaryKey(visitor);
    }

    /**
     * 批量删除
     * @param ids  输入要删除的对象的id数组
     * @return
     * @throws RuntimeException
     */
    @Override
    public int deleteByBatch(int[] ids) throws RuntimeException {
        for (int id : ids) {
          int i =   visitorMapper.deleteByPrimaryKey(id);
        }
        return 1;
    }

}