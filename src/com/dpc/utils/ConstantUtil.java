package com.dpc.utils;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtil {

	//图片外部路径前缀
	public final static String IMAGE_PATH_EXTERNAL = "/upload/";
	public final static String DOMAIN = "http://112.126.83.112:8080/hospital";
	public static final String MEMCACHE_IP = "112.126.83.112:11211";
	
	//医生患者关系状态
	public static class DoctorPatientRelation{
		public final static Integer PATIENT_MSG_SEND = 0;	//0：患者验证信息已发送。
		public final static Integer DOCTOR_YES = 1;			//1：医生允许。
		public final static Integer DOCTOR_NO = 2;			//2：医生拒绝。
		public final static Integer DOCTOR_MSG_SEND = 3;	//3：医生邀请信息已发送，
		public final static Integer PATIENT_YES = 4;		//4：患者允许，
		public final static Integer PATIENT_NO = 5;			//5：患者拒绝
	}
	
	//文章分类
	public static class ArticleCategory{
		public final static Integer HOME_SLIDE_IMAGE = 1;	//1：首页轮播图
		public final static Integer MEDICAL_DYNAMIC  = 2;	//2：医疗动态
		public final static Integer HAVE_FUN         = 3;	//3：轻松一刻
		public final static Integer HOME_H5_LINK     = 4;	//4：首页H5链接
		public final static Integer HEART_CARTOON    = 5;	//5：心漫画
		public final static Integer HEART_VIDEO      = 6;	//6：心视频
		public final static Integer HEART_KNOWLEDGE  = 7;	//7：心知识
	}
	
	public static class IllType{
		public final static Integer BANNER_SLIDE   = 1; //首页轮播图 
		public final static Integer H5_LINK        = 2; //首页H5链接
		public final static Integer SAN_GAO        = 3;	//三高
		public final static Integer GUAN_XIN_BING  = 4;	//冠心病
		public final static Integer XIN_GENG       = 5;	//心梗
		public final static Integer XIN_SHUAI      = 6;	//心衰
	}
	
	//session过期类型---单位：分钟
	public static class SessionExpireType{
		public final static long DEFAULT = 30*24*60;//授权码-30天
		public final static long TEN_MIN = 10;//临时授权码-10分钟
		public final static Map<String,Long> map = new HashMap<String,Long>();
		static{
			map.put("default", DEFAULT);
			map.put("ten_min", TEN_MIN);
		}
	}
}
