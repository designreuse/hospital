package com.dpc.web.VO;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FieldDescn implements Serializable
{

	private static final long serialVersionUID = 1L;

	/**
	 * 字段名称
	 */
	private String fieldName;

	/**
	 * 字段类型
	 */
	private String fieldType;

	/**
	 * 字段说明
	 */
	private String fieldDescn;

	public FieldDescn()
	{
	}

	public FieldDescn(String fieldName, String fieldDescn, String fieldType)
	{
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldDescn = fieldDescn;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public String getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
	}

	public String getFieldDescn()
	{
		return fieldDescn;
	}

	public void setFieldDescn(String fieldDescn)
	{
		this.fieldDescn = fieldDescn;
	}

}
