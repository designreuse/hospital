package com.dpc.web.mybatis3.domain;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User implements Serializable{
	
	private Integer id;
	private String username;//登录用户名
	private String password;//密码
	private String profileImageUrl;//用户头像
	private String mobile;//手机号码
	private String name;//姓名
	private Integer agender;//性别
	private String birthday;//出生日期
	private Integer age;//年龄
	private String registerTime;//注册时间
	private String salt;//颜值
	private Integer registerType;//颜值
	
	
	public Integer getRegisterType() {
		return registerType;
	}
	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAgender() {
		return agender;
	}
	public void setAgender(Integer agender) {
		this.agender = agender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
}