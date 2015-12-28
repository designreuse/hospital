package com.dpc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.mapper.UserMapper;
import com.dpc.web.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void register(User u) {
		userMapper.addUser(u);
	}

	@Override
	public User getUser(User user) {
		return userMapper.getUser(user);
	}

	@Override
	public void updateUser(User u) {
		userMapper.updateUser(u);
	}
	
	
}
