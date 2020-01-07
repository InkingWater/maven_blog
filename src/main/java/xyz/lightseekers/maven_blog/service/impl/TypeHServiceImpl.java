package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Type;
import xyz.lightseekers.maven_blog.bean.TypeExample;
import xyz.lightseekers.maven_blog.mapper.TypeMapper;
import xyz.lightseekers.maven_blog.mapper.ex.TypeEXHMapper;
import xyz.lightseekers.maven_blog.service.ITypeHService;


import java.util.List;

@Service

public class TypeHServiceImpl implements ITypeHService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TypeEXHMapper typeEXHMapper;

    @Override
    public int deleteById(int id) throws RuntimeException {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(Type type) throws RuntimeException {
        if (type == null) {
            throw new RuntimeException("参数为空");
        } else if (type.getId() == null) {
            return typeMapper.insert(type);
        } else {
            return typeMapper.updateByPrimaryKey(type);
        }
    }

    @Override
    public List<Type> selectAll() throws RuntimeException {
        TypeExample exp = new TypeExample();
        List<Type> list = typeMapper.selectByExample(exp);
        return list;
    }

    @Override
    public List<Type> selectByKey(String key) throws RuntimeException {
        if ((key == null || "".equals(key))) {
            return selectAll();
        } else if (!"".equals(key)) {//前者为空 后者不为空
            key = "%" + key + "%";
            return typeEXHMapper.selectByName(key);
        }
        return null;
    }
}