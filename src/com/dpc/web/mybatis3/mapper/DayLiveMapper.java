package com.dpc.web.mybatis3.mapper;

import com.dpc.web.mybatis3.domain.DayLive;

public interface DayLiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DayLive record);

    int insertSelective(DayLive record);

    DayLive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DayLive record);

    int updateByPrimaryKey(DayLive record);
}