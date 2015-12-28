package com.dpc.web.mybatis3.domain;


import java.util.Date;

public class Wish {
    private Integer id;

    private Integer userId;

    private String patientName;

    private String patientProfileImageUrl;

    private String content;

    private String postTime;
    
    private Integer isComeTrue;

    private Integer delFlag;

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

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientProfileImageUrl() {
		return patientProfileImageUrl;
	}

	public void setPatientProfileImageUrl(String patientProfileImageUrl) {
		this.patientProfileImageUrl = patientProfileImageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getIsComeTrue() {
		return isComeTrue;
	}

	public void setIsComeTrue(Integer isComeTrue) {
		this.isComeTrue = isComeTrue;
	}

}