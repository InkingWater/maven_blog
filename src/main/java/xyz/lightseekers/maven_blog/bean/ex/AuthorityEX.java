package xyz.lightseekers.maven_blog.bean.ex;

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

    private List<AuthorityEX> children;

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

    public List<AuthorityEX> getChildren() {
        return children;
    }

    public void setChildren(List<AuthorityEX> children) {
        this.children = children;
    }
}
