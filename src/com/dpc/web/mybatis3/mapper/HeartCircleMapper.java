package com.dpc.web.mybatis3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.HeartCircle;
import com.dpc.web.mybatis3.domain.HeartCircleImage;
import com.dpc.web.mybatis3.domain.HeartCircleRemark;

public interface HeartCircleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeartCircle record);

    int insertSelective(HeartCircle record);

    HeartCircle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeartCircle record);

    int updateByPrimaryKey(HeartCircle record);

	void addHeartCircleImage(HeartCircleImage image);

	HeartCircle getHeartCircleDetail(int id);

	void addHeartCircleRemark(HeartCircleRemark heartCircleRemark);

	List<HeartCircleRemark> getHeartCircleRemarkList();

	List<HeartCircle> getHeartCircleList();

	List<HeartCircleImage> getHeartCircleImageListByHeartCircleId(int hcId);

	HeartCircle getHeartCircleById(int id);
	
	List<HeartCircle> findHeartCircleByPaginaton(@Param("h") HeartCircle h, @Param("start") Integer start, @Param("limit") Integer limit);

	Integer getHeartCircleCount(@Param("h") HeartCircle h);

	HeartCircle getHeartCircleImageDetail(int id);
}