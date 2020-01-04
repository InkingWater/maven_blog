package xyz.lightseekers.maven_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: ZhiliangJia
 * @Description: swagger config class
 * @Date:Create in 9:38 2019/12/24
 * @Modified User:
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("xyz.lightseekers.maven_blog.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("个人博客管理系统")
				.description("Blog")
				.termsOfServiceUrl("http://www.lightseekers.xyz:8080")
				.version("1.1")
				.build();
	}
}

