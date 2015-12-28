package com.dpc.web.mybatis3.domain;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ErrorCode implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String code;
	private String descnCN;
	private String descnEN;
	private Integer delFlag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescnCN() {
		return descnCN;
	}
	public void setDescnCN(String descnCN) {
		this.descnCN = descnCN;
	}
	public String getDescnEN() {
		return descnEN;
	}
	public void setDescnEN(String descnEN) {
		this.descnEN = descnEN;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}