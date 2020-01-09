package xyz.lightseekers.maven_blog.bean.ex;

import java.io.Serializable;
import java.util.List;

public class BlogCommentEX implements Serializable {
    private Integer id;
    private String title;
    private List<CommentUserBlogEX> list;

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

    public List<CommentUserBlogEX> getList() {
        return list;
    }

    public void setList(List<CommentUserBlogEX> list) {
        this.list = list;
    }
}
