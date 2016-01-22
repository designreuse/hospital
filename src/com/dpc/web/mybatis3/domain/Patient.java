
package com.dpc.web.mybatis3.domain;

public class Patient {
    private Integer id;

    private Integer userId;

    private Double weight;

    private Integer score;
    private Integer hasBind;

    private String illProfile;

    
    public Integer getHasBind() {
		return hasBind;
	}

	public void setHasBind(Integer hasBind) {
		this.hasBind = hasBind;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getIllProfile() {
        return illProfile;
    }

    public void setIllProfile(String illProfile) {
        this.illProfile = illProfile == null ? null : illProfile.trim();
    }
}