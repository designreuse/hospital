package com.dpc.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.mapper.DoctorMapper;
import com.dpc.web.service.IBackDoctorService;

@Service
@Transactional
public class BackDoctorServiceImpl implements IBackDoctorService {

	@Autowired
	private DoctorMapper doctorMapper ;

	@Override
	public PageResult<DoctorVO> getAllDoctorList(PageEntity<DoctorVO> pageEntity) {
		List<DoctorVO> list = doctorMapper.getAllDoctorList(pageEntity);
		PageResult<DoctorVO> result = new PageResult<DoctorVO>();
		result.setRows(list);
		result.setRecords(100);
		result.setPage(pageEntity.getPage());
		result.setTotal(result.getRecords()/pageEntity.getRows()+1);
		return result;
	}
}
