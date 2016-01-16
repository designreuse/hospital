package com.dpc.web.mybatis3.domain;



import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CaseAnalysis {
    private Integer id;

    private String doctorName;

    private Integer eliteType;

    private String hospital;

    private String title;
    private String postTime;

    private String illCaseImage;
    private String remark;
    private String remarkPostTime;

    private String analysis;
    private Integer delFlag;

    private Integer start;
    private Integer limit;
    
    private List<CaseAnalysisRemark> remarkList;
    
	public List<CaseAnalysisRemark> getRemarkList() {
		return remarkList;
	}

	public void setRemarkList(List<CaseAnalysisRemark> remarkList) {
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemarkPostTime() {
		return remarkPostTime;
	}

	public void setRemarkPostTime(String remarkPostTime) {
		this.remarkPostTime = remarkPostTime;
	}

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

    

    public Integer getEliteType() {
		return eliteType;
	}

	public void setEliteType(Integer eliteType) {
		this.eliteType = eliteType;
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

	public String getPostTime() {
		return postTime;
	}
    
}