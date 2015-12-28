package com.dpc.web.VO;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Field implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -23240488190660425L;
	public String label;
	public String name;
	public String description;
	public int isRequired;
	public int isRest;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(int isRequired) {
		this.isRequired = isRequired;
	}
	public int getIsRest() {
		return isRest;
	}
	public void setIsRest(int isRest) {
		this.isRest = isRest;
	}

}
