package com.dpc.web.service;

import java.util.List;

import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.CaseAnalysis;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;

public interface IBackDoctorService {

	Pager<DoctorVO> findByPaginaton(DoctorVO doctor);

	DoctorVO getDoctorDetail(int parseInt);

	Pager<DiagnoseExperience> findDiagnoseExperienceByPaginaton(DiagnoseExperience dia);

	DiagnoseExperience getDiaExpDetail(String id);

	void addAcademicSupport(AcademicSupport academicSupport);

	Pager<AcademicSupport> findAcademicSupportByPaginaton(AcademicSupport support);

	Pager<DoctorVO> findAuthenticationByPaginaton(DoctorVO doctor);

	AcademicSupport getAcademicSupportDetail(int parseInt);

	void delAcademicSupport(int id);

	Pager<CaseAnalysis> findCaseAnalysisByPaginaton(CaseAnalysis caseAnalysis);

	List<CaseAnalysis> getCaseAnalysisList();

	CaseAnalysis getCaseAnalysisDetail(int parseInt);

	void updateAcademicSupport(AcademicSupport academicSupport);
	
}
