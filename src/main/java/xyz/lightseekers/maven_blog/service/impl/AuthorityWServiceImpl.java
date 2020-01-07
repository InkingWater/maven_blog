package xyz.lightseekers.maven_blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lightseekers.maven_blog.bean.Authority;
import xyz.lightseekers.maven_blog.bean.RoleAuthExample;
import xyz.lightseekers.maven_blog.bean.ex.AuthorityEX;
import xyz.lightseekers.maven_blog.mapper.AuthorityMapper;
import xyz.lightseekers.maven_blog.mapper.RoleAuthMapper;
import xyz.lightseekers.maven_blog.mapper.ex.AuthorityEXMapper;
import xyz.lightseekers.maven_blog.service.IAuthorityWService;

import java.util.List;

@Service
public class AuthorityWServiceImpl implements IAuthorityWService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private AuthorityEXMapper authorityEXMapper;

    @Autowired
    private RoleAuthMapper roleAuthMapper;

    @Override
    public int addOrUpdate(Authority authority) throws RuntimeException {
        if (authority==null) throw new RuntimeException();
        if (authority.getId()==null){
            return authorityMapper.insert(authority);
        }else{
            return authorityMapper.updateByPrimaryKey(authority);
        }
    }

    @Override
    public int deleteById(int id) throws RuntimeException {
        RoleAuthExample example = new RoleAuthExample();
        example.createCriteria().andAuthIdEqualTo(id);
        roleAuthMapper.deleteByExample(example);
        return authorityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<AuthorityEX> selectAll() throws RuntimeException {
        return authorityEXMapper.selectAll();
    }

    @Override
    public List<Authority> search(String word) throws RuntimeException {
        word= word==null?"":word;
        word="%"+word+"%";
        return authorityEXMapper.search(word);
    }


}
