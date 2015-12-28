package com.dpc.web.service;

import java.util.List;
import java.util.Map;
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.Interface;
import com.dpc.web.mybatis3.domain.InterfaceCategory;

public interface IInterfaceService {

	void addInterfaceCategory(InterfaceCategory category);

	List<InterfaceCategory> getInterfaceCategoryList();

	PageResult<Interface> getInterfacePageer(PageEntity<Interface> pageEntity);

	void addInterface(Interface i);

	List<Interface> getInterfaceByCategory(InterfaceCategory iCategory);

	Interface getInterfaceById(int id);

	List<Map<String, Object>> getAPIList();

	Map<String, Object> getIItemDescn(Integer id);

	Pager<Interface> findByPagination(Integer iCategoryId, String name);

	void update(Interface i, int parseInt);
	
}
