package com.dpc.web.service;

import java.util.List;

import com.dpc.web.VO.Pager;
import com.dpc.web.VO.PatientVO;
import com.dpc.web.VO.WishVO;
import com.dpc.web.mybatis3.domain.Announcement;
import com.dpc.web.mybatis3.domain.ArticleRemark;
import com.dpc.web.mybatis3.domain.DayLive;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.DiscoveryImage;
import com.dpc.web.mybatis3.domain.DiscoveryRemark;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;
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

	Wish getWishListById(int parseInt);

	void updatePatient(Patient patient);

	Discovery getDiscoveryById(int parseInt);

	void updateDiscovery(Discovery d);

	List<Doctor> getMyDoctors(Integer id);

	Doctor getMyDoctorDetail(int id);

	void unBindRelation(Integer pid, Integer did);

	List<Announcement> getMydoctorAnnouncements(Integer id);

	DoctorPatientRelation getDoctorPatientRelationById(int parseInt);

	Pager<Discovery> findDiscoveryByPaginaton(Discovery d);

	Discovery getDiscoveryDetailById(int parseInt);

	void addBackPost(Discovery d, List<String> imageUrls);

	List<PatientVO> getAllPatient();

	List<DoctorPatientRelation> getBindDoctors(Integer id);

	void addDayLive(DayLive dayLive);

	Patient getPatientById(Integer id);


}
