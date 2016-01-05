package com.dpc.utils;

import java.util.HashMap;
import java.util.List;
import com.cloopen.rest.sdk.CCPRestSDK;
import com.dpc.utils.memcached.MemSession;

public class SMSUtil {

	private static final String DEV_BASE_URL = "sandboxapp.cloopen.com";
	private static final String PRO_BASE_URL = "app.cloopen.com";
	private static final String PORT = "8883";
	private static final String ACCOUNT_SID = "aaf98f894a51c0bd014a56d82a320314";
	private static final String AUTH_TOKEN = "aa71286c58c74870b22c39bd7ea56201";
	private static final String APP_ID = "8a48b5514acda26e014ae12746b508a6";
	private static final String TEMPLATEID = "11362";
	
	public static String sendSMS(String mobile){
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(PRO_BASE_URL, PORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(ACCOUNT_SID, AUTH_TOKEN);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(APP_ID);// 初始化应用ID
		String randomCode = createRandom(mobile,true,4);
		result = restAPI.sendTemplateSMS(mobile,TEMPLATEID ,new String[]{randomCode,"10"});
		String code = result.get("statusCode").toString();
		if(code.equals("000000")){
			//存入session
			MemSession session = MemSession.getSession(mobile,true,"ten_min");
			session.setAttribute("verifycode", randomCode, "ten_min");
		}
		return code;
	}
	
	public static String createRandom(String mobile,boolean numberFlag, int length){  
		  String retStr = "";  
		  String strTable = numberFlag ? mobile : "1234567890abcdefghijkmnpqrstuvwxyz";  
		  int len = strTable.length();  
		  boolean bDone = true;  
		  do {  
		   retStr = "";  
		   int count = 0;  
		   for (int i = 0; i < length; i++) {  
		    double dblR = Math.random() * len;  
		    int intR = (int) Math.floor(dblR);  
		    char c = strTable.charAt(intR);  
		    if (('0' <= c) && (c <= '9')) {  
		     count++;  
		    }  
		    retStr += strTable.charAt(intR);  
		   }  
		   if (count >= 2) {  
		    bDone = false;  
		   }  
		  } while (bDone);  
		  
		  return retStr;  
		 }  
}
