package com.dpc.web.mybatis3.mapper;

import java.util.List;

import com.dpc.utils.PageEntity;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.mybatis3.domain.Doctor;

public interface DoctorMapper{

	List<DoctorVO> getAllDoctorList(PageEntity<DoctorVO> pageEntity);
	Integer getAllDoctorCount(PageEntity<Doctor> pageEntity);
	void addDoctor(Doctor doctor);
	List<Doctor> getDoctorList(Doctor d);
	void updateDoctor(Doctor doctor);
	
}


