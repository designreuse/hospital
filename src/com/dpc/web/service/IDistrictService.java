package com.dpc.web.service;

import java.util.List;

import com.dpc.web.mybatis3.domain.City;
import com.dpc.web.mybatis3.domain.County;
import com.dpc.web.mybatis3.domain.DistrictList;
import com.dpc.web.mybatis3.domain.Hospital;
import com.dpc.web.mybatis3.domain.Province;

public interface IDistrictService {

	void addProvince(Province province);

	void addCity(City c);

	void addCounty(County cc);

	void addHospital(Hospital h);

	DistrictList getunFullIdByName(String province, String city);

	DistrictList getFullIdByName(String province, String city, String county);

	List<Hospital> getHospitalByIDs(String ids);

	List<Province> getAllProvinces();

	List<City> getCitysByPid(int parseInt);

	List<County> getCountysByPid(int parseInt);

}
