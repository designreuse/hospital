
package com.dpc.web.mybatis3.domain;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DiagnoseExperience {
    private Integer id;

    private String doctorName;

    private Integer doctorId;

    private String illDesc;
    
    private String illType;

    private Integer readCount;

    private Integer remarkCount;

    private Integer status;
    private Integer reward;//打赏积分

    private Integer isAnonymous;

    private String experience;

    private String creTime;
    private Integer delFlag;
    
    //
    private Integer remainScore;
    private String profileImage;
    private List<DiagnoseExperienceRemark> remarkList;
    private List<DiagnoseExperienceImage> diagnoseExpImgList;
    
    private Integer start;
    private Integer limit;
    private Integer score;
    
    
	public Integer getScore() {
		return score;
	}

	public Integer getReward() {
		return reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
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

	public Integer getRemainScore() {
		return remainScore;
	}

	public void setRemainScore(Integer remainScore) {
		this.remainScore = remainScore;
	}

	public List<DiagnoseExperienceImage> getDiagnoseExpImgList() {
		return diagnoseExpImgList;
	}

	public void setDiagnoseExpImgList(List<DiagnoseExperienceImage> diagnoseExpImgList) {
		this.diagnoseExpImgList = diagnoseExpImgList;
	}

	public List<DiagnoseExperienceRemark> getRemarkList() {
		return remarkList;
	}

	public void setRemarkList(List<DiagnoseExperienceRemark> remarkList) {
		this.remarkList = remarkList;
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

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getIllDesc() {
        return illDesc;
    }

    public void setIllDesc(String illDesc) {
        this.illDesc = illDesc == null ? null : illDesc.trim();
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getRemarkCount() {
        return remarkCount;
    }

    public void setRemarkCount(Integer remarkCount) {
        this.remarkCount = remarkCount;
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Integer isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    public String getCreTime() {
        return creTime;
    }

    public void setCreTime(String creTime) {
        this.creTime = creTime;
    }

	public String getIllType() {
		return illType;
	}

	public void setIllType(String illType) {
		this.illType = illType;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
    
}