package com.dpc.web.service;

import java.util.List;

import com.dpc.utils.memcached.MemSession;
import com.dpc.web.mybatis3.domain.Admin;
import com.dpc.web.mybatis3.domain.User;

public interface IUserService {

	void register(User u);

	User getUser(User user);

	void updateUser(User u);

	MemSession getSessionByAccessToken(String accessToken);

	User getUserById(Integer id);

	User getDoctorByDoctorIdentity(String doctorIdentity);

	void addAdmin(Admin admin);

	Admin getAdmin(Admin admin);

	List<User> getUserList(User u);
	
}
