package com.dpc.web.controller.back;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.VO.PatientVO;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.service.IBackDoctorService;
import com.dpc.web.service.IBackPatientService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/back/patient",produces = {"application/json;charset=UTF-8"})
public class BackPatientController extends BaseController{
	
	@Autowired
	IBackPatientService backPatientService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String register(HttpSession session,HttpServletRequest request) throws IOException{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String delFlag = request.getParameter("delFlag");
		String category = request.getParameter("category");
		String illType = request.getParameter("illType");
		Article article = new Article();
		if(!ValidateUtil.isEmpty(startDate)){
			article.setStartDate(startDate);
		}
		if(!ValidateUtil.isEmpty(endDate)){
			article.setEndDate(endDate);
		}
		if(!ValidateUtil.isEmpty(delFlag) && !delFlag.equals("-1")){
			article.setDelFlag(Integer.parseInt(delFlag));
		}
		if(!ValidateUtil.isEmpty(category) && !category.equals("-1")){
			article.setCategory(Integer.parseInt(category));
		}
		if(!ValidateUtil.isEmpty(illType) && !illType.equals("-1")){
			article.setIllType(Integer.parseInt(illType));
		}
		PatientVO p = new PatientVO();
		Pager<PatientVO> page = backPatientService.findByPaginaton(p);
		request.setAttribute("page", page);
		return "/back/patient/list";
	}
	
	@RequestMapping(value = "/wish/list", method = RequestMethod.GET)
	public String getWishList(HttpSession session,HttpServletRequest request) throws IOException{
		String patientName = request.getParameter("patientName");
		String mobile = request.getParameter("mobile");
		String isComeTrue = request.getParameter("isComeTrue");
		Wish wish = new Wish();
		if(!ValidateUtil.isEmpty(patientName)){
			wish.setPatientName(patientName);
		}
		if(!ValidateUtil.isEmpty(isComeTrue)){
			wish.setIsComeTrue(Integer.parseInt(isComeTrue));
		}
		if(!ValidateUtil.isEmpty(mobile)){
			wish.setMobile(mobile);
		}
		Pager<Wish> page = backPatientService.findWishListByPaginaton(wish);
		request.setAttribute("page", page);
		return "/back/patient/wishList";
	}
	@RequestMapping(value = "/wish/detail/{id}", method = RequestMethod.GET)
	public String getWishDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		Wish wish = backPatientService.getWishDetail(Integer.parseInt(id));
		request.setAttribute("wish", wish);
		return "/back/patient/wishDetail";
	}
	
	
	//获取患者帖子列表---对应发现
	@RequestMapping(value = "/discovery/list", method = RequestMethod.GET)
	public String getDiscoveryList(HttpSession session,HttpServletRequest request) throws IOException{
		Discovery d = new Discovery();
		Pager<Discovery> page = backPatientService.findDiscoveryByPaginaton(d);
		return "/back/patient/wishDetail";
	}
	
	//患者帖子详情
	
	//后台新建患者帖子
	
	
}
