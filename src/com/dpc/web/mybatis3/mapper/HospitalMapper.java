package com.dpc.web.mybatis3.mapper;

import com.dpc.web.mybatis3.domain.Hospital;

public interface HospitalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hospital record);

    int insertSelective(Hospital record);

    Hospital selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hospital record);

    int updateByPrimaryKey(Hospital record);
}