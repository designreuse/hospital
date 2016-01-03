package com.dpc.web.mybatis3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dpc.utils.PageEntity;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.Announcement;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;

public interface DoctorMapper{

	Integer getAllDoctorCount(PageEntity<Doctor> pageEntity);
	void addDoctor(Doctor doctor);
	List<Doctor> getDoctorList(Doctor d);
	void updateDoctor(Doctor doctor);
	void addAnnouncement(Announcement announcement);
	void delAnnouncement(Announcement announcement);
	List<Announcement> getAnnouncementByDoctorId(int id);
	
	List<DoctorVO> getDoctorListWithPager(@Param("doctor") DoctorVO doctor, @Param("start") Integer start, @Param("limit") Integer limit);
	Integer getAllDoctorCount(@Param("doctor") DoctorVO doctor);
	DoctorVO getDoctorDetail(@Param("id") Integer id);
	List<DiagnoseExperience> getDiagnoseExperienceListWithPager(@Param("dia") DiagnoseExperience dia, @Param("start") Integer start, @Param("limit") Integer limit);
	Integer getAllDiagnoseExperienceCount(@Param("dia") DiagnoseExperience dia);
	DiagnoseExperience getDiaExpDetail(String id);
	void addAcademicSupport(AcademicSupport academicSupport);
	List<AcademicSupport> getAcademicSupportListWithPager(@Param("support") AcademicSupport support, @Param("start") Integer start, @Param("limit") Integer limit);
	Integer getAllAcademicSupportCount(@Param("support") AcademicSupport support);
	List<DoctorPatientRelation> getBindList(Integer id);
	void bindAcceptOrNot(DoctorPatientRelation doctorPatientRelation);
	List<DoctorVO> getAuthenticationListWithPager(@Param("doctor") DoctorVO doctor, @Param("start") Integer start, @Param("limit") Integer limit);
	Integer getAllAuthenticationCount(@Param("doctor") DoctorVO doctor);
	List<DoctorVO> getDoctorListExport();
	
}


