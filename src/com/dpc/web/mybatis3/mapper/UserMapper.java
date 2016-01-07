package com.dpc.web.mybatis3.mapper;


import com.dpc.web.mybatis3.domain.User;

public interface UserMapper{

	void addUser(User u);

	User getUser(User user);

	void updateUser(User u);

	User getUserById(Integer id);

	User getDoctorByDoctorIdentity(String doctorIdentity);
	
}


