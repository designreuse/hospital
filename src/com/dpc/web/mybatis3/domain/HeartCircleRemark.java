package com.dpc.web.mybatis3.domain;


import java.util.Date;

public class HeartCircleRemark {
    private Integer id;

    private Integer heartCircleId;

    private String remark;

    private Integer doctorId;

    private String doctorName;

    private String profileImage;

    private String creTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeartCircleId() {
        return heartCircleId;
    }

    public void setHeartCircleId(Integer heartCircleId) {
        this.heartCircleId = heartCircleId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage == null ? null : profileImage.trim();
    }

	public String getCreTime() {
		return creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

}