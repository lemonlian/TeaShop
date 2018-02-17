package com.interceptor;

import com.dao.DynamicDataSourceHolder;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 线上测试2
 * 拦截器实现数据读写操作
 */
/*
public class TestInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("测试请求");
        String method = httpServletRequest.getMethod();
        System.out.println("请求的类型" + method);
        if (method.equals("PUT") || method.equals("DELETE"))
            DynamicDataSourceHolder.putDataSource("write");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
*/
