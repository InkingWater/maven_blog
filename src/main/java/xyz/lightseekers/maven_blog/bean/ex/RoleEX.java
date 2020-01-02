package xyz.lightseekers.maven_blog.bean.ex;

import java.io.Serializable;

public class RoleEX implements Serializable {
    private Integer id;

    private String name;

    private String description;

    private RoleAuthEX authorities;

    @Override
    public String toString() {
        return "RoleEX{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", authorities=" + authorities +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleAuthEX getAuthorities() {
        return authorities;
    }

    public void setAuthorities(RoleAuthEX authorities) {
        this.authorities = authorities;
    }
}
