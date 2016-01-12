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
	public static final String e10004 = "10004";   // 性别不能为空
	public static final String e10005 = "10005";   // 生日不能为空
	public static final String e10006 = "10006";   // 姓名不能为空
	public static final String e10007 = "10007";   // 授权码不能为空
	public static final String e10008 = "10008";   // 授权码不合法
	
	//注册
	public static final String e11001 = "11001";   //手机号不能为空
	public static final String e11002 = "11002";   //手机号格式不正确
	public static final String e11003 = "11003";   //密码不能为空
	public static final String e11004 = "11004";   //注册类型不能为空
	public static final String e11005 = "11005";   //手机号码不存在
	public static final String e11006 = "11006";   //验证码已失效
	public static final String e11007 = "11007";   //验证码不正确
	public static final String e11008 = "11008";   //手机号码已注册
	public static final String e11009 = "11009";   //确认密码不能为空
	public static final String e11010 = "11010";   //新密码和确认密码不匹配
	
	//登录
	public static final String e11100 = "11100";   //用户名不存在
	public static final String e11101 = "11101";   //密码不正确
	
	//诊后心得
	public static final String e11201 = "11201";   //病情描述不能为空
	public static final String e11202 = "11202";   //诊后心得不能为空
	public static final String e11203 = "11203";   //患者类型不能为空
	public static final String e11204 = "11204";   //认证证件不完整
	public static final String e11206 = "11206";   //所在医院不能为空
	public static final String e11207 = "11207";   //技术职称不能为空
	public static final String e11208 = "11208";   //教学职称不能为空
	public static final String e11209 = "11209";   //科室不能为空
	public static final String e11210 = "11210";   //公告ID不能为空
	public static final String e11211 = "11211";   //公告内容不能为空
	public static final String e11212 = "11212";   //诊后心得ID不能为空
	public static final String e11213 = "11213";   //诊后心得评价内容不能为空
	
	//心血管圈
	public static final String e11300 = "11300";   //心血管发表内容不能为空
	public static final String e11301 = "11301";   //心血管主键标识不能为空
	public static final String e11302 = "11302";   //心血管评论内容不能为空
	public static final String e11303 = "11303";   //用户积分不够
	public static final String e11304 = "11304";   //已兑换积分，可参会
	public static final String e11305 = "11305";   //尚未兑换，请兑换
	
	//患者端---发现
	public static final String e11401 = "11401";   //发表说说不能为空
	public static final String e11402 = "11402";   //评论内容不能为空
	public static final String e11403 = "11403";   //评论ID不能为空
	

	//患者端---个人
	public static final String e11500 = "11500";   //用户ID不能为空
	public static final String e11501 = "11501";   //许愿内容不能为空
	public static final String e11502 = "11502";   //许愿ID不能为空
	public static final String e11503 = "11503";   //体重格式不正确
	
	public static final String e11600 = "11600";   //已经绑定该医生
	public static final String e11601 = "11601";   //与该医生是好友关系
	public static final String e11602 = "11602";   //您已绑定医生
	public static final String e11603 = "11603";   //邀请信息已发送
	public static final String e11604 = "11604";   //无法解绑
	
	//意见反馈
	public static final String e11700 = "11700";//反馈内容不能为空
	
	
	public static final String e11800 = "11800";//医生号码不能为空
	public static final String e11801 = "11801";//医生号码不存在
	public static final String e11802 = "11802";//医生主键不能为空
	
	public static final String e11900 = "11900";//省份ID不能为空
	public static final String e11901 = "11901";//机构ID不能为空
	public static final String e11902 = "11902";//获取机构类型不能为空
	
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
