package xyz.lightseekers.maven_blog.bean.ex;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 15:04
 */
public class LoveUserBlogEX implements Serializable {
    private Integer id;

    private UserEX user;

    private BlogDataEX blog;

    private String ip;

    private Double longitude;

    private Double latitude;

    private Integer flag;

    @Override
    public String toString() {
        return "LoveEX{" +
                "id=" + id +
                ", user=" + user +
                ", blog=" + blog +
                ", ip='" + ip + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", flag=" + flag +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEX getUser() {
        return user;
    }

    public void setUser(UserEX user) {
        this.user = user;
    }

    public BlogDataEX getBlog() {
        return blog;
    }

    public void setBlog(BlogDataEX blog) {
        this.blog = blog;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
