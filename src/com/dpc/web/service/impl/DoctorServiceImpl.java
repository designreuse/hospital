package com.dpc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.PageContext;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.Announcement;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceImage;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceRemark;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;
import com.dpc.web.mybatis3.domain.HeartCircle;
import com.dpc.web.mybatis3.domain.HeartCircleImage;
import com.dpc.web.mybatis3.domain.HeartCircleRemark;
import com.dpc.web.mybatis3.domain.TakeAcademicSupport;
import com.dpc.web.mybatis3.mapper.DiagnoseExperienceMapper;
import com.dpc.web.mybatis3.mapper.DoctorMapper;
import com.dpc.web.mybatis3.mapper.HeartCircleMapper;
import com.dpc.web.mybatis3.mapper.MedicalDynamicMapper;
import com.dpc.web.mybatis3.mapper.TakeAcademicSupportMapper;
import com.dpc.web.service.IDoctorService;

@Service
@Transactional
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private MedicalDynamicMapper medicalDynamicMapper;
	
	@Autowired
	private DiagnoseExperienceMapper diagnoseExperienceMapper;
	
	@Autowired
	private HeartCircleMapper heartCircleMapper;
	
	@Autowired
	private TakeAcademicSupportMapper takeAcademicSupportMapper;
	
	
	@Autowired
	private DoctorMapper doctorMapper;
	
	@Override
	public void addDoctorWithRegister(Doctor doctor) {
		doctorMapper.addDoctor(doctor);
	}

	@Override
	public DiagnoseExperience getDiagnoseExperienceDetail(int id) {
		return diagnoseExperienceMapper.getDiagnoseExperienceDetail(id);
	}

	@Override
	public void addDiagnoseExperienceRemark(DiagnoseExperienceRemark remark) {
		diagnoseExperienceMapper.addDiagnoseExperienceRemark(remark);
	}

	@Override
	public void addDiagnoseExperience(DiagnoseExperience diagnoseExperience, List<String> imageUrls) {
		diagnoseExperienceMapper.insertSelective(diagnoseExperience);
		Integer id = diagnoseExperience.getId();
		if(imageUrls!=null&&imageUrls.size()>0){
			for(String imageUrl : imageUrls){
				DiagnoseExperienceImage image = new DiagnoseExperienceImage();
				image.setDiaExpId(id);
				image.setImageUrl(imageUrl);
				diagnoseExperienceMapper.addDiagnoseExperienceImage(image);
			}
		}
	}

	@Override
	public List<DiagnoseExperienceImage> getDiagnoseExperienceImageByDiaExpId(int id) {
		return diagnoseExperienceMapper.getDiagnoseExperienceImageByDiaExpId(id);
	}

	@Override
	public List<DiagnoseExperience> getDiagnoseExperienceList() {
		return diagnoseExperienceMapper.getDiagnoseExperienceList();
	}

	@Override
	public List<Doctor> getDoctorList(Doctor d) {
		return doctorMapper.getDoctorList(d);
	}

	@Override
	public void addHeartCircle(HeartCircle circle, List<String> imageUrls) {
		heartCircleMapper.insertSelective(circle);
		Integer id = circle.getId();
		if(imageUrls!=null&&imageUrls.size()>0){
			for(String imageUrl : imageUrls){
				HeartCircleImage image = new HeartCircleImage();
				image.setHeartCircleId(id);
				image.setImageUrl(imageUrl);
				image.setDelFlag(0);
				heartCircleMapper.addHeartCircleImage(image);
			}
		}
	}

	@Override
	public HeartCircle getHeartCircleDetail(int id) {
		return heartCircleMapper.getHeartCircleDetail(id);
	}

	@Override
	public void addHeartCircleRemark(HeartCircleRemark heartCircleRemark) {
		heartCircleMapper.addHeartCircleRemark(heartCircleRemark);
	}

	@Override
	public List<HeartCircleRemark> getHeartCircleRemarkList() {
		return heartCircleMapper.getHeartCircleRemarkList();
	}

	@Override
	public List<HeartCircle> getHeartCircleList() {
		return heartCircleMapper.getHeartCircleList();
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		doctorMapper.updateDoctor(doctor);
		
	}

	@Override
	public void addAnnouncement(Announcement announcement) {
		doctorMapper.addAnnouncement(announcement);
	}

	@Override
	public void updateAnnouncement(Announcement announcement) {
		doctorMapper.delAnnouncement(announcement);
	}

	@Override
	public List<Announcement> getAnnouncementListByDoctorId(int id) {
		return doctorMapper.getAnnouncementByDoctorId(id);
	}

	@Override
	public List<DoctorPatientRelation> getBindList(Integer id) {
		return doctorMapper.getBindList(id);
	}

	@Override
	public void bindAcceptOrNot(int id, int acceptOrNot) {
		DoctorPatientRelation doctorPatientRelation = new DoctorPatientRelation();
		doctorPatientRelation.setId(id);
		doctorPatientRelation.setAcceptOrNot(acceptOrNot);
		doctorMapper.bindAcceptOrNot(doctorPatientRelation);
	}

	@Override
	public List<DoctorVO> getAllDoctorList() {
		return doctorMapper.getDoctorListExport();
	}

	@Override
	public void updateDiagnoseExp(DiagnoseExperience diaexp) {
		diagnoseExperienceMapper.updateByPrimaryKeySelective(diaexp);
	}

	@Override
	public DiagnoseExperience getDiagnoseExperienceById(int id) {
		
		return diagnoseExperienceMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<HeartCircleImage> getHeartCircleImageListByHeartCircleId(int hcId) {
		return heartCircleMapper.getHeartCircleImageListByHeartCircleId(hcId);
	}

	@Override
	public HeartCircle getHeartCircleById(int id) {
		return heartCircleMapper.getHeartCircleById(id);
	}

	@Override
	public void updateHeartCircle(HeartCircle circle) {
		heartCircleMapper.updateByPrimaryKeySelective(circle);
	}

	@Override
	public Doctor getDoctorById(Integer id) {
		return doctorMapper.getDoctorById(id);
	}

	@Override
	public List<AcademicSupport> getAcademicSupportList() {
		return doctorMapper.getAcademicSupportList();
	}

	@Override
	public AcademicSupport getAcademicSupportDetail(int id) {
		return doctorMapper.getAcademicSupportDetail(id);
	}

	@Override
	public void takePartActivity(TakeAcademicSupport takeAcademicSupport) {
		takeAcademicSupportMapper.insertSelective(takeAcademicSupport);
		
	}

	@Override
	public TakeAcademicSupport getTakeAcademicSupport(TakeAcademicSupport t) {
		return takeAcademicSupportMapper.getTakeAcademicSupport(t);
	}

	@Override
	public Pager<HeartCircle> findHeartCircleByPaginaton(HeartCircle h) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		h.setStart(start);
		h.setLimit(limit);
		List<HeartCircle> datas = heartCircleMapper.findHeartCircleByPaginaton(h,start,limit);
		Integer totalCount = heartCircleMapper.getHeartCircleCount(h);
		Pager<HeartCircle> pager = new Pager<HeartCircle>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;	
	}

	@Override
	public HeartCircle getHeartCircleDetailById(int id) {
		return heartCircleMapper.getHeartCircleImageDetail(id);
	}
	
}
