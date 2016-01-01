package com.dpc.web.mybatis3.domain;


import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CaseAnalysis {
    private Integer id;

    private String doctorName;

    private String eliteType;

    private String hospital;

    private String title;

    private String postTime;

    private String illCaseImage;

    private String analysis;
    
    private Integer delFlag;

    
    public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getEliteType() {
        return eliteType;
    }

    public void setEliteType(String eliteType) {
        this.eliteType = eliteType == null ? null : eliteType.trim();
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIllCaseImage() {
        return illCaseImage;
    }

    public void setIllCaseImage(String illCaseImage) {
        this.illCaseImage = illCaseImage == null ? null : illCaseImage.trim();
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis == null ? null : analysis.trim();
    }
}