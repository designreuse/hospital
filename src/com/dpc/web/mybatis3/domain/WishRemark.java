package com.dpc.web.mybatis3.domain;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WishRemark {
    private Integer id;

    private Integer wishId;
    
    private Integer userId;

    private String remark;
    
    private String remarkTime;

    private Integer delFlag;

    private String remarkUserName;
    private String remarkProfileImage;
    
    
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

}