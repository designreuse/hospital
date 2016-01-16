package com.dpc.web.mybatis3.domain;


import java.util.Date;

public class ExchangeHistory {
    private Integer id;

    private Integer doctorId;

    private String exchageTime;

    private Integer score;

    private String money;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getExchageTime() {
		return exchageTime;
	}

	public void setExchageTime(String exchageTime) {
		this.exchageTime = exchageTime;
	}

	public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}