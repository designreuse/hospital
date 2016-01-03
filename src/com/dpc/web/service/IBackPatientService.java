package com.dpc.web.service;

import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.VO.PatientVO;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.Wish;

public interface IBackPatientService {

	Pager<PatientVO> findByPaginaton(PatientVO p);

	Pager<Wish> findWishListByPaginaton(Wish wish);

	Wish getWishDetail(int parseInt);

	Pager<Discovery> findDiscoveryByPaginaton(Discovery d);

	
}
