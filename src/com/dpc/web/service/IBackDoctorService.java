package com.dpc.web.service;

import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.mybatis3.domain.Doctor;

public interface IBackDoctorService {

	PageResult<DoctorVO> getAllDoctorList(PageEntity<DoctorVO> pageEntity);
	
}
