package xyz.lightseekers.maven_blog.bean.ex;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 10:58
 */
public class UserEX implements Serializable {
    private Integer id;

    private String name;

    private String email;

    private String username;

    private String password;

    private RoleEX role;

    private Integer flag;

    @Override
    public String toString() {
        return "UserEX{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", flag=" + flag +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEX getRole() {
        return role;
    }

    public void setRole(RoleEX role) {
        this.role = role;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
