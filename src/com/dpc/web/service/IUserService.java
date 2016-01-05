package com.dpc.web.service;

import com.dpc.utils.memcached.MemSession;
import com.dpc.web.mybatis3.domain.User;

public interface IUserService {

	void register(User u);

	User getUser(User user);

	void updateUser(User u);

	MemSession getSessionByAccessToken(String accessToken);

	User getUserById(Integer id);
	
}
