package xyz.lightseekers.maven_blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import xyz.lightseekers.maven_blog.web.interceptor.WebInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/9 19:50
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Bean
    public HandlerInterceptor getWebInterceptor() {
        return new WebInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> url=new ArrayList<>();
        url.add("/authority/**");
        url.add("/banner/**");
        url.add("/blog/**");
        url.add("/comment/**");
        url.add("/message/**");
        url.add("/login/**");
        url.add("/love/**");
        url.add("/role/**");
        url.add("/type/**");
        url.add("/user/**");
        url.add("/visitor/**");
        registry.addInterceptor(getWebInterceptor()).addPathPatterns(url).excludePathPatterns("swagger-ui.html").excludePathPatterns("/webjars/**");
        super.addInterceptors(registry);
    }
}
