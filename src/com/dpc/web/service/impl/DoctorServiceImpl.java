package com.dpc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.DateUtil;
import com.dpc.utils.PageContext;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.VO.PatientVO;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.Announcement;
import com.dpc.web.mybatis3.domain.CaseAnalysis;
import com.dpc.web.mybatis3.domain.CaseAnalysisCollection;
import com.dpc.web.mybatis3.domain.CaseAnalysisRemark;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceImage;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceRemark;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;
import com.dpc.web.mybatis3.domain.ExchangeHistory;
import com.dpc.web.mybatis3.domain.HeartCircle;
import com.dpc.web.mybatis3.domain.HeartCircleImage;
import com.dpc.web.mybatis3.domain.HeartCircleRemark;
import com.dpc.web.mybatis3.domain.TakeAcademicSupport;
import com.dpc.web.mybatis3.mapper.CaseAnalysisCollectionMapper;
import com.dpc.web.mybatis3.mapper.CaseAnalysisMapper;
import com.dpc.web.mybatis3.mapper.CaseAnalysisRemarkMapper;
import com.dpc.web.mybatis3.mapper.DiagnoseExperienceMapper;
import com.dpc.web.mybatis3.mapper.DoctorMapper;
import com.dpc.web.mybatis3.mapper.ExchangeHistoryMapper;
import com.dpc.web.mybatis3.mapper.HeartCircleMapper;
import com.dpc.web.mybatis3.mapper.MedicalDynamicMapper;
import com.dpc.web.mybatis3.mapper.PatientMapper;
import com.dpc.web.mybatis3.mapper.TakeAcademicSupportMapper;
import com.dpc.web.service.IDoctorService;

@Service
@Transactional
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DiagnoseExperienceMapper diagnoseExperienceMapper;
	
	@Autowired
	private HeartCircleMapper heartCircleMapper;
	
	@Autowired
	private TakeAcademicSupportMapper takeAcademicSupportMapper;
	
	@Autowired
	private DoctorMapper doctorMapper;
	
	@Autowired
	private ExchangeHistoryMapper exchangeHistoryMapper;
	
	@Autowired
	private CaseAnalysisMapper caseAnalysisMapper;
	
	@Autowired
	private CaseAnalysisRemarkMapper caseAnalysisRemarkMapper;
	
	@Autowired
	private CaseAnalysisCollectionMapper analysisCollectionMapper;
	
	@Autowired
	private PatientMapper patientMapper;
	
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
		doctorPatientRelation.setBindTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
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

	@Override
	public List<ExchangeHistory> getMyExchangeHistoryList(int userID) {
		// TODO Auto-generated method stub
		return exchangeHistoryMapper.getMyExchangeHistoryList(userID);
	}

	@Override
	public void addExchangeHistory(ExchangeHistory exchangeHistory) {
		exchangeHistoryMapper.insertSelective(exchangeHistory);
	}

	@Override
	public void addCaseAnalysis(CaseAnalysis caseAnalysis) {
		caseAnalysisMapper.insertSelective(caseAnalysis);
	}

	@Override
	public void updateCaseAnalysis(CaseAnalysis caseAnalysis) {
		caseAnalysisMapper.updateByPrimaryKeySelective(caseAnalysis);
	}

	@Override
	public CaseAnalysis getCaseAnalysisById(int id) {
		return caseAnalysisMapper.selectByPrimaryKey(id);
	}

	@Override
	public DoctorVO getDoctorProfile(Integer id) {
		return doctorMapper.getDoctorDetail(id);
	}

	@Override
	public void caseAnalysisRemark(CaseAnalysisRemark analysisRemark) {
	 
		caseAnalysisRemarkMapper.insertSelective(analysisRemark);
	}

	@Override
	public void addCaseAnalysisCollection(CaseAnalysisCollection collection) {
		analysisCollectionMapper.insertSelective(collection);
	}

	@Override
	public List<CaseAnalysisCollection> getCaseAnalysisCollectList(Integer userId) {
		return analysisCollectionMapper.getCaseAnalysisCollectList(userId);
	}

	@Override
	public void delCaseAnalysisCollect(CaseAnalysisCollection collection) {
		analysisCollectionMapper.updateByPrimaryKeySelective(collection);
	}

	@Override
	public boolean hasCollectCaseAnalysis(CaseAnalysisCollection c) {
		
		Integer result = analysisCollectionMapper.getCaseAnalysisCollect(c);
		if(result>0){
			return true;
		}
		return false;
	}

	@Override
	public void delRelation(int id) {
		doctorMapper.delRelation(id);
		
	}

	@Override
	public Integer getBindListToday(int id) {
		return doctorMapper.getBindListToday(id);
	}

	@Override
	public Integer getBindListTotal(int id) {
		return doctorMapper.getBindListTotal(id);
	}

	@Override
	public PatientVO getMyPatientInfo(Integer doctorId, int patient) {
		return doctorMapper.getMyPatientInfo(doctorId,patient);
	}

	@Override
	public List<DoctorPatientRelation> getMyPatients(Integer id) {
		return doctorMapper.getMyPatients(id);
	}

	@Override
	public Integer getDayLiveTotal(int id) {
		return doctorMapper.getDayLiveTotal(id);
	}

	@Override
	public void doctorunBindPatient(Integer doctorId, int patientId) {
		doctorMapper.doctorunBindPatient(doctorId,patientId);
	}

	@Override
	public void updateDoctorPatientRelation(DoctorPatientRelation doctorPatientRelation) {
		patientMapper.updateDoctorPatientRelation(doctorPatientRelation);
		 
	}

	
	
	
}
