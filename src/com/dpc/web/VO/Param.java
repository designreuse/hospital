package com.dpc.web.VO;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Param implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String label;

	private String name;

	private String description;

	private int isRequired;

	private String argType;
	
	private Integer isRest;

	public Param(String label, String name, String description, int isRequired,
			String argType)
	{
		this.label = label;
		this.name = name;
		this.description = description;
		this.isRequired = isRequired;
		this.argType = argType;
		
	}

	public Param(String label, String name, int isRequired, String argType,Integer isRest)
	{
		this.label = label;
		this.name = name;
		this.isRequired = isRequired;
		this.argType = argType;
		this.isRest = isRest;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getIsRequired()
	{
		return isRequired;
	}

	public void setIsRequired(int isRequired)
	{
		this.isRequired = isRequired;
	}

	public String getArgType()
	{
		return argType;
	}

	public void setArgType(String argType)
	{
		this.argType = argType;
	}

	public Integer getIsRest() {
		return isRest;
	}

	public void setIsRest(Integer isRest) {
		this.isRest = isRest;
	}

}
