package com.dpc.web.mybatis3.mapper;

import com.dpc.web.mybatis3.domain.TakeAcademicSupport;

public interface TakeAcademicSupportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TakeAcademicSupport record);

    int insertSelective(TakeAcademicSupport record);

    TakeAcademicSupport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TakeAcademicSupport record);

    int updateByPrimaryKey(TakeAcademicSupport record);

	TakeAcademicSupport getTakeAcademicSupport(TakeAcademicSupport t);
}