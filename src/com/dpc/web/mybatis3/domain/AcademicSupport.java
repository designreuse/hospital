package com.dpc.web.mybatis3.domain;


import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AcademicSupport {
    private Integer id;

    private String title;

    private String content;

    private Integer type;

    private Integer score;

    private String promoteImage;

    private String creTime;

    private Integer delFlag;
    
    private Integer userScore;//跳转活动详情，显示当前用户积分
    private Integer start;
    private Integer limit;
    private Integer totalTakePart;
    
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}
	public Integer getUserScore() {
		return userScore;
	}

	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTotalTakePart() {
		return totalTakePart;
	}

	public void setTotalTakePart(Integer totalTakePart) {
		this.totalTakePart = totalTakePart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getPromoteImage() {
		return promoteImage;
	}

	public void setPromoteImage(String promoteImage) {
		this.promoteImage = promoteImage;
	}

	public String getCreTime() {
		return creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}