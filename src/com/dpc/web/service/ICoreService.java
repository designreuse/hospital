package com.dpc.web.service;

import java.util.List;

import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.Admin;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.City;
import com.dpc.web.mybatis3.domain.County;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.FeedBack;
import com.dpc.web.mybatis3.domain.MedicalDynamic;
import com.dpc.web.mybatis3.domain.Province;

public interface ICoreService {

	Pager<FeedBack> findFeedBackByPaginaton(FeedBack feedBack);

	void addFeedBack(FeedBack feedBack);

	void updateFeedBack(FeedBack feedBack);

	List<Admin> getAllManagerList();

	Admin getAdminById(int parseInt);

	void updateAdmin(Admin a);

	List<Province> getAllProvinceList();

	List<City> getCityListByProvinceId(int parseInt);

	List<County> getCountyListByCityId(int parseInt);

	
}
