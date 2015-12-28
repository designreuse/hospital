package com.dpc.web.mybatis3.domain;


import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Discovery {
    private Integer id;

    private String username;

    private String profileImageUrl;

    private String content;

    private Integer voteCount;

    private Integer remarkCount;

    private String postTime;

    private Integer delFlag;
    
    
    //非持久化字段
    private List<DiscoveryImage> imageList;
    private List<DiscoveryRemark> remarkList;
    
	public List<DiscoveryRemark> getRemarkList() {
		return remarkList;
	}

	public void setRemarkList(List<DiscoveryRemark> remarkList) {
		this.remarkList = remarkList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl == null ? null : profileImageUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getRemarkCount() {
        return remarkCount;
    }

    public void setRemarkCount(Integer remarkCount) {
        this.remarkCount = remarkCount;
    }

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public List<DiscoveryImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<DiscoveryImage> imageList) {
		this.imageList = imageList;
	}

	
}