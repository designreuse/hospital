package com.dpc.web.mybatis3.mapper;

import java.util.List;

import com.dpc.web.mybatis3.domain.ChatOnline;

public interface ChatOnlineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChatOnline record);

    int insertSelective(ChatOnline record);

    ChatOnline selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatOnline record);

    int updateByPrimaryKey(ChatOnline record);

	List<ChatOnline> getChatOnlineInfo(ChatOnline chatOnline);
}