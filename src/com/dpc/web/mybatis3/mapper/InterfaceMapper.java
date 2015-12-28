package com.dpc.web.mybatis3.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.dpc.utils.PageEntity;
import com.dpc.web.mybatis3.domain.Interface;
import com.dpc.web.mybatis3.domain.InterfaceCategory;

public interface InterfaceMapper{

	void addInterfaceCategory(InterfaceCategory category);

	List<InterfaceCategory> getInterfaceCategoryList();

	Integer getAllInterfaceCount();

	List<Interface> getInterfacePager(PageEntity<Interface> pageEntity);

	void addInterface(Interface i);

	List<Interface> getInterfaceByCategory(Integer id);

	Map<String, Object> getByIdForMap(Integer id);

	Interface getInterfaceById(int id);

	Integer getCountByName(Integer iCategoryId, String name);

	List<Interface> getInterfaceInPageByICategoryID(Integer iCategoryId, String name, int start, int limit);

	void updateIItem(@Param("item") Interface item, @Param("categoryId") Integer categoryId);
	
}


