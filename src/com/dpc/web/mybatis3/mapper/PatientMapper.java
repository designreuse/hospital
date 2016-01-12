package com.dpc.web.mybatis3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.PatientVO;
import com.dpc.web.VO.WishVO;
import com.dpc.web.mybatis3.domain.Announcement;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;
import com.dpc.web.mybatis3.domain.Patient;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.mybatis3.domain.WishRemark;

public interface PatientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);

	PatientVO getProfile(int id);

	void addWish(Wish wish);

	void addWishRemark(WishRemark wishRemark);

	void updateWish(Wish wish);

	List<WishVO> getWishListByUserId(Integer id);

	List<PatientVO> getPatientListWithPager(@Param("patient") PatientVO patient, @Param("start") Integer start, @Param("limit") Integer limit);
	
	Integer getAllPatientCount(@Param("patient") PatientVO patient);

	Integer hasRelationshipWithDoctor(DoctorPatientRelation doctorPatientRelation);

	Integer hasBindWithDoctor(Integer userId);

	void patientBindDoctor(DoctorPatientRelation doctorPatientRelation);

	List<Wish> getWishList(@Param("wish") Wish wish, @Param("start") Integer start, @Param("limit") Integer limit);

	Integer getAllWishCount(@Param("wish") Wish wish);

	Wish getWishDetail(int id);

	Wish getWishListById(int id);

	List<Discovery> getDiscoveryList(@Param("discovery") Discovery d, @Param("start") Integer start, @Param("limit") Integer limit);

	Integer getAllDiscoveryCount(@Param("discovery") Discovery d);

	Discovery getDiscoveryById(int id);

	void updateDiscovery(Discovery d);

	List<Doctor> getMyDoctors(Integer id);

	Doctor getMyDoctorDetail(int id);

	void unBindRelation(Integer pid, Integer did);

	List<Announcement> getMydoctorAnnouncements(Integer id);

	DoctorPatientRelation getDoctorPatientRelationById(int id);

}