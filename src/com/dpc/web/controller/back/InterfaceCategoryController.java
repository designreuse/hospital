package com.dpc.web.controller.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.InterfaceCategory;
import com.dpc.web.service.IInterfaceService;

@Controller
@RequestMapping(value="/back/interface/category",produces = {"application/json;charset=UTF-8"})
public class InterfaceCategoryController extends BaseController{
	
	@Autowired
	IInterfaceService interfaceService;
	
	
	@RequestMapping(value = { "/view/add" }, method = RequestMethod.GET)
	public String addView(InterfaceCategory category){
		return "/back/interface/common/addInterfaceCategory";
	}
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public String add(InterfaceCategory category){
		category.setDelFlag(0);
		interfaceService.addInterfaceCategory(category);
		return "redirect:/back/interface/category/list";
	}
	
	/**
	 * 
	 * @description 获取接口分类列表
	 * @author dcx
	 * @date 2015年12月21日 下午10:31:10
	 * @param category
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listView(HttpServletRequest request){
		List<InterfaceCategory> list = interfaceService.getInterfaceCategoryList();
		request.setAttribute("list", list);
		return "/back/interface/common/interfaceCategoryList";
	}
	
}
