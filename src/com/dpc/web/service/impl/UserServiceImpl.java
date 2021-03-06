package com.dpc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.Base64;
import com.dpc.utils.StringUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.utils.memcached.MemSession;
import com.dpc.web.mybatis3.domain.Admin;
import com.dpc.web.mybatis3.domain.ChatOnline;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.mapper.AdminMapper;
import com.dpc.web.mybatis3.mapper.ChatOnlineMapper;
import com.dpc.web.mybatis3.mapper.UserMapper;
import com.dpc.web.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private ChatOnlineMapper chatOnlineMapper;
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
	public MemSession getSessionByAccessToken(String accessToken){		
		String decodeToken = null;
		try {
			decodeToken = new String(Base64.decode(accessToken.getBytes(), 8));	
		} catch (Exception e) {
			return null;
		}
		if(ValidateUtil.isEmpty(decodeToken)){
			return null;
		}
		String uid = decodeToken.substring(decodeToken.indexOf("_") + 1);
		MemSession session = MemSession.getSession("u" + uid, false,"default");
		if (!StringUtil.isEmpty(session.getMap())){
			String sessionAccessToken = (String) session.getAttribute("accessToken");
			if (sessionAccessToken.equals(decodeToken)){
				return session;
			}
		}		
		return null;
	}

	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	public User getDoctorByDoctorIdentity(String doctorIdentity) {
		return userMapper.getDoctorByDoctorIdentity(doctorIdentity);
	}

	@Override
	public void addAdmin(Admin admin) {
		adminMapper.insertSelective(admin);
		
	}

	@Override
	public Admin getAdmin(Admin admin) {
		
		return adminMapper.getAdmin(admin);
	}

	@Override
	public List<User> getUserList(User u) {
		return userMapper.getUserList(u);
	}

	@Override
	public void chatOnline(ChatOnline chatOnline) {
		chatOnlineMapper.insertSelective(chatOnline);
	}

	@Override
	public List<ChatOnline> getChatOnlineInfo(ChatOnline chatOnline) {
		return chatOnlineMapper.getChatOnlineInfo(chatOnline);
	}
	
	
}
