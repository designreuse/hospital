package com.dpc.web.service;

import java.util.List;
import com.dpc.web.mybatis3.domain.BannerSlide;

public interface IOtherService {

	List<BannerSlide> getAllBannerSlide(String platform);

	void saveBannerSlide(BannerSlide bannerSlide);

	
}
