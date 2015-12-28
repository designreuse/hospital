package com.dpc.web.service;

import java.util.List;

import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceImage;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceRemark;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.HeartCircle;
import com.dpc.web.mybatis3.domain.HeartCircleRemark;

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

	
}