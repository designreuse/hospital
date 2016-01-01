package com.dpc.utils;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtil {

	//图片外部路径前缀
	public final static String IMAGE_PATH_EXTERNAL = "/upload/";
	public final static String DOMAIN = "http://localhost:8080/hospital";
	
	//医生患者关系状态
	public static class DoctorPatientRelation{
		public final static Integer PATIENT_MSG_SEND = 0;	//0：患者验证信息已发送。
		public final static Integer DOCTOR_YES = 1;			//1：医生允许。
		public final static Integer DOCTOR_NO = 2;			//2：医生拒绝。
		public final static Integer DOCTOR_MSG_SEND = 3;	//3：医生邀请信息已发送，
		public final static Integer PATIENT_YES = 4;		//4：患者允许，
		public final static Integer PATIENT_NO = 5;			//5：患者拒绝
	}
	
}
