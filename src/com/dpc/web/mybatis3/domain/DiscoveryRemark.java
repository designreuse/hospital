package com.dpc.web.mybatis3.domain;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DiscoveryRemark {
    private Integer id;

    private Integer discoveryId;
    
    private Integer userId;
    
    private String remark;

    private String postTime;

    
    private String remarkUserName;
    private String  remarkUserProfile;
    
    
    public String getRemarkUserName() {
		return remarkUserName;
	}

	public void setRemarkUserName(String remarkUserName) {
		this.remarkUserName = remarkUserName;
	}

	public String getRemarkUserProfile() {
		return remarkUserProfile;
	}

	public void setRemarkUserProfile(String remarkUserProfile) {
		this.remarkUserProfile = remarkUserProfile;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

	public Integer getDiscoveryId() {
		return discoveryId;
	}

	public void setDiscoveryId(Integer discoveryId) {
		this.discoveryId = discoveryId;
	}
    
    
}