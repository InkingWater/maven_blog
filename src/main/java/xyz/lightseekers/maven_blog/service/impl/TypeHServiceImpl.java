package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Type;
import xyz.lightseekers.maven_blog.bean.TypeExample;
import xyz.lightseekers.maven_blog.mapper.TypeMapper;
import xyz.lightseekers.maven_blog.service.TypeHService;


import java.util.List;

@Service

public class TypeHServiceImpl implements TypeHService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void deleteByid(int id) throws RuntimeException {
        typeMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void saveOrUpdate(Type type) throws RuntimeException {
        if(type==null)
        {
            throw new RuntimeException("参数为空");
        }else if (type.getId() != null)
        {
            typeMapper.insert(type);
        }else{
            typeMapper.updateByPrimaryKey(type);
        }


    }

    @Override
    public List<Type> findType() throws RuntimeException {
        TypeExample exp = new TypeExample();
        List<Type> list = typeMapper.selectByExample(exp);
        return list;
    }

    @Override
    public List<Type> MHfind(String key) throws RuntimeException {
        if ((key==null||"".equals(key))) {
            return findType();
        }else if(!"".equals(key))
        {//前者为空 后者不为空
            key="%"+key+"%";
            return typeMapper.selectByName(key);
        }
        return null;
    }
}
