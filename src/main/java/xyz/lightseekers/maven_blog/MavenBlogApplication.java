package xyz.lightseekers.maven_blog;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScans(
        @MapperScan("xyz.lightseekers.maven_blog.mapper")
)
public class MavenBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MavenBlogApplication.class, args);
    }

}
