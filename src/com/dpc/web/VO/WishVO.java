package com.dpc.web.VO;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.dpc.web.mybatis3.domain.WishRemark;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WishVO implements Serializable{
	private Integer id;
	
	private String patientName;
	
	private String patientProfileImageUrl;
	
	private String postTime;
	
	private String isComeTrue;
	
	private String wishContent;
	
	private List<WishRemark> wishRemarkList;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPatientProfileImageUrl() {
		return patientProfileImageUrl;
	}

	public void setPatientProfileImageUrl(String patientProfileImageUrl) {
		this.patientProfileImageUrl = patientProfileImageUrl;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}


	public String getIsComeTrue() {
		return isComeTrue;
	}

	public void setIsComeTrue(String isComeTrue) {
		this.isComeTrue = isComeTrue;
	}

	public String getWishContent() {
		return wishContent;
	}

	public void setWishContent(String wishContent) {
		this.wishContent = wishContent;
	}

	public List<WishRemark> getWishRemarkList() {
		return wishRemarkList;
	}

	public void setWishRemarkList(List<WishRemark> wishRemarkList) {
		this.wishRemarkList = wishRemarkList;
	}
	
}