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

import com.dpc.utils.ErrorCodeUtil;
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
import com.dpc.web.mybatis3.domain.Patient;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.service.IBackDoctorService;
import com.dpc.web.service.IBackPatientService;
import com.dpc.web.service.IPatientService;
import com.dpc.web.service.IUserService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/back/patient",produces = {"application/json;charset=UTF-8"})
public class BackPatientController extends BaseController{
	
	@Autowired
	IBackPatientService backPatientService;
	@Autowired
	IPatientService patientService;
	@Autowired
	IUserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String register(HttpSession session,HttpServletRequest request) throws IOException{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String startScore = request.getParameter("startScore");
		String endScore = request.getParameter("endScore");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		PatientVO p = new PatientVO();
		if(!ValidateUtil.isEmpty(startDate) && !ValidateUtil.isEmpty(endDate)){
			p.setStartDate(startDate);
			p.setEndDate(endDate);
		}
		if(!ValidateUtil.isEmpty(startScore) && !ValidateUtil.isEmpty(endScore)){
			p.setStartScore(Integer.parseInt(startScore));
			p.setEndScore(Integer.parseInt(endScore));
		}
		if(!ValidateUtil.isEmpty(username)){
			p.setUsername(username);
		}
		if(!ValidateUtil.isEmpty(name)){
			p.setName(name);
		}
		Pager<PatientVO> page = backPatientService.findByPaginaton(p);
		request.setAttribute("page", page);
		return "/back/patient/list";
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String getPatientDetail(HttpSession session,HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
	
		PatientVO p = patientService.getProfile(Integer.parseInt(id));
		request.setAttribute("p", p);
		return "/back/patient/detail";
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
		if(!ValidateUtil.isEmpty(isComeTrue) && !isComeTrue.equals("-1")){
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
	@RequestMapping(value = "/updatePatientView", method = RequestMethod.GET)
	public String updatePatientView(HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		
		PatientVO p = patientService.getProfile(Integer.parseInt(id));
		request.setAttribute("p", p);
		return "/back/patient/updatePatient";
	}
	@RequestMapping(value = "/updatePatient", method = RequestMethod.POST)
	public String updatePatient(HttpServletRequest request) throws IOException{
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String agender = request.getParameter("agender");
		String birthday = request.getParameter("birthday");
		String mobile = request.getParameter("mobile");
		String illProfile = request.getParameter("illProfile");
		
		String weight = request.getParameter("weight");
		
		User user = new User();
		user.setId(Integer.parseInt(userId));
		
		user.setMobile(mobile);
		user.setName(name);
		user.setAgender(Integer.parseInt(agender));
		user.setBirthday(birthday);
		userService.updateUser(user);
		Patient patient = new Patient();
		patient.setUserId(Integer.parseInt(userId));
		patient.setWeight(Double.parseDouble(weight));
		patient.setIllProfile(illProfile);
		patientService.updatePatient(patient);
		return "redirect:/back/patient/list";
	}
	
	
}
