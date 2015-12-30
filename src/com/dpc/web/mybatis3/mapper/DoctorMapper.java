package com.dpc.web.mybatis3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dpc.utils.PageEntity;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.mybatis3.domain.Announcement;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.Doctor;

public interface DoctorMapper{

	Integer getAllDoctorCount(PageEntity<Doctor> pageEntity);
	void addDoctor(Doctor doctor);
	List<Doctor> getDoctorList(Doctor d);
	void updateDoctor(Doctor doctor);
	void addAnnouncement(Announcement announcement);
	void delAnnouncement(Announcement announcement);
	List<Announcement> getAnnouncementByDoctorId(int id);
	
	List<DoctorVO> getDoctorListWithPager(@Param("doctor") DoctorVO doctor, @Param("start") Integer start, @Param("limit") Integer limit);
	Integer getAllDoctorCount(DoctorVO doctor);
	
}


