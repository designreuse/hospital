package com.dpc.web.VO;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DoctorVO implements Serializable{
	
	private Integer id;
	private String username;
	private String mobile;
	private String agender;
	private String birthday;
	private String name;
	private String hospital;//医生所在医院
	private Integer score;//医生积分
	private String department;//科室
	private String address;//所在地
	private String technicalTitle;//技术职称
	private String teachingTitle;//技术职称
	private Integer verifyed;//医生是否已经认证：0未认证，1已认证
	private String registerTime;
	private String crtWithPhotoUrl;
	private String crtWithNameUrl;
	private String startDate;
    private String endDate;
    private Integer start;
    private Integer limit;
    private Integer exportRowCount;
    private String crtOperTime;//认证操作时间
    private Integer startScore;
    private Integer endScore;
	    
    
	public String getCrtOperTime() {
		return crtOperTime;
	}
	public void setCrtOperTime(String crtOperTime) {
		this.crtOperTime = crtOperTime;
	}
	public Integer getExportRowCount() {
		return exportRowCount;
	}
	public void setExportRowCount(Integer exportRowCount) {
		this.exportRowCount = exportRowCount;
	}
	public Integer getStartScore() {
		return startScore;
	}
	public void setStartScore(Integer startScore) {
		this.startScore = startScore;
	}
	public Integer getEndScore() {
		return endScore;
	}
	public void setEndScore(Integer endScore) {
		this.endScore = endScore;
	}
	public String getStartDate() {
			return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTechnicalTitle() {
		return technicalTitle;
	}
	public void setTechnicalTitle(String technicalTitle) {
		this.technicalTitle = technicalTitle;
	}
	public Integer getVerifyed() {
		return verifyed;
	}
	public void setVerifyed(Integer verifyed) {
		this.verifyed = verifyed;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAgender() {
		return agender;
	}
	public void setAgender(String agender) {
		this.agender = agender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTeachingTitle() {
		return teachingTitle;
	}
	public void setTeachingTitle(String teachingTitle) {
		this.teachingTitle = teachingTitle;
	}
	public String getCrtWithPhotoUrl() {
		return crtWithPhotoUrl;
	}
	public void setCrtWithPhotoUrl(String crtWithPhotoUrl) {
		this.crtWithPhotoUrl = crtWithPhotoUrl;
	}
	public String getCrtWithNameUrl() {
		return crtWithNameUrl;
	}
	public void setCrtWithNameUrl(String crtWithNameUrl) {
		this.crtWithNameUrl = crtWithNameUrl;
	}
	
	
}