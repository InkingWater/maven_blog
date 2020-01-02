package xyz.lightseekers.maven_blog.bean.ex;

import java.io.Serializable;
import java.util.List;

public class RoleAuthEX implements Serializable {
    private Integer id;

    private AuthorityEX auth;

    private List<RoleAuthEX> children;

    @Override
    public String toString() {
        return "RoleAuthEX{" +
                "id=" + id +
                ", auth=" + auth +
                ", children=" + children +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorityEX getAuth() {
        return auth;
    }

    public void setAuth(AuthorityEX auth) {
        this.auth = auth;
    }

    public List<RoleAuthEX> getChildren() {
        return children;
    }

    public void setChildren(List<RoleAuthEX> children) {
        this.children = children;
    }
}
