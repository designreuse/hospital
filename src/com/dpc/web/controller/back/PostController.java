package com.dpc.web.controller.back;

import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dpc.utils.ConstantUtil;
import com.dpc.utils.DateUtil;
import com.dpc.utils.JsonUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.utils.memcached.MemSession;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Admin;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.DiscoveryImage;
import com.dpc.web.mybatis3.domain.HeartCircle;
import com.dpc.web.mybatis3.domain.HeartCircleImage;
import com.dpc.web.service.IArticleService;
import com.dpc.web.service.IDoctorService;
import com.dpc.web.service.IPatientService;

@Controller
@RequestMapping(value="/back/post",produces = {"application/json;charset=UTF-8"})
public class PostController extends BaseController{
	
	@Autowired
	private IPatientService patientService;
	@Autowired
	private IDoctorService doctorService;
	

	//后台新建患者帖子
	@RequestMapping(value = "/addDiscovery", method = RequestMethod.POST)
	public String addDiscovery(HttpSession session,HttpServletRequest request) throws IOException{
		Admin admin = (Admin) session.getAttribute("admin");
		String content = request.getParameter("content");
		List<MultipartFile> images = null;
		List<String> imageUrls = null;
		if (request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			images = req.getFiles("discoveryImage");
			imageUrls = upload(session,request,images);
		}
		Discovery d = new Discovery();
		d.setDelFlag(0);
		d.setType(1);
		d.setContent(content);
		d.setPostTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		d.setVoteCount(0);
		d.setRemarkCount(0);
		d.setUserId(admin.getId());
		patientService.addBackPost(d,imageUrls);
		return "redirect:/back/post/patient/list/view";
	}
	//后台新建患者帖子
	@RequestMapping(value = "/addHeartCircle", method = RequestMethod.POST)
	public String addHeartCircle(HttpSession session,HttpServletRequest request) throws IOException{
		Admin admin = (Admin) session.getAttribute("admin");
		String content = request.getParameter("content");
		List<MultipartFile> images = null;
		List<String> imageUrls = null;
		if (request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			images = req.getFiles("discoveryImage");
			imageUrls = upload(session,request,images);
		}
		HeartCircle circle = new HeartCircle();
		circle.setContent(content);
		circle.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		circle.setDoctorId(admin.getId());
		circle.setProfileImage(admin.getProfileImageUrl());
		circle.setRemarkCount(0);
		circle.setType(1);
		circle.setDelFlag(0);
		doctorService.addHeartCircle(circle,imageUrls);
		return "redirect:/back/post/doctor/list/view";
	}
	@RequestMapping(value = "/patient/list/view", method = RequestMethod.GET)
	public String getPatientPostsView(HttpSession session,HttpServletRequest request) throws IOException{
		MemSession mem = MemSession.getSession("menu_" + session.getId(),true,"default");
		mem.setAttribute("menu", "post", "default");
		
		String username = request.getParameter("username");
		String postTime = request.getParameter("postTime");
		
		Discovery d = new Discovery();
		if(!ValidateUtil.isEmpty(username)){
			d.setUsername(username);
		}
		if(!ValidateUtil.isEmpty(postTime)){
			d.setPostTime(postTime);
		}
//		d.setType(1);
		Pager<Discovery> page = patientService.findDiscoveryByPaginaton(d);
		request.setAttribute("page", page);
		
		return "/back/post/patientPosts";
	}
	@RequestMapping(value = "/patient/detail", method = RequestMethod.GET)
	public String getPatientDetial(HttpSession session,HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		Discovery d = patientService.getDiscoveryDetailById(Integer.parseInt(id));
		if(d!=null&&!ValidateUtil.isEmpty(d.getImageList())){
			for(DiscoveryImage i : d.getImageList()){
				i.setImageUrl(ConstantUtil.DOMAIN+i.getImageUrl());
			}
		}
		request.setAttribute("d", d);
		return "/back/post/patientPostDetail";
	}
	@RequestMapping(value = "/patient/del", method = RequestMethod.GET)
	public String delPatientPost(HttpSession session,HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		Discovery d = new Discovery();
		d.setId(Integer.parseInt(id));
		d.setDelFlag(1);
		patientService.updateDiscovery(d);
		return "redirect:/back/post/patient/list/view";
	}
	@RequestMapping(value = "/doctor/detail", method = RequestMethod.GET)
	public String getDoctorDetial(HttpSession session,HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		HeartCircle d = doctorService.getHeartCircleDetailById(Integer.parseInt(id));
		if(d!=null&&!ValidateUtil.isEmpty(d.getImageList())){
			for(HeartCircleImage i : d.getImageList()){
				i.setImageUrl(ConstantUtil.DOMAIN+i.getImageUrl());
			}
		}
		request.setAttribute("d", d);
		return "/back/post/doctorPostDetail";
	}
	@RequestMapping(value = "/doctor/del", method = RequestMethod.GET)
	public String delDoctorPost(HttpSession session,HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		HeartCircle d = new HeartCircle();
		d.setId(Integer.parseInt(id));
		d.setContent(null);
		d.setDelFlag(1);
		doctorService.updateHeartCircle(d);
		return "redirect:/back/post/doctor/list/view";
	}
	
	
	@RequestMapping(value = "/doctor/list/view", method = RequestMethod.GET)
	public String getDoctorPostsView(HttpSession session,HttpServletRequest request) throws IOException{
		MemSession mem = MemSession.getSession("menu_" + session.getId(),true,"default");
		mem.setAttribute("menu", "post", "default");
		String username = request.getParameter("username");
		String postTime = request.getParameter("postTime");
		
		HeartCircle h = new HeartCircle();
		if(!ValidateUtil.isEmpty(username)){
			h.setUsername(username);
		}
		if(!ValidateUtil.isEmpty(postTime)){
			h.setCreTime(postTime);
		}
		Pager<HeartCircle> page = doctorService.findHeartCircleByPaginaton(h);
		request.setAttribute("page", page);
		
		return "/back/post/doctorPosts";
	}
	
	
	@RequestMapping(value = "/patient/add/view", method = RequestMethod.GET)
	public String addPatientPostView(HttpSession session,HttpServletRequest request) throws IOException{
		MemSession mem = MemSession.getSession("menu_" + session.getId(),true,"default");
		mem.setAttribute("menu", "post", "default");
		return "/back/post/addPatientPost";
	}
	@RequestMapping(value = "/doctor/add/view", method = RequestMethod.GET)
	public String addDoctorPostView(HttpSession session,HttpServletRequest request) throws IOException{
		MemSession mem = MemSession.getSession("menu_" + session.getId(),true,"default");
		mem.setAttribute("menu", "post", "default");
		return "/back/post/addDoctorPost";
	}
	
	
	
}
