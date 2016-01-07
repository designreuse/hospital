package com.dpc.web.mybatis3.mapper;

import com.dpc.web.mybatis3.domain.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

	Admin getAdmin(Admin admin);
}