package com.dpc.web.mybatis3.domain;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CaseAnalysisCollection {
    private Integer id;

    private Integer userId;

    private Integer caseId;

    private Integer delFlag;
    
    private String collectTime;

    private List<CaseAnalysis> caseAnalysisList;
    
    public List<CaseAnalysis> getCaseAnalysisList() {
		return caseAnalysisList;
	}

	public void setCaseAnalysisList(List<CaseAnalysis> caseAnalysisList) {
		this.caseAnalysisList = caseAnalysisList;
	}

	public Integer getId() {
        return id;
    }

    public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}