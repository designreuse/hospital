package com.dpc.web.controller.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.dpc.utils.GsonUtil;
import com.dpc.utils.JsonUtil;
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.utils.memcached.MemSession;
import com.dpc.web.VO.Field;
import com.dpc.web.VO.FieldDescn;
import com.dpc.web.VO.Pager;
import com.dpc.web.VO.Param;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Interface;
import com.dpc.web.mybatis3.domain.InterfaceCategory;
import com.dpc.web.service.IErrorCodeService;
import com.dpc.web.service.IInterfaceService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/back/interface",produces = {"application/json;charset=UTF-8"})
public class InterfaceController extends BaseController{
	
	@Autowired
	IInterfaceService interfaceService;
	@Autowired
	IErrorCodeService errorCodeService;
	
	/**
	 * 接口列表
	 */
	@RequestMapping(value = { "/list" })
	public ModelAndView list(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "name", required = false) String name){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager<Interface> page = interfaceService.findByPagination(categoryId, name);
		result.put("page", page);
		result.put("categories", interfaceService.getInterfaceCategoryList());
		return new ModelAndView("iItemManager", result);
	}
	// 接口测试工具首页
	@RequestMapping(value="i",method = RequestMethod.GET)
	public ModelAndView index(){
		Map<String, Object> map = new HashMap<String, Object>();

		// 获取接口分类
		List<InterfaceCategory> iCategories = interfaceService.getInterfaceCategoryList();
		map.put("iCategories", iCategories);

		// 获取首个分类下的全部接口
		InterfaceCategory iCategory = iCategories.get(0);
		List<Interface> iItems = interfaceService.getInterfaceByCategory(iCategory);
		map.put("iItems", iItems);

		return new ModelAndView("/back/interface/common/index", map);

	}
	
	@RequestMapping(value = { "/view/add" }, method = RequestMethod.GET)
	public String addView(HttpServletRequest request){
		List<InterfaceCategory> list = interfaceService.getInterfaceCategoryList();
		request.setAttribute("clist", list);
		return "/back/interface/common/addInterface";
	}
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public String add(String action,String method,String[] label,String[] name, Integer[] isRequired,Integer[] isRest, String[] argType,String[] fieldName, String[] fieldDescn, String[] fieldType,Interface i){
		String jsonInputParams = buildParamsJson(label, name,isRequired, argType,isRest);
		String jsonReturnParams = buildFiledsJson(fieldName, fieldDescn, fieldType);
		i.setParams(jsonInputParams);
		i.setFieldDescn(jsonReturnParams);
		i.setName(action);
		i.setMethod(method);
		interfaceService.addInterface(i);
		return "redirect:/back/interface/view/list";
	}
	
	@RequestMapping(value = { "/view/list" }, method = RequestMethod.GET)
	public String listView(HttpSession session,InterfaceCategory category){
		MemSession mem = MemSession.getSession("menu_" + session.getId(),true,"default");
		mem.setAttribute("menu", "interface", "default");
		
		return "/back/interface/common/interfaceList";
	}
	
	@RequestMapping(value ="/list", method = RequestMethod.POST)
	@ResponseBody
	public String list(HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sord=request.getParameter("sord");
		String sidx=request.getParameter("sidx");
		String params = request.getParameter("params");
		
		PageEntity<Interface> pageEntity = new PageEntity<Interface>();
		pageEntity.setRows(Integer.parseInt(rows));
		pageEntity.setPage(Integer.parseInt(page));
		pageEntity.setOffset();
		pageEntity.setSord(sord);
		pageEntity.setSidx(sidx);
		Interface i = new Interface();
		pageEntity.setParams(i);
		
		PageResult<Interface> pageResult = interfaceService.getInterfacePageer(pageEntity);
		Gson gson = new Gson();
		String data = gson.toJson(pageResult);
		return data;
	}
	
	// 获取分类下有哪些接口
	@ResponseBody
	@RequestMapping(value = "/getInterfaces", produces = "text/html;charset=UTF-8")
	public String getIItems(HttpServletRequest request) throws Exception{
		int cid = Integer.valueOf(request.getParameter("cid"));
		InterfaceCategory category = new InterfaceCategory();
		category.setId(cid);
		List<Interface> iItems = interfaceService.getInterfaceByCategory(category);
		List<Interface> newItems = new ArrayList<Interface>();
		for (int i = 0; i < iItems.size(); i++){
			Interface iItem = new Interface();
			iItem.setId(iItems.get(i).getId());
			iItem.setName(iItems.get(i).getName());
			newItems.add(iItem);
		}
		String result = JsonUtil.object2String(newItems);
		return result;
	}
	
	// 获取分类下有哪些接口
	@RequestMapping(value = "/getInterface")
	public ModelAndView getIItem(HttpServletRequest requset) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		int id = Integer.valueOf(requset.getParameter("id"));
		Interface iItem = interfaceService.getInterfaceById(id);
		String params = iItem.getParams();
		List<Field> fields = GsonUtil.jsonToFieldList(params);
		map.put("iItem", iItem);
		map.put("fields", fields);
		return new ModelAndView("/back/interface/common/itemForm", map);
	}
		
		
	@RequestMapping(value = { "/getIItemDescn" }, method = RequestMethod.POST)
	@ResponseBody
	public Object getIItemDescn(HttpServletRequest req){
		Integer id = Integer.valueOf(req.getParameter("id"));
		Map<String, Object> result = interfaceService.getIItemDescn(id);
		return result;
	}

	// 新接口
	@RequestMapping(value = "/getApiList")
	public ModelAndView getApiList(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> apis = interfaceService.getAPIList();
		result.put("iItems", apis);
		return new ModelAndView("/interface/apiList", result);
	}

	/**
	 * 跳转更新页面
	 */
	@RequestMapping(value = { "/update/{id}" }, method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("id") Integer id, Model model)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("iCategories", interfaceService.getInterfaceCategoryList());
		Interface item = interfaceService.getInterfaceById(id);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> params = (List<Map<String, Object>>) JsonUtil.json2List(item.getParams());
		result.put("iItem", item);
		result.put("params", params);
		result.put("fields", JsonUtil.json2List(item.getFieldDescn()));
		return new ModelAndView("/back/interface/common/updateInterface", result);
	}
	
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String update(String categoryId,String action,String[] label,String[] name, Integer[] isRequired, Integer[] isRest,String[] argType,String[] fieldName, String[] fieldDescn, String[] fieldType,Interface i)
	{
		String jsonInputParams = buildParamsJson(label, name,isRequired, argType,isRest);
		String jsonReturnParams = buildFiledsJson(fieldName, fieldDescn, fieldType);
		i.setParams(jsonInputParams);
		i.setFieldDescn(jsonReturnParams);
		i.setName(action);
		interfaceService.update(i, Integer.parseInt(categoryId));
		return "redirect:/back/interface/view/list";
	}
	// 错误代码说明
	@RequestMapping(value = "/errorCode", method = RequestMethod.GET)
	public ModelAndView errorCode(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorCodes", errorCodeService.getErrorCodes());
		return new ModelAndView("/interface/errorCode", map);
	}
	/**
	 * 构造一个接口响应参数的json数组
	 * 
	 * @param fieldNames
	 * @param fieldDescns
	 * @param fieldTypes
	 * @return
	 */
	private String buildFiledsJson(String[] fieldNames, String[] fieldDescns,
			String[] fieldTypes)
	{
		List<FieldDescn> fields = new ArrayList<FieldDescn>();
		if (fieldNames != null)
		{
			for (int i = 0; i < fieldNames.length; i++)
			{
				String fieldName = fieldNames[i];
				String fieldDescn = fieldDescns[i];
				String fieldType = fieldTypes[i];
				fields.add(new FieldDescn(fieldName, fieldDescn, fieldType));
			}
		}

		String json = JsonUtil.object2String(fields);
		return json;
	}
	
	/**
	 * 构造一个接口输入参数的json数组
	 * 
	 * @param labels
	 * @param names
	 * @param isRequireds
	 * @param argTypes
	 * @param isRests 
	 * @return
	 */
	private String buildParamsJson(String[] labels, String[] names,
			Integer[] isRequireds, String[] argTypes, Integer[] isRests)
	{
		List<Param> params = new ArrayList<Param>();
		if (labels != null)
		{
			for (int i = 0; i < labels.length; i++)
			{
				String label = labels[i];
				String name = names[i];
				Integer isRequired = isRequireds[i];
				String argType = argTypes[i];
				Integer isRest = isRests[i];
				params.add(new Param(label, name, isRequired, argType,isRest));
			}
		}

		String json = JsonUtil.object2String(params);
		return json;
	}
	
}
