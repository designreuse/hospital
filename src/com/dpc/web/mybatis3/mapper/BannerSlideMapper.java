package com.dpc.web.mybatis3.mapper;

import java.util.List;

import com.dpc.web.mybatis3.domain.BannerSlide;

public interface BannerSlideMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BannerSlide record);

    int insertSelective(BannerSlide record);

    BannerSlide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BannerSlide record);

    int updateByPrimaryKey(BannerSlide record);

	List<BannerSlide> getAllBannerSlide(BannerSlide bannerSlide);


}