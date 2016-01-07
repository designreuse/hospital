package com.dpc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dpc.web.mybatis3.domain.Admin;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String[] IGNORE_URI = {"/back/manager/login","/back/manager/logout","/login.jsp"};
	 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURL().toString();
        System.out.println(">>>: " + url);
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if (admin != null) flag = true;
        }
        if (!flag) {
        	request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
        
        return flag;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
