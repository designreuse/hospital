package com.dpc.web.mybatis3.domain;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DiscoveryRemark {
    private Integer id;

    private Integer discoveryId;
    
    private String remarkUserName;

    private String remarkUserProfile;

    private String remark;

    private String postTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemarkUserName() {
        return remarkUserName;
    }

    public void setRemarkUserName(String remarkUserName) {
        this.remarkUserName = remarkUserName == null ? null : remarkUserName.trim();
    }

    public String getRemarkUserProfile() {
        return remarkUserProfile;
    }

    public void setRemarkUserProfile(String remarkUserProfile) {
        this.remarkUserProfile = remarkUserProfile == null ? null : remarkUserProfile.trim();
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