package com.dpc.web.mybatis3.mapper;

import java.util.List;

import com.dpc.web.VO.PatientVO;
import com.dpc.web.VO.WishVO;
import com.dpc.web.mybatis3.domain.Patient;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.mybatis3.domain.WishRemark;

public interface PatientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);

	PatientVO getProfile(int id);

	void addWish(Wish wish);

	void addWishRemark(WishRemark wishRemark);

	void updateWish(Wish wish);

	List<WishVO> getWishListByUserId(Integer id);
}