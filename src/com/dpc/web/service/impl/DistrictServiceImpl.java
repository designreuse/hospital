package com.dpc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.mybatis3.domain.City;
import com.dpc.web.mybatis3.domain.County;
import com.dpc.web.mybatis3.domain.DistrictList;
import com.dpc.web.mybatis3.domain.Hospital;
import com.dpc.web.mybatis3.domain.Interface;
import com.dpc.web.mybatis3.domain.Province;
import com.dpc.web.mybatis3.mapper.CityMapper;
import com.dpc.web.mybatis3.mapper.CountyMapper;
import com.dpc.web.mybatis3.mapper.HospitalMapper;
import com.dpc.web.mybatis3.mapper.ProvinceMapper;
import com.dpc.web.service.IDistrictService;

@Service
@Transactional
public class DistrictServiceImpl implements IDistrictService {

	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CountyMapper countyMapper;
	@Autowired
	private HospitalMapper hospitalMapper;
	@Override
	public void addProvince(Province province) {
		provinceMapper.insertSelective(province);
	}
	@Override
	public void addCity(City c) {
		cityMapper.insertSelective(c);
	}
	@Override
	public void addCounty(County cc) {
		countyMapper.insertSelective(cc);
		
	}
	@Override
	public void addHospital(Hospital h) {
		hospitalMapper.insertSelective(h);
		
	}
	@Override
	public DistrictList getunFullIdByName(String province, String city) {
		return countyMapper.getunFullIdByName(province,city);
	}
	@Override
	public DistrictList getFullIdByName(String province, String city, String county) {
		return countyMapper.getFullIdByName(province,city,county);
	}
	@Override
	public List<Hospital> getHospitalByIDs(String ids) {
		return hospitalMapper.getHospitalByIDs(ids);
	}
	@Override
	public List<Province> getAllProvinces() {
		return provinceMapper.getAllProvinces();
	}
	@Override
	public List<City> getCitysByPid(int pid) {
		return cityMapper.getCitysByPid(pid);
	}
	@Override
	public List<County> getCountysByPid(int pid) {
		return countyMapper.getCountysByPid(pid);
	}
	
}
