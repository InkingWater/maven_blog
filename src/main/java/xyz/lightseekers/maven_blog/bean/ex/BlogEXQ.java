package xyz.lightseekers.maven_blog.bean.ex;

import java.io.Serializable;
import java.util.List;

public class BlogEXQ implements Serializable {
    private Integer id;
    private String title;
    private List<CommentUserBlogEXQ> list;

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

    public List<CommentUserBlogEXQ> getList() {
        return list;
    }

    public void setList(List<CommentUserBlogEXQ> list) {
        this.list = list;
    }
}
