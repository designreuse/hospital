package com.dpc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.ConstantUtil;
import com.dpc.utils.PageContext;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.VO.DoctorVO;
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
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.mybatis3.domain.WishRemark;
import com.dpc.web.mybatis3.mapper.ArticleRemarkMapper;
import com.dpc.web.mybatis3.mapper.DayLiveMapper;
import com.dpc.web.mybatis3.mapper.DiscoveryMapper;
import com.dpc.web.mybatis3.mapper.DoctorMapper;
import com.dpc.web.mybatis3.mapper.PatientMapper;
import com.dpc.web.mybatis3.mapper.UserMapper;
import com.dpc.web.service.IPatientService;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private DiscoveryMapper discoveryMapper;
	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private ArticleRemarkMapper articleRemarkMapper;
	@Autowired
	private DayLiveMapper dayLiveMapper;
	
	@Override
	public void addDiscovery(Discovery discovery, List<String> imageUrls) {
		discoveryMapper.insertSelective(discovery);
		Integer discoveryId = discovery.getId();
		if(imageUrls!=null&&imageUrls.size()>0){
			for(String url : imageUrls){
				DiscoveryImage discoveryImage = new DiscoveryImage();
				discoveryImage.setDelFlag(0);
				discoveryImage.setDiscoveryId(discoveryId);
				discoveryImage.setImageUrl(url);
				discoveryMapper.addDiscoveryImage(discoveryImage);
			}
		}
	}

	@Override
	public void addDiscoveryRemark(DiscoveryRemark discoveryRemark) {
		discoveryMapper.addDiscoveryRemark(discoveryRemark);
		
	}

	@Override
	public List<Discovery> getDiscoveryList() {
		return discoveryMapper.getDiscoveryList();
	}

	@Override
	public List<DiscoveryImage> getDiscoveryImageListByDiscoveryId(Integer id) {
		return discoveryMapper.getDiscoveryImageListByDiscoveryId(id);
	}

	@Override
	public Discovery getDiscoveryDetail(int id) {
		return discoveryMapper.getDiscoveryDetail(id);
	}

	@Override
	public void addPatient(Patient patient) {
		patientMapper.insertSelective(patient);
	}

	@Override
	public PatientVO getProfile(int id) {
		return patientMapper.getProfile(id);
	}

	@Override
	public void addWish(Wish wish) {
		patientMapper.addWish(wish);
	}

	@Override
	public void addWishRemark(WishRemark wishRemark) {
		patientMapper.addWishRemark(wishRemark);
	}

	@Override
	public void updateWish(Wish wish) {
		patientMapper.updateWish(wish);
	}

	@Override
	public List<WishVO> getWishListByUserId(Integer id) {
		return patientMapper.getWishListByUserId(id);
	}

	@Override
	public boolean hasRelationshipWithDoctor(DoctorPatientRelation doctorPatientRelation) {
		Integer count = patientMapper.hasRelationshipWithDoctor(doctorPatientRelation);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean hasBindWithDoctor(Integer userId) {
		Integer count = patientMapper.hasBindWithDoctor(userId);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void patientBindDoctor(DoctorPatientRelation dp) {
		if(dp.getDirection()==1){
			//患者验证信息已发送。
			dp.setChecked(0);
		}else{
			//医生绑定患者
			dp.setChecked(1);
		}
		patientMapper.patientBindDoctor(dp);
	}

	@Override
	public Wish getWishListById(int id) {
		// TODO Auto-generated method stub
		return patientMapper.getWishListById(id);
	}

	@Override
	public void updatePatient(Patient patient) {
		patientMapper.updateByPrimaryKeySelective(patient);
	}

	@Override
	public Discovery getDiscoveryById(int id) {
		return patientMapper.getDiscoveryById(id);
	}

	@Override
	public void updateDiscovery(Discovery d) {
		patientMapper.updateDiscovery(d);
	}

	@Override
	public List<Doctor> getMyDoctors(Integer id) {
		return patientMapper.getMyDoctors(id);
	}

	@Override
	public Doctor getMyDoctorDetail(int id) {
		return patientMapper.getMyDoctorDetail(id);
	}

	@Override
	public void unBindRelation(Integer pid, Integer did) {
		patientMapper.unBindRelation(pid,did);
	}

	@Override
	public List<Announcement> getMydoctorAnnouncements(Integer id) {
		return patientMapper.getMydoctorAnnouncements(id);
	}

	@Override
	public DoctorPatientRelation getDoctorPatientRelationById(int id) {
		return patientMapper.getDoctorPatientRelationById(id);
	}

	@Override
	public Pager<Discovery> findDiscoveryByPaginaton(Discovery d) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		d.setStart(start);
		d.setLimit(limit);
		List<Discovery> datas = discoveryMapper.findDiscoveryByPaginaton(d,start,limit);
		if(datas!=null&&datas.size()>0){
			for(Discovery dis : datas){
				if(dis.getImageList()!=null&&dis.getImageList().size()>0){
					Integer len = dis.getImageList().size();
					List<DiscoveryImage> dim = new ArrayList<DiscoveryImage>();
					DiscoveryImage di = dis.getImageList().get(len-1);
					if(!ValidateUtil.isEmpty(di.getImageUrl())){
						di.setImageUrl(ConstantUtil.DOMAIN+di.getImageUrl());
					}
					dim.add(di);
					dis.setImageList(dim);
				}
			}
		}
		Integer totalCount = discoveryMapper.findDiscoveryTotal(d);
		Pager<Discovery> pager = new Pager<Discovery>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;	
	}

	@Override
	public Discovery getDiscoveryDetailById(int id) {
		return discoveryMapper.getDiscoveryImageDetail(id);
	}

	@Override
	public void addBackPost(Discovery d, List<String> imageUrls) {
		discoveryMapper.insertSelective(d);
		if(imageUrls!=null&&imageUrls.size()>0){
			for(String s : imageUrls){
				DiscoveryImage discoveryImage = new DiscoveryImage();
				discoveryImage.setDelFlag(0);
				discoveryImage.setDiscoveryId(d.getId());
				discoveryImage.setImageUrl(s);
				discoveryMapper.addDiscoveryImage(discoveryImage);
			}
		}
	}

	@Override
	public List<PatientVO> getAllPatient() {
		return patientMapper.getAllPatient();
	}

	@Override
	public List<DoctorPatientRelation> getBindDoctors(Integer id) {
		return patientMapper.getBindDoctors(id);
	}

	@Override
	public void addDayLive(DayLive dayLive) {
		dayLiveMapper.insertSelective(dayLive);
	}

	@Override
	public Patient getPatientById(Integer id) {
		return patientMapper.getPatientById(id);
	}

	
}
