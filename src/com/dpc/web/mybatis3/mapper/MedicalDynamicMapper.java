package com.dpc.web.mybatis3.mapper;

import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.MedicalDynamic;

public interface MedicalDynamicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicalDynamic record);

    int insertSelective(MedicalDynamic record);

    MedicalDynamic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicalDynamic record);

    int updateByPrimaryKeyWithBLOBs(MedicalDynamic record);

    int updateByPrimaryKey(MedicalDynamic record);
}