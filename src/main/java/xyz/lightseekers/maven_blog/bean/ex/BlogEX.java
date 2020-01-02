package xyz.lightseekers.maven_blog.bean.ex;

import xyz.lightseekers.maven_blog.bean.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 15:48 2019/12/31
 * @Modified User:
 */
public class BlogEX implements Serializable {
    private Integer id;

    private String title;

    private Date date;

    private Integer love;

    private Integer visitor;

    private Type type;

    private String article;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public Integer getVisitor() {
        return visitor;
    }

    public void setVisitor(Integer visitor) {
        this.visitor = visitor;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "BlogEX{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", love=" + love +
                ", visitor=" + visitor +
                ", type=" + type +
                ", article='" + article + '\'' +
                '}';
    }
}
