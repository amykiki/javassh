package org.mybatis.smvc.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Amysue on 2016/6/2.
 */
public class StopWatchHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(StopWatchHandlerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("Request URL::" + request.getRequestURL().toString()+"::Start Time::" + startTime);
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long postTime = System.currentTimeMillis();
        logger.info("Request URL::" + request.getRequestURL().toString() + "::Send to Handler, Post Time=" + postTime);
        modelAndView.addObject("postTime", postTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long startTime = (long)request.getAttribute("startTime");
        logger.info("Request URL::" + request.getRequestURL().toString() + "::End Time=" + endTime);
        logger.info("Request URL::" + request.getRequestURL().toString() + "::Time Taken=" + (endTime - startTime));
    }
}
