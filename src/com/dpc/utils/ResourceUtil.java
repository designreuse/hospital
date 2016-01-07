package com.dpc.utils;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 项目参数工具类
 * 
 */
public class ResourceUtil {

//    update-begin--Author:zhangguoming  Date:20140226 for：添加验证码
    /**
     * 获取随机码的长度
     *
     * @return 随机码的长度
     */
    public static String getRandCodeLength() {
        return "4";
    }

    /**
     * 获取随机码的类型
     *
     * @return 随机码的类型
     */
    public static String getRandCodeType() {
        return "5";
    }

	public static String getFirstQueryString(String requestPath) {
		return requestPath.substring(requestPath.indexOf("?")+1);
	}
	
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String queryString = request.getQueryString();
		String requestPath = "";
		if(StringUtil.isEmpty(queryString)){
			requestPath = request.getRequestURI();
		}else{
			requestPath = request.getRequestURI() + "?" + request.getQueryString();
		}
		
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}
}
