package xyz.lightseekers.maven_blog.bean.ex;

import xyz.lightseekers.maven_blog.bean.Authority;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 15:00 2019/12/31
 * @Modified User:
 */
public class AuthorityEX implements Serializable {
    private Integer id;

    private String name;

    private String url;

    private String description;

    private List<Authority> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Authority> getChildren() {
        return children;
    }

    public void setChildren(List<Authority> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "AuthorityEX{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", children=" + children +
                '}';
    }
}
