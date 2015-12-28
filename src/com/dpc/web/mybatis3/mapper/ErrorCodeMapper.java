package com.dpc.web.mybatis3.mapper;

import java.util.List;
import java.util.Map;

import com.dpc.web.mybatis3.domain.ErrorCode;

public interface ErrorCodeMapper {

	/** 
	* @Title: getErrorCodes 
	* @Description: TODO
	* @param @return
	* @return List<Map<String,Object>>
	* @throws 
	*/
	List<Map<String, Object>> getErrorCodes();

	/** 
	* @Title: addErrorCode 
	* @Description: TODO
	* @param @param errorCode
	* @return void
	* @throws 
	*/
	void addErrorCode(ErrorCode errorCode);

	/** 
	* @Title: getById 
	* @Description: TODO
	* @param @param id
	* @param @return
	* @return Object
	* @throws 
	*/
	ErrorCode getById(Integer id);

	/** 
	* @Title: updateErrorCode 
	* @Description: TODO
	* @param @param errorCode
	* @return void
	* @throws 
	*/
	void updateErrorCode(ErrorCode errorCode);

	/** 
	* @Title: getErrorCodeByPage 
	* @Description: TODO
	* @param @param start
	* @param @param limit
	* @param @return
	* @return List<ErrorCode>
	* @throws 
	*/
	List<ErrorCode> getErrorCodeByPage(int start, int limit);

	/** 
	* @Title: getTotalCount 
	* @Description: TODO
	* @param @return
	* @return Integer
	* @throws 
	*/
	Integer getTotalCount();

	/** 
	* @Title: getAll 
	* @Description: TODO
	* @param @return
	* @return List<ErrorCode>
	* @throws 
	*/
	List<ErrorCode> getAll();
	
	public void batchDel(String[] ids);

	
}
