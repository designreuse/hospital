package com.dpc.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.ConstantUtil;
import com.dpc.utils.PageContext;
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.CaseAnalysis;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.mapper.CaseAnalysisMapper;
import com.dpc.web.mybatis3.mapper.CityMapper;
import com.dpc.web.mybatis3.mapper.CountyMapper;
import com.dpc.web.mybatis3.mapper.DoctorMapper;
import com.dpc.web.mybatis3.mapper.HospitalMapper;
import com.dpc.web.mybatis3.mapper.ProvinceMapper;
import com.dpc.web.service.IBackDoctorService;

@Service
@Transactional
public class BackDoctorServiceImpl implements IBackDoctorService {

	@Autowired
	private DoctorMapper doctorMapper;
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CountyMapper countyMapper;
	@Autowired
	private CaseAnalysisMapper caseAnalysisMapper;
	

	@Override
	public Pager<DoctorVO> findByPaginaton(DoctorVO doctor) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		doctor.setStart(start);
		doctor.setLimit(limit);
		List<DoctorVO> datas = doctorMapper.getDoctorListWithPager(doctor,start,limit);
		if(datas!=null&&datas.size()>0){
			for(DoctorVO d : datas){
				String province = "";
				String city = "";
				String county = "";
				if(!ValidateUtil.isEmpty(d.getAddress())){
					String[] addressArr = d.getAddress().split("-");
					if(addressArr!=null&&addressArr.length>0){
						for(int i=0;i<addressArr.length;i++){
							if(i==0){
								 province = provinceMapper.selectByPrimaryKey(Integer.parseInt(addressArr[0])).getName();
							}
							if(i==1){
								 city = cityMapper.selectByPrimaryKey(Integer.parseInt(addressArr[1])).getName();
							}
							if(i==2){
								 county = countyMapper.selectByPrimaryKey(Integer.parseInt(addressArr[2])).getName();
							}
						}
					}
				}
				StringBuffer sb = new StringBuffer();
				if(!ValidateUtil.isEmpty(province)){
					sb.append(province).append(" ");
				}
				if(!ValidateUtil.isEmpty(city)){
					sb.append(city).append(" ");
				}
				if(!ValidateUtil.isEmpty(county)){
					sb.append(county);
				}
				if(!ValidateUtil.isEmpty(sb.toString())){
					d.setAddress(sb.toString());
				}
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

	@Override
	public Pager<CaseAnalysis> findCaseAnalysisByPaginaton(CaseAnalysis caseAnalysis) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		caseAnalysis.setStart(start);
		caseAnalysis.setLimit(limit);
		List<CaseAnalysis> datas = caseAnalysisMapper.getCaseAnalysisListWithPager(caseAnalysis,start,limit);
		if(datas!=null&&datas.size()>0){
			for(CaseAnalysis a: datas){
				if(!ValidateUtil.isEmpty(a.getIllCaseImage())){
					a.setIllCaseImage(ConstantUtil.DOMAIN+a.getIllCaseImage());
				}
			}
		}
		Integer totalCount = caseAnalysisMapper.getAllCaseAnalysisCount(caseAnalysis);
		Pager<CaseAnalysis> pager = new Pager<CaseAnalysis>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;
	}

	@Override
	public List<CaseAnalysis> getCaseAnalysisList() {
		return caseAnalysisMapper.getAllCaseAnalysisList();
	}

	@Override
	public CaseAnalysis getCaseAnalysisDetail(int id) {
		return caseAnalysisMapper.getCaseAnalysisDetail(id);
	}
	
	
	
	
}
