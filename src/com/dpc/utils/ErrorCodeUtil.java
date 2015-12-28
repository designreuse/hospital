/*
 * -------------------------------------------------------
 * Copyright (c) 2015, 北京易易科技有限公司
 * All rights reserved.
 * 
 * FileName：ErrorCode.java
 * CreateTime:2015年6月15日上午11:29:01
 * Description：简要描述本文件的内容
 * History：
 * Date           Author               Desc
 * -------------------------------------------------------
 */
package com.dpc.utils;

import java.util.HashMap;
import java.util.Map;


public class ErrorCodeUtil
{
	
	private static ErrorCodeProperties pro = ErrorCodeProperties.getInstance();

	
	
	public static final String e10000 = "10000";   // 您无权进行此操作
	
	public static final String e10001 = "10001";   // 系统错误
	public static final String e10002 = "10002";   // 当前用户未登录
	public static final String e10003 = "10003";   // 原始密码不正确
	
	//注册
	public static final String e11001 = "11001";   //手机号不能为空
	public static final String e11002 = "11002";   //手机号格式不正确
	public static final String e11003 = "11003";   //密码不能为空
	public static final String e11004 = "11004";   //注册类型不能为空
	
	//登录
	public static final String e11100 = "11100";   //用户名不存在
	public static final String e11101 = "11101";   //密码不正确
	
	//诊后心得
	public static final String e11201 = "11201";   //病情描述不能为空
	public static final String e11202 = "11202";   //诊后心得不能为空
	public static final String e11203 = "11203";   //患者类型不能为空
	
	//心血管圈
	public static final String e11300 = "11300";   //心血管发表内容不能为空
	public static final String e11301 = "11301";   //心血管主键标识不能为空
	public static final String e11302 = "11302";   //心血管评论内容不能为空
	
	//患者端---发现
	public static final String e11401 = "11401";   //发表说说不能为空
	public static final String e11402 = "11402";   //评论内容不能为空
	public static final String e11403 = "11403";   //评论ID不能为空
	

	//患者端---个人
	public static final String e11500 = "11500";   //用户ID不能为空
	public static final String e11501 = "11501";   //许愿内容不能为空
	public static final String e11502 = "11502";   //许愿ID不能为空
	
	public static String errorMsg(Map<String, Object> map, String errorCode) {
		map.put("error_code", errorCode);
		map.put("error", pro.getProperties(errorCode));
		return JsonUtil.object2String(map);
	}
	
	public static String errorMsg(String errorCode){
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("error_code", errorCode);
		ret.put("error", pro.getProperties(errorCode));
		return JsonUtil.object2String(ret);
	}

}
