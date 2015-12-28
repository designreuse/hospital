package com.dpc.web.mybatis3.domain;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WishRemark {
    private Integer id;

    private Integer wishId;

    private String remark;
    
    private String remarkUserName;
    
    private String remarkProfileImage;

    private String remarkTime;

    private Integer delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWishId() {
		return wishId;
	}

	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemarkTime() {
		return remarkTime;
	}

	public void setRemarkTime(String remarkTime) {
		this.remarkTime = remarkTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemarkUserName() {
		return remarkUserName;
	}

	public void setRemarkUserName(String remarkUserName) {
		this.remarkUserName = remarkUserName;
	}

	public String getRemarkProfileImage() {
		return remarkProfileImage;
	}

	public void setRemarkProfileImage(String remarkProfileImage) {
		this.remarkProfileImage = remarkProfileImage;
	}

}