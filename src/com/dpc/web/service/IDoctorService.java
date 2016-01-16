package com.dpc.web.service;

import java.util.List;

import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.Announcement;
import com.dpc.web.mybatis3.domain.CaseAnalysis;
import com.dpc.web.mybatis3.domain.CaseAnalysisRemark;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceImage;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceRemark;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;
import com.dpc.web.mybatis3.domain.ExchangeHistory;
import com.dpc.web.mybatis3.domain.HeartCircle;
import com.dpc.web.mybatis3.domain.HeartCircleImage;
import com.dpc.web.mybatis3.domain.HeartCircleRemark;
import com.dpc.web.mybatis3.domain.TakeAcademicSupport;

public interface IDoctorService {

	void addDoctorWithRegister(Doctor doctor);

	void addDiagnoseExperience(DiagnoseExperience diagnoseExperience, List<String> imageUrls);

	DiagnoseExperience getDiagnoseExperienceDetail(int parseInt);

	void addDiagnoseExperienceRemark(DiagnoseExperienceRemark remark);

	List<DiagnoseExperienceImage> getDiagnoseExperienceImageByDiaExpId(int parseInt);

	List<DiagnoseExperience> getDiagnoseExperienceList();

	List<Doctor> getDoctorList(Doctor d);

	void addHeartCircle(HeartCircle circle, List<String> imageUrls);

	HeartCircle getHeartCircleDetail(int parseInt);

	void addHeartCircleRemark(HeartCircleRemark heartCircleRemark);

	List<HeartCircleRemark> getHeartCircleRemarkList();

	List<HeartCircle> getHeartCircleList();

	void updateDoctor(Doctor doctor);

	void addAnnouncement(Announcement announcement);

	void updateAnnouncement(Announcement announcement);

	List<Announcement> getAnnouncementListByDoctorId(int parseInt);

	List<DoctorPatientRelation> getBindList(Integer id);

	void bindAcceptOrNot(int parseInt, int parseInt2);

	List<DoctorVO> getAllDoctorList();

	void updateDiagnoseExp(DiagnoseExperience diaexp);

	DiagnoseExperience getDiagnoseExperienceById(int id);

	List<HeartCircleImage> getHeartCircleImageListByHeartCircleId(int parseInt);

	HeartCircle getHeartCircleById(int id);

	void updateHeartCircle(HeartCircle circle);

	Doctor getDoctorById(Integer id);

	List<AcademicSupport> getAcademicSupportList();

	AcademicSupport getAcademicSupportDetail(int id);

	void takePartActivity(TakeAcademicSupport takeAcademicSupport);

	TakeAcademicSupport getTakeAcademicSupport(TakeAcademicSupport t);

	Pager<HeartCircle> findHeartCircleByPaginaton(HeartCircle h);

	HeartCircle getHeartCircleDetailById(int parseInt);

	List<ExchangeHistory> getMyExchangeHistoryList(int parseInt);

	void addExchangeHistory(ExchangeHistory exchangeHistory);

	void addCaseAnalysis(CaseAnalysis caseAnalysis);

	void updateCaseAnalysis(CaseAnalysis caseAnalysis);

	CaseAnalysis getCaseAnalysisById(int parseInt);

	DoctorVO getDoctorProfile(Integer id);

	void caseAnalysisRemark(CaseAnalysisRemark analysisRemark);

}
