package com.dpc.web.service;

import java.util.List;
import java.util.Map;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.ErrorCode;

/**
 *
 *DiWu
 */

public interface IErrorCodeService
{

	public List<Map<String, Object>> getErrorCodes();

	public void addErrorCode(ErrorCode errorCode);

	/** 
	* @Title: getByID 
	* @Description: TODO
	* @param @param id
	* @param @return
	* @return Object
	* @throws 
	*/
	public ErrorCode getByID(Integer id);

	/** 
	* @Title: updateErrorCode 
	* @Description: TODO
	* @param @param errorCode
	* @return void
	* @throws 
	*/
	public void updateErrorCode(ErrorCode errorCode);

	/** 
	* @Title: findByPagination 
	* @Description: 分页
	* @param @return
	* @return Object
	* @throws 
	*/
	public Pager<ErrorCode> findByPagination();

	/** 
	* @Title: delete 
	* @Description: TODO
	* @param @param ids
	* @return void
	* @throws 
	*/
	public void delete(String[] ids);

	/** 
	* @Title: getAll 
	* @Description: TODO
	* @param @return
	* @return List<ErrorCode>
	* @throws 
	*/
	public List<ErrorCode> getAll();
	
}
