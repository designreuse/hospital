package com.dpc.web.mybatis3.domain;


import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Discovery {
    private Integer id;
    
    private Integer userId;

    private String content;

    private Integer voteCount;

    private Integer remarkCount;

    private String postTime;

    private Integer delFlag;
    private Integer type;
    
    //非持久化字段
    private List<DiscoveryImage> imageList;
    private List<DiscoveryRemark> remarkList;
    private Integer start;
    private Integer limit;
    private String name;
    private String username;
    private String profileImageUrl;
    
	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public List<DiscoveryRemark> getRemarkList() {
		return remarkList;
	}

	public void setRemarkList(List<DiscoveryRemark> remarkList) {
		this.remarkList = remarkList;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
}