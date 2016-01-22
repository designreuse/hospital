package com.dpc.web.mybatis3.domain;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Doctor implements Serializable{
	private Integer id;
	private Integer userId;//用户ID-指向t_user表的ID
	private String hospital;//医生所在医院
	private Integer score;//医生积分
	private String department;//科室
	private String address;//所在地
	private String technicalTitle;//技术职称
	private String teachingTitle;//教学职称
	private Integer verifyed;//医生是否已经认证：0未认证，1已认证
	private String crtWithPhotoUrl;//认证证件有照片的页
	private String crtWithNameUrl;//认证证件有姓名的页
	private String qrCodeUrl;//认证证件有姓名的页
	private Integer totalPatient;//名下的患者数量
	private String crtOperTime;//认证操作时间
	private String doctorIdentity;
	private String doctorIdentityPlain;
	private Integer patientCount;
	private Integer dayScore;
	
	//非持久化字段
	private String username;
	private String mobile;
	private String name;
	private String registerTime;
	private String profileImageUrl;
	private String relation;//患者与该医生的关系
	
	
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	public Integer getDayScore() {
		return dayScore;
	}
	public void setDayScore(Integer dayScore) {
		this.dayScore = dayScore;
	}
	public Integer getPatientCount() {
		return patientCount;
	}
	public void setPatientCount(Integer patientCount) {
		this.patientCount = patientCount;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
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
	
	public String getDoctorIdentity() {
		return doctorIdentity;
	}
	public void setDoctorIdentity(String doctorIdentity) {
		this.doctorIdentity = doctorIdentity;
	}
	public String getDoctorIdentityPlain() {
		return doctorIdentityPlain;
	}
	public void setDoctorIdentityPlain(String doctorIdentityPlain) {
		this.doctorIdentityPlain = doctorIdentityPlain;
	}
	public String getCrtOperTime() {
		return crtOperTime;
	}
	public void setCrtOperTime(String crtOperTime) {
		this.crtOperTime = crtOperTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getTeachingTitle() {
		return teachingTitle;
	}
	public void setTeachingTitle(String teachingTitle) {
		this.teachingTitle = teachingTitle;
	}
	public Integer getVerifyed() {
		return verifyed;
	}
	public void setVerifyed(Integer verifyed) {
		this.verifyed = verifyed;
	}
	public Integer getTotalPatient() {
		return totalPatient;
	}
	public void setTotalPatient(Integer totalPatient) {
		this.totalPatient = totalPatient;
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
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
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