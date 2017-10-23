package com.huorong.Intercept;

import com.huorong.service.CommonService;
import com.huorong.service.redis.RedisService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huorong on 17/10/23.
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    RedisService redisService;
    @Autowired
    CommonService commonService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            if (redisService == null) {// 解决service为null无法注入问题
                System.out.println("redisService is null!!!");
                BeanFactory factory = WebApplicationContextUtils
                        .getRequiredWebApplicationContext(request.getServletContext());
                redisService = (RedisService) factory.getBean("redisService");
            }
            if (commonService == null) {// 解决service为null无法注入问题
                System.out.println("commonService is null!!!");
                BeanFactory factory = WebApplicationContextUtils
                        .getRequiredWebApplicationContext(request.getServletContext());
                commonService = (CommonService) factory.getBean("commonService");
            }
            System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
            String key = redisService.getStr("loginKey");
            if (key != null) {
                Cookie[] cookies = request.getCookies();
                boolean flag = false;
                for (Cookie cookie : cookies) {
                    if (commonService.CookieDeAESC(cookie.getValue()).equals(key)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendError(250);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
