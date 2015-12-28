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
import com.dpc.web.VO.PatientVO;
import com.dpc.web.VO.WishVO;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.DiscoveryImage;
import com.dpc.web.mybatis3.domain.DiscoveryRemark;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.mybatis3.domain.WishRemark;
import com.dpc.web.service.IPatientService;

@Controller
@RequestMapping(value="/patient",produces = {"application/json;charset=UTF-8"})
public class PatientController extends BaseController{
	
	@Autowired
	IPatientService patientService;
	
	//患者个人资料
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String profile(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(ValidateUtil.isEmpty(id)){
			return error(ErrorCodeUtil.e11500);
		}
		PatientVO patientVO = patientService.getProfile(Integer.parseInt(id));
		return JsonUtil.object2String(patientVO);
	}
	
	//患者许愿
	@RequestMapping(value = "/wish", method = RequestMethod.POST)
	@ResponseBody
	public String wish(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		String content = request.getParameter("content");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e11501);
		}
		Wish wish = new Wish();
		wish.setUserId(u.getId());
		wish.setPatientName(u.getName());
		wish.setContent(content);
		wish.setPatientProfileImageUrl(u.getProfileImageUrl());
		wish.setPostTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		wish.setDelFlag(0);
		wish.setIsComeTrue(0);
		patientService.addWish(wish);
		return success();
	}
	//患者许愿
	@RequestMapping(value = "/wish/cometrue/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String wishComeTrue(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType()!=null&&u.getRegisterType()==2){
			return error(ErrorCodeUtil.e10000);
		}
		if(ValidateUtil.isEmpty(id)){
			return error(ErrorCodeUtil.e11502);
		}
		Wish wish = new Wish();
		wish.setId(Integer.parseInt(id));
		wish.setIsComeTrue(1);
		patientService.updateWish(wish);
		return success();
	}
	//获取患者许愿列表
	@RequestMapping(value = "/wishlist", method = RequestMethod.GET)
	@ResponseBody
	public String getWishList(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		List<WishVO> wishList = patientService.getWishListByUserId(u.getId());
		if(wishList!=null&&wishList.size()>0){
			for(WishVO wish : wishList){
				wish.setPatientProfileImageUrl(ConstantUtil.DOMAIN+wish.getPatientProfileImageUrl());
				wish.setPostTime(DateUtil.timeDiffer(DateUtil.parse(wish.getPostTime(), DateUtil.DATETIME_PATTERN), DateUtil.parse(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN), DateUtil.DATETIME_PATTERN)));
				List<WishRemark> rlist = wish.getWishRemarkList();
				if(rlist!=null&&rlist.size()>0){
					for(WishRemark wr : rlist){
						wr.setRemarkProfileImage(ConstantUtil.DOMAIN+wr.getRemarkProfileImage());
						wr.setRemarkTime(DateUtil.timeDiffer(DateUtil.parse(wr.getRemarkTime(), DateUtil.DATETIME_PATTERN), DateUtil.parse(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN), DateUtil.DATETIME_PATTERN)));
					}
				}
			}
		}
		return JsonUtil.object2String(wishList);
	}
	//患者许愿
	@RequestMapping(value = "/wish/remark/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String wish(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		User u = (User) session.getAttribute("u");
		String content = request.getParameter("content");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e11501);
		}
		WishRemark wishRemark = new WishRemark();
		wishRemark.setDelFlag(0);
		wishRemark.setRemark(content);
		wishRemark.setRemarkTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		wishRemark.setWishId(Integer.parseInt(id));
		patientService.addWishRemark(wishRemark);
		return success();
	}
	
	//发表说说
	@RequestMapping(value = "/discovery/add", method = RequestMethod.POST)
	@ResponseBody
	public String addDiscovery(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		String[] imageBase64s=request.getParameterValues("imageBase64s");
		String content = request.getParameter("content");
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e10002);
		}
		List<String> imageUrls = null;
		if(!ValidateUtil.isEmpty(imageBase64s)){
			imageUrls =upload(session,request,imageBase64s);
		}
		Discovery discovery = new Discovery();
		discovery.setPostTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		discovery.setVoteCount(0);
		discovery.setRemarkCount(0);
		discovery.setDelFlag(0);
		discovery.setUsername(u.getName());
		discovery.setProfileImageUrl(u.getProfileImageUrl());
		discovery.setContent(content);
		patientService.addDiscovery(discovery,imageUrls);
		
		return success();
	}
	//对说说进行评价
	@RequestMapping(value = "/discovery/remark/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String addDiscoveryRemark(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		
		String remark = request.getParameter("remark");
		if(ValidateUtil.isEmpty(remark)){
			return error(ErrorCodeUtil.e10002);
		}
		DiscoveryRemark discoveryRemark = new DiscoveryRemark();
		discoveryRemark.setDiscoveryId(Integer.parseInt(id));
		discoveryRemark.setPostTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		discoveryRemark.setRemark(remark);
		discoveryRemark.setRemarkUserName(u.getName());
		discoveryRemark.setRemarkUserProfile(u.getProfileImageUrl());
		patientService.addDiscoveryRemark(discoveryRemark);
		return success();
	}
	//说说详情
	@RequestMapping(value = "/discovery/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getDiscoveryDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(ValidateUtil.isEmpty(id)){
			return error(ErrorCodeUtil.e11403);
		}
		Discovery d = patientService.getDiscoveryDetail(Integer.parseInt(id));
		if(d!=null){
			d.setPostTime(DateUtil.timeDiffer(DateUtil.parse(d.getPostTime(), DateUtil.DATETIME_PATTERN), DateUtil.parse(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN), DateUtil.DATETIME_PATTERN)));
			List<DiscoveryImage> imageList = patientService.getDiscoveryImageListByDiscoveryId(d.getId());
			if(imageList!=null&&imageList.size()>0){
				for(DiscoveryImage image : imageList){
					image.setImageUrl(ConstantUtil.DOMAIN+image.getImageUrl());
				}
			}
			List<DiscoveryRemark> remarkList = d.getRemarkList();
			if(remarkList!=null&&remarkList.size()>0){
				for(DiscoveryRemark remark : remarkList){
					remark.setPostTime(DateUtil.timeDiffer(DateUtil.parse(remark.getPostTime(), DateUtil.DATETIME_PATTERN), DateUtil.parse(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN), DateUtil.DATETIME_PATTERN)));
				}
			}
			d.setImageList(imageList);
		}
		return JsonUtil.object2String(d);
	}
	//获取发现列表
	@RequestMapping(value = "/discovery/list", method = RequestMethod.GET)
	@ResponseBody
	public String getDiscoveryRemarkList(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		List<Discovery> list = patientService.getDiscoveryList();
		if(list!=null&&list.size()>0){
			for(Discovery d : list){
				d.setProfileImageUrl(ConstantUtil.DOMAIN+d.getProfileImageUrl());
				d.setPostTime(DateUtil.timeDiffer(DateUtil.parse(d.getPostTime(), DateUtil.DATETIME_PATTERN), DateUtil.parse(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN), DateUtil.DATETIME_PATTERN)));
				List<DiscoveryImage> imageList = patientService.getDiscoveryImageListByDiscoveryId(d.getId());
				
				if(imageList!=null&&imageList.size()>0){
					for(DiscoveryImage image : imageList){
						image.setImageUrl(ConstantUtil.DOMAIN+image.getImageUrl());
					}
				}
				if(imageList!=null&&imageList.size()>0){
					d.setImageList(imageList);
				}
			}
		}
		return JsonUtil.object2String(list);
	}
}
