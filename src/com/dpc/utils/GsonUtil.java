package com.dpc.utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.dpc.web.VO.Field;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {
	//list转json
	public static String listToJson(List<?> list){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");	
		gsonBuilder.disableHtmlEscaping();
		gsonBuilder.registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter());  
		Gson gson = gsonBuilder.create(); 
		return gson.toJson(list);
	}
	
	//map转json
	public static String mapToJson(Map<String,Object> map){
		GsonBuilder gsonBuilder = new GsonBuilder();  
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");	
		gsonBuilder.disableHtmlEscaping();
		gsonBuilder.registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter());  
		Gson gson = gsonBuilder.create(); 
		return gson.toJson(map);
	}
	
	//json转list
	public static List<Field> jsonToFieldList(String json){
		GsonBuilder gsonBuilder = new GsonBuilder();  
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");	
		gsonBuilder.disableHtmlEscaping();
		gsonBuilder.registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter());  
		Gson gson = gsonBuilder.create(); 
		
		List<Field> retList = gson.fromJson(json,new TypeToken<List<Field>>() {}.getType() );
		return retList;
	}
}
