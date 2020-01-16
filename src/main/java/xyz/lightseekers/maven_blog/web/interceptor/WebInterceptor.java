package xyz.lightseekers.maven_blog.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.lightseekers.maven_blog.bean.Visitor;
import xyz.lightseekers.maven_blog.service.IVisitorService;
import xyz.lightseekers.maven_blog.service.impl.VisitorServiceImpl;
import xyz.lightseekers.maven_blog.util.BaiDuUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: Light
 * @Date: 2020/1/9 19:49
 */
public class WebInterceptor implements HandlerInterceptor {

    @Autowired
    private IVisitorService visitorService;

    private static final Logger logger = LoggerFactory.getLogger(WebInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Visitor visitor=new Visitor();
        BaiDuUtil.setVisitor(request,visitor);
        visitorService.insert(visitor);
        logger.info("===={}====", visitor.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
