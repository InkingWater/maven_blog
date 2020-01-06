package xyz.lightseekers.maven_blog.bean.ex;

public class BlogCountByMonth {
    private String datetime;

    private int blogCount;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }
}
