package com.dpc.web.mybatis3.mapper;

import java.util.List;

import com.dpc.web.mybatis3.domain.CaseAnalysisCollection;

public interface CaseAnalysisCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseAnalysisCollection record);

    int insertSelective(CaseAnalysisCollection record);

    CaseAnalysisCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseAnalysisCollection record);

    int updateByPrimaryKey(CaseAnalysisCollection record);

	List<CaseAnalysisCollection> getCaseAnalysisCollectList(Integer userId);

	Integer getCaseAnalysisCollect(CaseAnalysisCollection c);
}