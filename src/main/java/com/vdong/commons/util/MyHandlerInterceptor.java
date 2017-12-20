package com.vdong.commons.util;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截�?
 * @author SilverSea
 *
 */
public class MyHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object obj, Exception err)  
            throws Exception {  
    }  
  
    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
            Object obj, ModelAndView mav) throws Exception {  
    }  
  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object obj) throws Exception {  
       
        return true;  
    }  
}
