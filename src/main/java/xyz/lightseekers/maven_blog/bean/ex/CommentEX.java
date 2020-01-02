package xyz.lightseekers.maven_blog.bean.ex;

import xyz.lightseekers.maven_blog.bean.Blog;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 14:30
 */
public class CommentEX implements Serializable {
    private Integer id;

    private String content;

    private String ip;

    private Double longitude;

    private Double latitude;

    private UserEX user;

    private BlogEX blog;

    private List<CommentEX> children;

    @Override
    public String toString() {
        return "CommentEX{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", ip='" + ip + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", user=" + user +
                ", blog=" + blog +
                ", children=" + children +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public UserEX getUser() {
        return user;
    }

    public void setUser(UserEX user) {
        this.user = user;
    }

    public BlogEX getBlog() {
        return blog;
    }

    public void setBlog(BlogEX blog) {
        this.blog = blog;
    }

    public List<CommentEX> getChildren() {
        return children;
    }

    public void setChildren(List<CommentEX> children) {
        this.children = children;
    }
}
