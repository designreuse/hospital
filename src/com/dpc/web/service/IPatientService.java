package com.dpc.web.service;

import java.util.List;

import com.dpc.web.VO.PatientVO;
import com.dpc.web.VO.WishVO;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceImage;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceRemark;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.DiscoveryImage;
import com.dpc.web.mybatis3.domain.DiscoveryRemark;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;
import com.dpc.web.mybatis3.domain.HeartCircle;
import com.dpc.web.mybatis3.domain.HeartCircleRemark;
import com.dpc.web.mybatis3.domain.Patient;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.mybatis3.domain.WishRemark;

public interface IPatientService {

	void addDiscovery(Discovery discovery, List<String> imageUrls);

	void addDiscoveryRemark(DiscoveryRemark discoveryRemark);

	List<Discovery> getDiscoveryList();

	List<DiscoveryImage> getDiscoveryImageListByDiscoveryId(Integer id);

	Discovery getDiscoveryDetail(int parseInt);

	void addPatient(Patient patient);

	PatientVO getProfile(int parseInt);

	void addWish(Wish wish);

	void addWishRemark(WishRemark wishRemark);

	void updateWish(Wish wish);

	List<WishVO> getWishListByUserId(Integer id);

	boolean hasRelationshipWithDoctor(DoctorPatientRelation doctorPatientRelation);

	boolean hasBindWithDoctor(Integer id);

	void patientBindDoctor(DoctorPatientRelation dp);


}
