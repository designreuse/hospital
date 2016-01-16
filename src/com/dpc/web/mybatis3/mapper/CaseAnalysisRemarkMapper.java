package com.dpc.web.mybatis3.mapper;

import com.dpc.web.mybatis3.domain.CaseAnalysisRemark;

public interface CaseAnalysisRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseAnalysisRemark record);

    int insertSelective(CaseAnalysisRemark record);

    CaseAnalysisRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseAnalysisRemark record);

    int updateByPrimaryKey(CaseAnalysisRemark record);
}