package com.dpc.web.mybatis3.domain;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HeartCircle {
    private Integer id;

    private String profileImage;

    private String doctorName;

    private String images;

    private String content;

    private Integer remarkCount;

    private String creTime;
    
    private Integer doctorId;
    
    private List<HeartCircleRemark> remarkList;
    private List<HeartCircleImage> imageList;
    public List<HeartCircleRemark> getRemarkList() {
		return remarkList;
	}

	public void setRemarkList(List<HeartCircleRemark> remarkList) {
		this.remarkList = remarkList;
	}

	public List<HeartCircleImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<HeartCircleImage> imageList) {
		this.imageList = imageList;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage == null ? null : profileImage.trim();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getRemarkCount() {
        return remarkCount;
    }

    public void setRemarkCount(Integer remarkCount) {
        this.remarkCount = remarkCount;
    }

	public String getCreTime() {
		return creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

}