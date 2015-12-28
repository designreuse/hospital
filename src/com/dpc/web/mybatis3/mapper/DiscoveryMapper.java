package com.dpc.web.mybatis3.mapper;

import java.util.List;

import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.DiscoveryImage;
import com.dpc.web.mybatis3.domain.DiscoveryRemark;

public interface DiscoveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Discovery record);

    int insertSelective(Discovery record);

    Discovery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Discovery record);

    int updateByPrimaryKey(Discovery record);

	void addDiscoveryImage(DiscoveryImage discoveryImage);

	void addDiscoveryRemark(DiscoveryRemark discoveryRemark);

	List<Discovery> getDiscoveryList();

	List<DiscoveryImage> getDiscoveryImageListByDiscoveryId(Integer id);

	Discovery getDiscoveryDetail(int id);
}