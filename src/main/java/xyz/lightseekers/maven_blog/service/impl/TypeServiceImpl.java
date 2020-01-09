package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Type;
import xyz.lightseekers.maven_blog.bean.TypeExample;
import xyz.lightseekers.maven_blog.mapper.TypeMapper;
import xyz.lightseekers.maven_blog.mapper.ex.TypeEXMapper;
import xyz.lightseekers.maven_blog.service.ITypeService;


import java.util.List;

@Service

public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TypeEXMapper typeEXMapper;

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
            return typeEXMapper.selectByName(key);
        }
        return null;
    }

    @Override
    public String deleteTypes(int[] id) throws RuntimeException {
        for (int i = 0; i < id.length; i++) {
            deleteById(id[i]);
        }
        return id.toString();
    }
}
