package com.dpc.web.mybatis3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dpc.web.mybatis3.domain.CaseAnalysis;

public interface CaseAnalysisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseAnalysis record);

    int insertSelective(CaseAnalysis record);

    CaseAnalysis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseAnalysis record);

    int updateByPrimaryKeyWithBLOBs(CaseAnalysis record);

    int updateByPrimaryKey(CaseAnalysis record);

    List<CaseAnalysis> getCaseAnalysisListWithPager(@Param("caseAnalysis") CaseAnalysis caseAnalysis, @Param("start") Integer start, @Param("limit") Integer limit);
	Integer getAllCaseAnalysisCount(@Param("caseAnalysis") CaseAnalysis caseAnalysis);

	List<CaseAnalysis> getAllCaseAnalysisList();

	CaseAnalysis getCaseAnalysisDetail(int id);
	
}