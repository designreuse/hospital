package com.dpc.utils;

import java.io.IOException;
import java.util.List;




import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class JsonUtil {

	/**
	 * map转换json字符串
	 * @param value
	 * @return
	 */
	public static String object2String(Object value) {
		if(value == null)
			return null;
		ObjectMapper mapper = new ObjectMapper(); 
		// 过滤对象的null属性.
		mapper.getSerializationConfig().disable(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES);		
		try {
			return mapper.writeValueAsString(value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static Object json2Object(String json,Class<?> clazz){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param json
	 * @return
	 */
	public static List<?> json2List(String json){
		if(json == null)
			return null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
