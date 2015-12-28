package com.dpc.web.controller.front;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dpc.utils.ConstantUtil;
import com.dpc.utils.DateUtil;
import com.dpc.utils.ErrorCodeUtil;
import com.dpc.utils.JsonUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceImage;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceRemark;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.HeartCircle;
import com.dpc.web.mybatis3.domain.HeartCircleRemark;
import com.dpc.web.mybatis3.domain.MedicalDynamic;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.service.IArticleService;
import com.dpc.web.service.IDoctorService;

@Controller
@RequestMapping(value="/doctor",produces = {"application/json;charset=UTF-8"})
public class DoctorController extends BaseController{
	
	@Autowired
	IDoctorService doctorService;
	
	@Autowired
	IArticleService articleService;
	
	
	//添加诊后心得
	@RequestMapping(value = "/diagnose_experience/add", method = RequestMethod.POST)
	@ResponseBody
	public String addDiagnoseExperience(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		String isAnonymous = request.getParameter("isAnonymous"); 
		String experience = request.getParameter("experience"); 
		String status = request.getParameter("status"); 
		String illDesc = request.getParameter("illDesc"); 
		String illType = request.getParameter("illType"); 
		String[] imageBase64s=request.getParameterValues("imageBase64s");
		
		if(ValidateUtil.isEmpty(illType)){
			return error(ErrorCodeUtil.e11203);
		}
		if(ValidateUtil.isEmpty(illDesc)){
			return error(ErrorCodeUtil.e11201);
		}
		if(ValidateUtil.isEmpty(experience)){
			return error(ErrorCodeUtil.e11202);
		}
		if(ValidateUtil.isEmpty(isAnonymous)){
			//默认匿名
			isAnonymous = "1";
		}
		if(ValidateUtil.isEmpty(status)){
			//默认未发表
			status = "0";
		}
		List<String> imageUrls = null;
		if(!ValidateUtil.isEmpty(imageBase64s)){
			imageUrls =upload(session,request,imageBase64s);
		}
		
		DiagnoseExperience diagnoseExperience = new DiagnoseExperience();
		diagnoseExperience.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		diagnoseExperience.setDoctorId(u.getId());
		diagnoseExperience.setExperience(experience);
		diagnoseExperience.setIllType(illType);
		diagnoseExperience.setIsAnonymous(Integer.parseInt(isAnonymous));
		diagnoseExperience.setReadCount(0);
		diagnoseExperience.setReadCount(0);
		diagnoseExperience.setStatus(Integer.parseInt(status));
		doctorService.addDiagnoseExperience(diagnoseExperience,imageUrls);
		
		return success();
	}
	
