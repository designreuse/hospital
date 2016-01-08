package com.dpc.web.mybatis3.mapper;

import java.util.List;

import com.dpc.web.mybatis3.domain.County;
import com.dpc.web.mybatis3.domain.DistrictList;

public interface CountyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(County record);

    int insertSelective(County record);

    County selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(County record);

    int updateByPrimaryKey(County record);

	Integer getCountyIdByName(String province, String city, String county);

	DistrictList getunFullIdByName(String province, String city);

	DistrictList getFullIdByName(String province, String city, String county);

	List<County> getCountysByPid(int pid);
}