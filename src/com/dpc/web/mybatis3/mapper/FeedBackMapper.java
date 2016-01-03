package com.dpc.web.mybatis3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.FeedBack;

public interface FeedBackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FeedBack record);

    int insertSelective(FeedBack record);

    FeedBack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FeedBack record);

    int updateByPrimaryKey(FeedBack record);
    
	List<FeedBack> getFeedBackListWithPager(@Param("feedBack") FeedBack feedBack, @Param("start") Integer start, @Param("limit") Integer limit);

	Integer getAllFeedBackCount(@Param("feedBack") FeedBack feedBack);
}