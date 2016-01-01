package com.dpc.web.controller.back;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.Validate;
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
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.service.IBackDoctorService;
import com.dpc.web.service.IDoctorService;
import com.dpc.web.service.IUserService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/back/doctor",produces = {"application/json;charset=UTF-8"})
public class BackDoctorController extends BaseController{
	
	@Autowired
	IBackDoctorService backDoctorService;
	@Autowired
	IUserService userService;
	
	
	@RequestMapping(value = "/view/list", method = RequestMethod.GET)
	public String loginView(HttpSession session,HttpServletRequest request) throws IOException{
		return "/back/doctor/list";
	}
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String getDoctorDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		DoctorVO d = backDoctorService.getDoctorDetail(Integer.parseInt(id));
		request.setAttribute("d", d);
		return "/back/doctor/detail";
	}
	@RequestMapping(value = "/update/view/{id}", method = RequestMethod.GET)
	public String updateDoctorView(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		DoctorVO d = backDoctorService.getDoctorDetail(Integer.parseInt(id));
		request.setAttribute("d", d);
		return "/back/doctor/update";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateDoctor(HttpSession session,HttpServletRequest request,DoctorVO d) throws IOException{
		
		User u = new User();
		u.setId(d.getId());
		u.setAgender(Integer.parseInt(d.getAgender()));
		u.setName(d.getName());
		userService.updateUser(u);
		
		Doctor doctor = new Doctor();
		
		
		return "/back/doctor/detail";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String register(HttpSession session,HttpServletRequest request) throws IOException{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String exportRowCount = request.getParameter("exportRowCount");
		String verifyed = request.getParameter("verifyed");
		String startScore = request.getParameter("startScore");
		String endScore = request.getParameter("endScore");
		
		DoctorVO doctor = new DoctorVO();
		if(!ValidateUtil.isEmpty(startDate) && !ValidateUtil.isEmpty(endDate)){
			doctor.setStartDate(startDate);
			doctor.setEndDate(endDate);
		}
		if(!ValidateUtil.isEmpty(username)){
			doctor.setUsername(username);
		}
		if(!ValidateUtil.isEmpty(name)){
			doctor.setName(name);
		}
		if(!ValidateUtil.isEmpty(exportRowCount)){
			doctor.setExportRowCount(Integer.parseInt(exportRowCount));
		}
		if(!ValidateUtil.isEmpty(verifyed)){
			doctor.setVerifyed(Integer.parseInt(verifyed));
		}
		if(!ValidateUtil.isEmpty(startScore) && !ValidateUtil.isEmpty(endScore)){
			doctor.setStartScore(Integer.parseInt(startScore));
			doctor.setEndScore(Integer.parseInt(endScore));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		Pager<DoctorVO> page = backDoctorService.findByPaginaton(doctor);
		request.setAttribute("page", page);
		return "/back/doctor/list";
	}
	//诊后心得
	@RequestMapping(value = "/diaexp/list", method = RequestMethod.GET)
	public String getDiaExpList(HttpSession session,HttpServletRequest request) throws IOException{
		String doctorName = request.getParameter("doctorName");
		String status = request.getParameter("status");
		String creTime = request.getParameter("creTime");
		
		DiagnoseExperience dia = new DiagnoseExperience();
		if(!ValidateUtil.isEmpty(doctorName)){
			dia.setDoctorName(doctorName);
		}
		if(!ValidateUtil.isEmpty(status)){
			dia.setStatus(Integer.parseInt(status));
		}
		if(!ValidateUtil.isEmpty(creTime)){
			dia.setCreTime(creTime);
		}
		
		Pager<DiagnoseExperience> page = backDoctorService.findDiagnoseExperienceByPaginaton(dia);
		request.setAttribute("page", page);
		return "/back/doctor/diaexpList";
	}
	//诊后心得
	@RequestMapping(value = "/diaexp/detail/{id}", method = RequestMethod.GET)
	public String getDiaExpDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		DiagnoseExperience dia = backDoctorService.getDiaExpDetail(id);
		request.setAttribute("dia", dia);
		return "/back/doctor/diaDetail";
	}
	//跳转到添加学术活动
	@RequestMapping(value = "/academicSupport/add/view", method = RequestMethod.GET)
	public String addAcademicSupportView(HttpSession session,HttpServletRequest request) throws IOException{
		
		
		return "/back/doctor/addAcademicSupport";
	}
	//添加学术活动
	@RequestMapping(value = "/academicSupport/add", method = RequestMethod.POST)
	public String addAcademicSupport(HttpSession session,HttpServletRequest request,AcademicSupport academicSupport) throws IOException{
		backDoctorService.addAcademicSupport(academicSupport);
		academicSupport.setDelFlag(0);
		return "/back/doctor/addAcademicSupport";
	}
	@RequestMapping(value = "/academicSupport/list", method = RequestMethod.GET)
	public String getAcademicSupportList(HttpSession session,HttpServletRequest request,AcademicSupport academicSupport) throws IOException{
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		String creTime = request.getParameter("creTime");
		
		AcademicSupport support = new AcademicSupport();
		if(!ValidateUtil.isEmpty(title)){
			support.setTitle(title);
		}
		if(!ValidateUtil.isEmpty(type)){
			support.setType(Integer.parseInt(type));
		}
		if(!ValidateUtil.isEmpty(creTime)){
			support.setCreTime(creTime);
		}
		
		Pager<AcademicSupport> page = backDoctorService.findAcademicSupportByPaginaton(support);
		request.setAttribute("page", page);
		return "/back/doctor/academicSupportList";
	}
	
	@RequestMapping(value = "/caseAnalysis/add/view", method = RequestMethod.GET)
	public String addCaseAnalysisView(HttpSession session,HttpServletRequest request,AcademicSupport academicSupport) throws IOException{
		return "/back/doctor/addCaseAnalysis";
	}
}
