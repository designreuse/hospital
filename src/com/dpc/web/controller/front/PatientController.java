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
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;
import com.dpc.web.mybatis3.domain.Patient;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.mybatis3.domain.WishRemark;
import com.dpc.web.service.IPatientService;
import com.dpc.web.service.IUserService;

@Controller
@RequestMapping(value="/patient",produces = {"application/json;charset=UTF-8"})
public class PatientController extends BaseController{
	
	@Autowired
	IPatientService patientService;
	@Autowired
	IUserService userService;
	
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
		if(patientVO!=null&&patientVO.getProfileImageUrl()!=null&&patientVO.getProfileImageUrl()!=""){
			patientVO.setProfileImageUrl(ConstantUtil.DOMAIN+patientVO.getProfileImageUrl());
		}
		return JsonUtil.object2String(patientVO);
	}
	@RequestMapping(value = "/profile/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String updateProfile(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		String name = request.getParameter("name");
		String agender = request.getParameter("agender");
		String birthday = request.getParameter("birthday");
		String mobile = request.getParameter("mobile");
		
		String weight = request.getParameter("weight");
		
		String[] profileImage = request.getParameterValues("profileImageUrl");
		List<String> profileImageUrl = null;
		if(!ValidateUtil.isEmpty(profileImage)){
			profileImageUrl = upload(session,request,profileImage);
		}
		
		User user = new User();
		user.setId(u.getId());
		if(!ValidateUtil.isEmpty(mobile)){
			user.setMobile(mobile);
		}
		if(!ValidateUtil.isEmpty(name)){
			user.setName(name);
		}
		if(!ValidateUtil.isEmpty(agender)){
			user.setAgender(Integer.parseInt(agender));
		}
		if(!ValidateUtil.isEmpty(birthday)){
			user.setBirthday(birthday);
		}
		if(profileImageUrl!=null&&profileImageUrl.size()>0){
			user.setProfileImageUrl(profileImageUrl.get(0));
		}
		userService.updateUser(user);
		
		Patient patient = new Patient();
		patient.setUserId(u.getId());
		if(!ValidateUtil.isEmpty(weight)){
			patient.setWeight(Double.parseDouble(weight));
		}
		patientService.updatePatient(patient);
		return success();
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
		wish.setContent(content);
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
	//患者许愿评论
	@RequestMapping(value = "/wish/remark/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String wishRemark(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		User u = (User) session.getAttribute("u");
		String content = request.getParameter("content");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e11501);
		}
		WishRemark wishRemark = new WishRemark();
		wishRemark.setUserId(u.getId());
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
		discovery.setUserId(u.getId());
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
		discoveryRemark.setUserId(u.getId());
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
			d.setProfileImageUrl(ConstantUtil.DOMAIN+d.getProfileImageUrl());
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
					remark.setRemarkUserProfile(ConstantUtil.DOMAIN+remark.getRemarkUserProfile());
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
	
	
	//患者绑定医生或加医生好友
	@RequestMapping(value = "/patientBindDoctor", method = RequestMethod.POST)
	@ResponseBody
	public String patientBindDoctor(HttpSession session,HttpServletRequest request) throws IOException{
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		//输入号码，暂时用手机
		String mobile = request.getParameter("mobile");
		
		if(ValidateUtil.isEmpty(mobile)){
			return error(ErrorCodeUtil.e11001);
		}
		
		//通过医生号码找到医生的ID
		User user = new User();
		user.setMobile(mobile);
		user = userService.getUser(user);
		if(user == null){
			return error(ErrorCodeUtil.e11005);
		}		
		
		//查看当前患者和绑定医生是否已经绑定或已经是好友
		DoctorPatientRelation doctorPatientRelation = new DoctorPatientRelation();
		doctorPatientRelation.setDoctorId(user.getId());
		doctorPatientRelation.setPatientId(u.getId());
		//绑定信息是否已经发送
		doctorPatientRelation.setChecked(-1);
		if(patientService.hasRelationshipWithDoctor(doctorPatientRelation)){
			return error(ErrorCodeUtil.e11603);
		}
		doctorPatientRelation.setRelation(1);
		if(patientService.hasRelationshipWithDoctor(doctorPatientRelation)){
			return error(ErrorCodeUtil.e11600);
		}
		doctorPatientRelation.setRelation(2);
		if(patientService.hasRelationshipWithDoctor(doctorPatientRelation)){
			return error(ErrorCodeUtil.e11601);
		}
		DoctorPatientRelation dp = new DoctorPatientRelation();
		dp.setPatientId(u.getId());
		dp.setDoctorId(user.getId());
		dp.setDirection(1);
		dp.setPatientName(u.getName());
		//该患者是否已经和其他医生绑定
		if(patientService.hasBindWithDoctor(u.getId())){
			//好友关系
			dp.setRelation(2);
			patientService.patientBindDoctor(dp);
		}
		//绑定关系
		dp.setRelation(1);
		patientService.patientBindDoctor(dp);
		
		return success();
	}
}
