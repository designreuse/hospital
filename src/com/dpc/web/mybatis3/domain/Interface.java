package com.dpc.web.mybatis3.domain;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Interface implements Serializable{
	private Integer id;
	private Integer categoryId;
	private String name;//接口名称	utf8	utf8_general_ci		0	0
	private String method;
	private String params;
	private String itemDesc;
	private String errorDesc;
	private String fieldDescn;
	private String errorResult;
	private String successResult;
	private Integer status;
	private Integer isRest;
	private Integer delFlag;
	
	private InterfaceCategory category;
	
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getFieldDescn() {
		return fieldDescn;
	}
	public void setFieldDescn(String fieldDescn) {
		this.fieldDescn = fieldDescn;
	}
	public String getErrorResult() {
		return errorResult;
	}
	public void setErrorResult(String errorResult) {
		this.errorResult = errorResult;
	}
	public String getSuccessResult() {
		return successResult;
	}
	public void setSuccessResult(String successResult) {
		this.successResult = successResult;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public InterfaceCategory getCategory() {
		return category;
	}
	public void setCategory(InterfaceCategory category) {
		this.category = category;
	}
	public Integer getIsRest() {
		return isRest;
	}
	public void setIsRest(Integer isRest) {
		this.isRest = isRest;
	}



}