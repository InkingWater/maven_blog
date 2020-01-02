package xyz.lightseekers.maven_blog.bean.ex;

import xyz.lightseekers.maven_blog.bean.Authority;

public class RoleEX {
    private Integer id;

    private String name;

    private String description;

    private AuthorityEX authorities;

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

    public AuthorityEX getAuthorities() {
        return authorities;
    }

    public void setAuthorities(AuthorityEX authorities) {
        this.authorities = authorities;
    }
}
