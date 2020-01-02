package xyz.lightseekers.maven_blog.bean.ex;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/2 15:48
 */
public class MessageEX implements Serializable {
    private Integer id;

    private String content;

    private String qq;

    private String tel;

    private UserEX user;

    private String ip;

    private Double longitude;

    private Double latitude;

    private Integer flag;

    @Override
    public String toString() {
        return "MessageEX{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", qq='" + qq + '\'' +
                ", tel='" + tel + '\'' +
                ", user=" + user +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public UserEX getUser() {
        return user;
    }

    public void setUser(UserEX user) {
        this.user = user;
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
