package xyz.lightseekers.maven_blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import xyz.lightseekers.maven_blog.convert.String2DateConverter;

import javax.annotation.PostConstruct;

/**
 * @Author: ZhiliangJia
 * @Description:
 * @Date:Create in 11:39 2019/12/28
 * @Modified User:
 */
@Configuration
public class WebMvcConfig {
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 增加字符串转日期的功能
     */

    @PostConstruct
    public void initEditable() {

        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();

            genericConversionService.addConverter(new String2DateConverter());

        }

    }
}
