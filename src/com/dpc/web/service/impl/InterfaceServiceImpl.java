package com.dpc.web.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.PageContext;
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.Interface;
import com.dpc.web.mybatis3.domain.InterfaceCategory;
import com.dpc.web.mybatis3.mapper.DoctorMapper;
import com.dpc.web.mybatis3.mapper.InterfaceMapper;
import com.dpc.web.service.IInterfaceService;

@Service
@Transactional
public class InterfaceServiceImpl implements IInterfaceService {

	@Autowired
	private InterfaceMapper interfaceMapper;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addInterfaceCategory(InterfaceCategory category) {
		interfaceMapper.addInterfaceCategory(category);
	}

	@Override
	public List<InterfaceCategory> getInterfaceCategoryList() {
		return interfaceMapper.getInterfaceCategoryList();
	}

	@Override
	public PageResult<Interface> getInterfacePageer(PageEntity<Interface> pageEntity) {
		List<Interface> list = interfaceMapper.getInterfacePager(pageEntity);
		PageResult<Interface> result = new PageResult<Interface>();
		result.setRows(list);
		result.setRecords(interfaceMapper.getAllInterfaceCount());
		result.setPage(pageEntity.getPage());
		result.setTotal(result.getRecords()/pageEntity.getRows()+1);
		return result;
	}

	@Override
	public void addInterface(Interface i) {
		interfaceMapper.addInterface(i);
	}

	@Override
	public List<Interface> getInterfaceByCategory(InterfaceCategory iCategory) {
		return interfaceMapper.getInterfaceByCategory(iCategory.getId());
	}
	
	@Override
	public Interface getInterfaceById(int id) {
		return interfaceMapper.getInterfaceById(id);
	}

	@Override
	public List<Map<String, Object>> getAPIList() {
		List<Map<String, Object>> results = jdbcTemplate.queryForList("select id,name from t_interface_category ");
		Iterator<Map<String, Object>> iter = results.iterator();
		while(iter.hasNext()){
			Map<String, Object> result = iter.next();
			Integer id = (Integer) result.get("id");
			result.put("interfaces", jdbcTemplate.queryForList("select name,itemDesc,status from t_interface where categoryID=?",id));
		}
		return results;
	}

	@Override
	public Map<String, Object> getIItemDescn(Integer id) {
		return interfaceMapper.getByIdForMap(id);
	}

	@Override
	public Pager<Interface> findByPagination(Integer iCategoryId, String name) {
		int start = PageContext.getStart();
		int limit = PageContext.getLimit();
	
		List<Interface> datas =  interfaceMapper.getInterfaceInPageByICategoryID(iCategoryId,name,start,limit);
		Integer totalCount = interfaceMapper.getCountByName(iCategoryId,name);
		Pager<Interface> pager = new Pager<Interface>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;
	}

	@Override
	public void update(Interface i, int categoryId) {
		interfaceMapper.updateIItem(i, categoryId);
	}
}