	@RequestMapping(value = "/diagnose_experience/remark", method = RequestMethod.POST)
	@ResponseBody
	public String diagnoseExperienceRemark(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		String diaExpId = request.getParameter("diaExpId"); 
		String content = request.getParameter("content"); 
		String name = u.getName();
		String profileImage = u.getProfileImageUrl();
		
		DiagnoseExperienceRemark remark = new DiagnoseExperienceRemark();
		remark.setContent(content);
		remark.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		remark.setDiaExpId(Integer.parseInt(diaExpId));
		remark.setDoctorId(u.getId());
		remark.setName(name);
		remark.setProfileImage(profileImage);
		remark.setDelFlag(0);
		
		doctorService.addDiagnoseExperienceRemark(remark);
		return success();
	}
	@RequestMapping(value = "/diagnose_experience/detail", method = RequestMethod.POST)
	@ResponseBody
	public String getDiagnoseExperienceDetail(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		String diaExpId = request.getParameter("diaExpId"); 
		DiagnoseExperience diagnoseExperience = doctorService.getDiagnoseExperienceDetail(Integer.parseInt(diaExpId));
		List<DiagnoseExperienceImage> images = doctorService.getDiagnoseExperienceImageByDiaExpId(Integer.parseInt(diaExpId));
		if(images!=null&&images.size()>0){
			for(DiagnoseExperienceImage image : images){
				image.setImageUrl(ConstantUtil.DOMAIN+image.getImageUrl());
			}
		}
		diagnoseExperience.setDiagnoseExpImgList(images);
		return JsonUtil.object2String(diagnoseExperience);
	}
	@RequestMapping(value = "/diagnose_experience/list", method = RequestMethod.POST)
	@ResponseBody
	public String getDiagnoseExperienceList(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		List<DiagnoseExperience> list = doctorService.getDiagnoseExperienceList();
		return JsonUtil.object2String(list);
	}
	//添加诊后心得
	@RequestMapping(value = "/diagnose_experience/view/add", method = RequestMethod.GET)
	public String addDiagnoseExperienceView(HttpSession session,HttpServletRequest request) throws IOException{
		
		return "/back/interface/doctor/addDiagnoseExperienceView";
	}
	
	
	@RequestMapping(value = "/heartcircle/add", method = RequestMethod.POST)
	@ResponseBody
	public String addHeartCircle(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		String[] imageBase64s=request.getParameterValues("imageBase64s");
		String content = request.getParameter("content");
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e11300);
		}
		List<String> imageUrls = null;
		if(!ValidateUtil.isEmpty(imageBase64s)){
			imageUrls =upload(session,request,imageBase64s);
		}
		
		HeartCircle circle = new HeartCircle();
		circle.setContent(content);
		circle.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		circle.setDoctorId(u.getId());
		Doctor d = new Doctor();
		d.setId(u.getId());
		List<Doctor> dlist = doctorService.getDoctorList(d);
		if(dlist!=null&&dlist.size()>0){
			d = dlist.get(0);
			circle.setDoctorName(d.getName());
		}
		circle.setProfileImage(u.getProfileImageUrl());
		circle.setRemarkCount(0);
		
		doctorService.addHeartCircle(circle,imageUrls);
		
		return success();
	}
	@RequestMapping(value = "/heartcircle/detail", method = RequestMethod.POST)
	@ResponseBody
	public String getHeartCircleDetail(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		String id = request.getParameter("id");
		if(ValidateUtil.isEmpty(id)){
			return error(ErrorCodeUtil.e11301);
		}
		HeartCircle circle = doctorService.getHeartCircleDetail(Integer.parseInt(id));
		return success();
	}
	@RequestMapping(value = "/heartcircle/remark", method = RequestMethod.POST)
	@ResponseBody
	public String addHeartCircleRemark(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		String heartCircleId = request.getParameter("heartCircleId");
		String remark = request.getParameter("remark");
		if(ValidateUtil.isEmpty(heartCircleId)){
			return error(ErrorCodeUtil.e11301);
		}
		if(ValidateUtil.isEmpty(remark)){
			return error(ErrorCodeUtil.e11302);
		}
		HeartCircleRemark heartCircleRemark = new HeartCircleRemark();
		Doctor d = new Doctor();
		d.setId(u.getId());
		List<Doctor> dlist = doctorService.getDoctorList(d);
		if(dlist!=null&&dlist.size()>0){
			d = dlist.get(0);
			heartCircleRemark.setDoctorName(d.getName());
		}
		heartCircleRemark.setDoctorId(u.getId());
		heartCircleRemark.setRemark(remark);
		heartCircleRemark.setHeartCircleId(Integer.parseInt(heartCircleId));
		heartCircleRemark.setProfileImage(u.getProfileImageUrl());
		heartCircleRemark.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		doctorService.addHeartCircleRemark(heartCircleRemark);
		return success();
	}
	@RequestMapping(value = "/heartcircle/remark/list", method = RequestMethod.POST)
	@ResponseBody
	public String getHeartCircleRemarkList(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		List<HeartCircleRemark> list = doctorService.getHeartCircleRemarkList();
		if(list!=null&&list.size()>0){
			for(HeartCircleRemark circleRemark : list){
				circleRemark.setProfileImage(ConstantUtil.DOMAIN+circleRemark.getProfileImage());
				Date beforeDate = DateUtil.parse(circleRemark.getCreTime(), DateUtil.DATETIME_PATTERN); 
				Date afterDate  = new Date();
				circleRemark.setCreTime(DateUtil.timeDiffer(beforeDate, afterDate));
			}
		}
		return JsonUtil.object2String(list);
	}
	
	@RequestMapping(value = "/heartcircle/list", method = RequestMethod.POST)
	@ResponseBody
	public String getHeartCircleList(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		List<HeartCircle> list = doctorService.getHeartCircleList();
//		if(list!=null&&list.size()>0){
//			for(HeartCircleRemark circleRemark : list){
//				circleRemark.setProfileImage(ConstantUtil.DOMAIN+circleRemark.getProfileImage());
//				Date beforeDate = DateUtil.parse(circleRemark.getCreTime(), DateUtil.DATETIME_PATTERN); 
//				Date afterDate  = new Date();
//				circleRemark.setCreTime(DateUtil.timeDiffer(beforeDate, afterDate));
//			}
//		}
		return JsonUtil.object2String(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
