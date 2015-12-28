package com.dpc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.ValidateUtil;
import com.dpc.web.mybatis3.domain.BannerSlide;
import com.dpc.web.mybatis3.mapper.BannerSlideMapper;
import com.dpc.web.service.IOtherService;

@Service
@Transactional
public class OtherServiceImpl implements IOtherService {

	@Autowired
	private BannerSlideMapper bannerSlideMapper;

	@Override
	public List<BannerSlide> getAllBannerSlide(String platform) {
		BannerSlide bannerSlide = new BannerSlide();
		if(!ValidateUtil.isEmpty(platform)){
			bannerSlide.setPlatform(Integer.parseInt(platform));
		}
		return bannerSlideMapper.getAllBannerSlide(bannerSlide);
	}

	@Override
	public void saveBannerSlide(BannerSlide bannerSlide) {
		bannerSlideMapper.insertSelective(bannerSlide);
	}
}
