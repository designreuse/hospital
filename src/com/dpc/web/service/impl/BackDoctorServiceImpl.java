package com.dpc.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.ConstantUtil;
import com.dpc.utils.PageContext;
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.mapper.DoctorMapper;
import com.dpc.web.service.IBackDoctorService;

@Service
@Transactional
public class BackDoctorServiceImpl implements IBackDoctorService {

	@Autowired
	private DoctorMapper doctorMapper ;

	@Override
	public Pager<DoctorVO> findByPaginaton(DoctorVO doctor) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		doctor.setStart(start);
		doctor.setLimit(limit);
		List<DoctorVO> datas = doctorMapper.getDoctorListWithPager(doctor,start,limit);
		if(datas!=null&&datas.size()>0){
			for(DoctorVO d : datas){
				
			}
		}
		Integer totalCount = doctorMapper.getAllDoctorCount(doctor);
		Pager<DoctorVO> pager = new Pager<DoctorVO>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;
	}

	@Override
	public DoctorVO getDoctorDetail(int id) {
		return doctorMapper.getDoctorDetail(id);
	}

	@Override
	public Pager<DiagnoseExperience> findDiagnoseExperienceByPaginaton(DiagnoseExperience dia) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		dia.setStart(start);
		dia.setLimit(limit);
		List<DiagnoseExperience> datas = doctorMapper.getDiagnoseExperienceListWithPager(dia,start,limit);
		Integer totalCount = doctorMapper.getAllDiagnoseExperienceCount(dia);
		Pager<DiagnoseExperience> pager = new Pager<DiagnoseExperience>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;
	}

	@Override
	public DiagnoseExperience getDiaExpDetail(String id) {
		return doctorMapper.getDiaExpDetail(id);
	}

	@Override
	public void addAcademicSupport(AcademicSupport academicSupport) {
		doctorMapper.addAcademicSupport(academicSupport);
	}

	@Override
	public Pager<AcademicSupport> findAcademicSupportByPaginaton(AcademicSupport support) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		support.setStart(start);
		support.setLimit(limit);
		List<AcademicSupport> datas = doctorMapper.getAcademicSupportListWithPager(support,start,limit);
		Integer totalCount = doctorMapper.getAllAcademicSupportCount(support);
		Pager<AcademicSupport> pager = new Pager<AcademicSupport>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;
	}

	@Override
	public Pager<DoctorVO> findAuthenticationByPaginaton(DoctorVO doctor) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		doctor.setStart(start);
		doctor.setLimit(limit);
		List<DoctorVO> datas = doctorMapper.getAuthenticationListWithPager(doctor,start,limit);
		Integer totalCount = doctorMapper.getAllAuthenticationCount(doctor);
		Pager<DoctorVO> pager = new Pager<DoctorVO>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;
	}

	@Override
	public AcademicSupport getAcademicSupportDetail(int id) {
		return doctorMapper.getAcademicSupportDetail(id);
	}

	@Override
	public void delAcademicSupport(int id) {
		doctorMapper.delAcademicSupport(id);
		
	}
	
}
