package com.dpc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.ConstantUtil;
import com.dpc.utils.PageContext;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.Admin;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.City;
import com.dpc.web.mybatis3.domain.County;
import com.dpc.web.mybatis3.domain.FeedBack;
import com.dpc.web.mybatis3.domain.Province;
import com.dpc.web.mybatis3.mapper.AdminMapper;
import com.dpc.web.mybatis3.mapper.ArticleMapper;
import com.dpc.web.mybatis3.mapper.CityMapper;
import com.dpc.web.mybatis3.mapper.CountyMapper;
import com.dpc.web.mybatis3.mapper.FeedBackMapper;
import com.dpc.web.mybatis3.mapper.ProvinceMapper;
import com.dpc.web.service.IArticleService;
import com.dpc.web.service.ICoreService;

@Service
@Transactional
public class CoreServiceImpl implements ICoreService {
	
	@Autowired
	private FeedBackMapper feedBackMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private ProvinceMapper  provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CountyMapper countyMapper;
		
	
	@Override
	public Pager<FeedBack> findFeedBackByPaginaton(FeedBack feedBack) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		feedBack.setStart(start);
		feedBack.setLimit(limit);
		List<FeedBack> datas = feedBackMapper.getFeedBackListWithPager(feedBack,start,limit);
		if(datas!=null&&datas.size()>0){
			for(FeedBack d : datas){
				
			}
		}
		Integer totalCount = feedBackMapper.getAllFeedBackCount(feedBack);
		Pager<FeedBack> pager = new Pager<FeedBack>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;
	}


	@Override
	public void addFeedBack(FeedBack feedBack) {
		feedBackMapper.insertSelective(feedBack);
		
	}


	@Override
	public void updateFeedBack(FeedBack feedBack) {
		feedBackMapper.updateByPrimaryKeySelective(feedBack);
	}


	@Override
	public List<Admin> getAllManagerList() {
		return adminMapper.getAllManagerList();
	}


	@Override
	public Admin getAdminById(int id) {
		return adminMapper.selectByPrimaryKey(id);
	}


	@Override
	public void updateAdmin(Admin a) {
		adminMapper.updateByPrimaryKeySelective(a);		
	}


	@Override
	public List<Province> getAllProvinceList() {
		return provinceMapper.getAllProvinces();
	}


	@Override
	public List<City> getCityListByProvinceId(int id) {
		return cityMapper.getCitysByPid(id);
	}


	@Override
	public List<County> getCountyListByCityId(int pid) {
		return countyMapper.getCountysByPid(pid);
	}
	
	
}
