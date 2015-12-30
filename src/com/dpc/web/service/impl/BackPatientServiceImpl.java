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
import com.dpc.web.VO.PatientVO;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.mapper.DoctorMapper;
import com.dpc.web.mybatis3.mapper.PatientMapper;
import com.dpc.web.service.IBackDoctorService;
import com.dpc.web.service.IBackPatientService;

@Service
@Transactional
public class BackPatientServiceImpl implements IBackPatientService {

	@Autowired
	private PatientMapper patientMapper ;

	@Override
	public Pager<PatientVO> findByPaginaton(PatientVO p) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		p.setStart(start);
		p.setLimit(limit);
		List<PatientVO> datas = patientMapper.getPatientListWithPager(p,start,limit);
		if(datas!=null&&datas.size()>0){
			for(PatientVO d : datas){
				
			}
		}
		Integer totalCount = patientMapper.getAllPatientCount(p);
		Pager<PatientVO> pager = new Pager<PatientVO>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;
	}

	
	
	
	
}
