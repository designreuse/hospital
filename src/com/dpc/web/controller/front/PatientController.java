
package com.dpc.web.controller.front;

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
import com.dpc.utils.ConstantUtil;
import com.dpc.utils.DateUtil;
import com.dpc.utils.ErrorCodeUtil;
import com.dpc.utils.JsonUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.utils.memcached.MemSession;
import com.dpc.web.VO.PatientVO;
import com.dpc.web.VO.WishVO;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Announcement;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.ArticleRemark;
import com.dpc.web.mybatis3.domain.DayLive;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.DiscoveryImage;
import com.dpc.web.mybatis3.domain.DiscoveryRemark;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;
import com.dpc.web.mybatis3.domain.Patient;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.mybatis3.domain.WishRemark;
import com.dpc.web.mybatis3.mapper.CityMapper;
import com.dpc.web.mybatis3.mapper.CountyMapper;
import com.dpc.web.mybatis3.mapper.ProvinceMapper;
import com.dpc.web.service.IArticleService;
import com.dpc.web.service.IDoctorService;
import com.dpc.web.service.IPatientService;
import com.dpc.web.service.IUserService;

@Controller
@RequestMapping(value="/patient",produces = {"application/json;charset=UTF-8"})
public class PatientController extends BaseController{
	
	@Autowired
	IPatientService patientService;
	@Autowired
	IUserService userService;
	@Autowired
	IDoctorService doctorService;
	@Autowired
	IArticleService articleService;
	
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CountyMapper countyMapper;
	//患者个人资料
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	@ResponseBody
	public String profile(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType()==1){
			return error(ErrorCodeUtil.e10000);
		}
		PatientVO patientVO = patientService.getProfile(u.getId());
		if(patientVO!=null&&patientVO.getProfileImageUrl()!=null&&patientVO.getProfileImageUrl()!=""){
			patientVO.setProfileImageUrl(ConstantUtil.DOMAIN+patientVO.getProfileImageUrl());
		}
		return JsonUtil.object2String(patientVO);
	}
	@RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateProfile(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType()==1){
			return error(ErrorCodeUtil.e10000);
		}
		String name = request.getParameter("name");
		String agender = request.getParameter("agender");
		String birthday = request.getParameter("birthday");
		String mobile = request.getParameter("mobile");
		
		String weight = request.getParameter("weight");
		
		User user = new User();
		user.setId(u.getId());
		if(!ValidateUtil.isEmpty(mobile)){
			if(!ValidateUtil.isMobile(mobile)){
				return error(ErrorCodeUtil.e11002);
			}
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
		String[] imageBase64s=request.getParameterValues("imageBase64s");
		List<String> imageUrls = null;
		if(!ValidateUtil.isEmpty(imageBase64s)){
			imageUrls =upload(session,request,imageBase64s);
		}
		String imageUrl = "";
		if(imageUrls!=null&&imageUrls.size()>0){
			imageUrl = imageUrls.get(0);
		}
		if(!ValidateUtil.isEmpty(imageUrl)){
			user.setProfileImageUrl(imageUrl);
		}
		if(!ValidateUtil.isEmpty(name) || !ValidateUtil.isEmpty(agender) || !ValidateUtil.isEmpty(birthday) || !ValidateUtil.isEmpty(mobile) || !ValidateUtil.isEmpty(imageUrl)){
			userService.updateUser(user);
		}
		
		Patient patient = new Patient();
		patient.setUserId(u.getId());
		if(!ValidateUtil.isEmpty(weight)){
			if(!ValidateUtil.isNumber(weight)){
				return error(ErrorCodeUtil.e11503);
			}
			patient.setWeight(Double.parseDouble(weight));
		}
		if(!ValidateUtil.isEmpty(weight)){
			patientService.updatePatient(patient);
		}
		return success();
	}
	
	
	
	//患者许愿
	@RequestMapping(value = "/wish", method = RequestMethod.POST)
	@ResponseBody
	public String wish(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		String content = request.getParameter("content");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType()==1){
			return error(ErrorCodeUtil.e10000);
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
	//患者 实现许愿
	@RequestMapping(value = "/wish/cometrue/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String wishComeTrue(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType()==2){
			return error(ErrorCodeUtil.e10000);
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
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType()==1){
			return error(ErrorCodeUtil.e10000);
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
	@RequestMapping(value = "/wish/remark", method = RequestMethod.POST)
	@ResponseBody
	public String wishRemark(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		String content = request.getParameter("content");
		String wishId = request.getParameter("wishId");
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e11501);
		}
		WishRemark wishRemark = new WishRemark();
		wishRemark.setUserId(u.getId());
		wishRemark.setDelFlag(0);
		wishRemark.setRemark(content);
		wishRemark.setRemarkTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		wishRemark.setWishId(Integer.parseInt(wishId));
		patientService.addWishRemark(wishRemark);
		return success();
	}
	
	//发表说说
	@RequestMapping(value = "/discovery/add", method = RequestMethod.POST)
	@ResponseBody
	public String addDiscovery(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType()==1){
			return error(ErrorCodeUtil.e10000);
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
		discovery.setType(2);
		discovery.setContent(content);
		patientService.addDiscovery(discovery,imageUrls);
		
		//记录日活
		//是否是绑定关系
		List<DoctorPatientRelation> bindDoctors = patientService.getBindDoctors(u.getId());
		if(bindDoctors!=null&&bindDoctors.size()>0){
			for(DoctorPatientRelation relation : bindDoctors){
				//daylive 2016-10-10 pid did
				MemSession dayliveSession = MemSession.getSession("daylive" + DateUtil.date2Str(new Date(), DateUtil.DATE_PATTERN)+u.getId()+relation.getDoctorId(),false,"default");
				if(dayliveSession!=null && dayliveSession.getMap()==null){
					//记录当前患者对应医生的日活
					DayLive dayLive = new DayLive();
					dayLive.setDoctorId(relation.getDoctorId());
					dayLive.setPatientId(u.getId());
					dayLive.setOperTime(DateUtil.date2Str(new Date(), DateUtil.DATE_PATTERN));
					patientService.addDayLive(dayLive);
					
					//给对应医生添加积分
					Doctor d = doctorService.getDoctorById(relation.getDoctorId());
					if(d!=null){
						Doctor doctor = new Doctor();
						doctor.setId(d.getId());
						doctor.setScore(d.getScore()+10);
						doctorService.updateDoctor(doctor);
					}
					MemSession.getSession("daylive" + DateUtil.date2Str(new Date(), DateUtil.DATE_PATTERN)+u.getId()+relation.getDoctorId(),true,"default");
				}
			}
		}
		return success();
	}
	//对说说进行评价
	@RequestMapping(value = "/discovery/remark/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String addDiscoveryRemark(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
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
		
		Discovery discovery = patientService.getDiscoveryById(Integer.parseInt(id));
		Discovery d = new Discovery();
		d.setId(Integer.parseInt(id));
		d.setRemarkCount(discovery.getRemarkCount()+1);
		patientService.updateDiscovery(d);
		return success();
	}
	//说说详情
	@RequestMapping(value = "/discovery/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getDiscoveryDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType()==1){
			return error(ErrorCodeUtil.e10000);
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
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
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
	
	//获取我的医生列表
	@RequestMapping(value = "/mydoctors", method = RequestMethod.GET)
	@ResponseBody
	public String getMyDoctors(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType() == 1){
			return error(ErrorCodeUtil.e10000);
		}
		List<Doctor> list = patientService.getMyDoctors(u.getId());
		if(list!=null&&list.size()>0){
			for(Doctor doctor : list){
				if(!ValidateUtil.isEmpty(doctor.getProfileImageUrl())){
					doctor.setProfileImageUrl(ConstantUtil.DOMAIN+doctor.getProfileImageUrl());
				}
			}
		}
		return JsonUtil.object2String(list);
	}
	//解除好友关系
	@RequestMapping(value = "/unbind", method = RequestMethod.POST)
	@ResponseBody
	public String unbind(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		String doctorId = request.getParameter("doctorId");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType() == 1){
			return error(ErrorCodeUtil.e10000);
		}
//		DoctorPatientRelation doctorPatientRelation = new DoctorPatientRelation();
//		doctorPatientRelation.setDoctorId(Integer.parseInt(doctorId));
//		doctorPatientRelation.setPatientId(u.getId());
//		doctorPatientRelation.setRelation(1);
//		if(patientService.hasRelationshipWithDoctor(doctorPatientRelation)){
//			return error(ErrorCodeUtil.e11604);
//		}
		patientService.unBindRelation(u.getId(),Integer.parseInt(doctorId));
		
		return success();
	}
	//获取我的医生详情
	@RequestMapping(value = "/mydoctor/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getMyDoctorDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType() == 1){
			return error(ErrorCodeUtil.e10000);
		}
		if(ValidateUtil.isEmpty(id)){
			return error(ErrorCodeUtil.e11802);
		}
		Doctor d = patientService.getMyDoctorDetail(Integer.parseInt(id));
		if(d!=null){
			if(!ValidateUtil.isEmpty(d.getProfileImageUrl())){
				d.setProfileImageUrl(ConstantUtil.DOMAIN+d.getProfileImageUrl());
			}
			
			String province = "";
			String city = "";
			String county = "";
			if(!ValidateUtil.isEmpty(d.getAddress())){
				String[] addressArr = d.getAddress().split("-");
				if(addressArr!=null&&addressArr.length>0){
					for(int j=0;j<addressArr.length;j++){
						if(j==0){
							 province = provinceMapper.selectByPrimaryKey(Integer.parseInt(addressArr[0])).getName();
						}
						if(j==1){
							 city = cityMapper.selectByPrimaryKey(Integer.parseInt(addressArr[1])).getName();
						}
						if(j==2){
							 county = countyMapper.selectByPrimaryKey(Integer.parseInt(addressArr[2])).getName();
						}
					}
				}
			}
			d.setAddress(province+" "+city+" "+county);
		}
		return JsonUtil.object2String(d);
	}
	//获取我绑定的医生和加为好友的医生的公告
	@RequestMapping(value = "/mydoctor/announcements", method = RequestMethod.GET)
	@ResponseBody
	public String getMydoctorAnnouncements(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		String id = request.getParameter("id");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType() == 1){
			return error(ErrorCodeUtil.e10000);
		}
		List<Announcement> list = patientService.getMydoctorAnnouncements(u.getId());
		if(list!=null&&list.size()>0){
			for(Announcement announcement : list){
				if(!ValidateUtil.isEmpty(announcement.getImageUrl())){
					announcement.setImageUrl(ConstantUtil.DOMAIN+announcement.getImageUrl());
				}
			}
		}
		return JsonUtil.object2String(list);
	}
	
	
	//患者绑定医生或加医生好友
	@RequestMapping(value = "/patientBindDoctor", method = RequestMethod.POST)
	@ResponseBody
	public String patientBindDoctor(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(u.getRegisterType()==1){
			return error(ErrorCodeUtil.e10000);
		}
		String doctorIdentity = request.getParameter("doctorIdentity");
		
		if(ValidateUtil.isEmpty(doctorIdentity)){
			return error(ErrorCodeUtil.e11800);
		}
		//通过医生号码找到医生的ID
		User user = userService.getDoctorByDoctorIdentity(doctorIdentity);
		if(user == null){
			return error(ErrorCodeUtil.e11801);
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
		//是否是绑定关系
		doctorPatientRelation.setChecked(1);
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
		dp.setBindTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		//该患者是否已经和其他医生绑定
		if(patientService.hasBindWithDoctor(u.getId())){
			//好友关系
			dp.setRelation(2);
			patientService.patientBindDoctor(dp);
		}else{
			//绑定关系
			dp.setRelation(1);
			patientService.patientBindDoctor(dp);
		}
		
		return success();
	}
	
	//获取某疾病下的文章
	@RequestMapping(value = "/ill/articles/{illType}", method = RequestMethod.GET)
	@ResponseBody
	public String getArticlesWithIll(HttpSession session,HttpServletRequest request,@PathVariable("illType") String illType) throws IOException{
		Article param = new Article();
		param.setIllType(Integer.parseInt(illType));
		Map<String, Object> result = new HashMap<String, Object>();
		//获取疾病下文章列表
		List<Article> articleList = articleService.getArticlesByIllType(Integer.parseInt(illType));
		if(articleList!=null&&articleList.size()>0){
			for(Article a : articleList){
				if(!ValidateUtil.isEmpty(a.getCoverImageUrl())){
					a.setCoverImageUrl(ConstantUtil.DOMAIN+a.getCoverImageUrl());
				}
			}
		}
		//获取心视频列表
		List<Article> vedioList = articleService.getHeartVedioList(param);
		if(vedioList!=null&&vedioList.size()>0){
			for(Article a : vedioList){
				if(!ValidateUtil.isEmpty(a.getCoverImageUrl())){
					a.setCoverImageUrl(ConstantUtil.DOMAIN+a.getCoverImageUrl());
				}
			}
		}
		//获取心漫画列表
		List<Article> cartoonList = articleService.getCartoonList(param);
		if(cartoonList!=null&&cartoonList.size()>0){
			for(Article a : cartoonList){
				if(!ValidateUtil.isEmpty(a.getCoverImageUrl())){
					a.setCoverImageUrl(ConstantUtil.DOMAIN+a.getCoverImageUrl());
				}
			}
		}
		result.put("heart_knowledge", articleList);
		result.put("heart_vedio", vedioList);
		result.put("heart_cartoon", cartoonList);
		
		String ret = JsonUtil.object2String(result);
		return ret;
	}
	//养心讲堂
	@RequestMapping(value = "/saveHeart/lecture", method = RequestMethod.GET)
	@ResponseBody
	public String getSaveHeartLecture(HttpSession session,HttpServletRequest request) throws IOException{
		Article param = new Article();
		param.setIllType(null);
		Map<String, Object> result = new HashMap<String, Object>();
		//获取疾病下文章列表
		List<Article> articleList = articleService.getArticlesLately();
				
		//获取心视频列表
		List<Article> vedioList = articleService.getHeartVedioList(param);
		
		//获取心漫画列表
		List<Article> cartoonList = articleService.getCartoonList(param);
		Integer a = 0;
		Integer b = 0;
		Integer c = 0;
		if(articleList!=null&&articleList.size()>0){
			if(cartoonList!=null&&cartoonList.size()>0){
				for(Article article : articleList){
					if(!ValidateUtil.isEmpty(article.getCoverImageUrl())){
						article.setCoverImageUrl(ConstantUtil.DOMAIN+article.getCoverImageUrl());
					}
				}
			}
			a= articleList.size();
		}
		if(vedioList!=null&&vedioList.size()>=0){
			if(vedioList!=null&&vedioList.size()>0){
				for(Article article : vedioList){
					if(!ValidateUtil.isEmpty(article.getCoverImageUrl())){
						article.setCoverImageUrl(ConstantUtil.DOMAIN+article.getCoverImageUrl());
					}
				}
			}
			b=vedioList.size();
		}
		if(cartoonList!=null&&cartoonList.size()>=0){
			if(cartoonList!=null&&cartoonList.size()>0){
				for(Article article : cartoonList){
					if(!ValidateUtil.isEmpty(article.getCoverImageUrl())){
						article.setCoverImageUrl(ConstantUtil.DOMAIN+article.getCoverImageUrl());
					}
				}
			}
			c=cartoonList.size();
		}
	/*	if(articleList!=null&&articleList.size()>=3){
			result.put("heart_knowledge", articleList.subList(0, 3));
		}else if(articleList!=null&&articleList.size()<3){
			result.put("heart_knowledge", articleList.subList(0, a));
		}
		if(vedioList!=null&&vedioList.size()>=3){
			result.put("heart_vedio", vedioList.subList(0, 3));
		}else if(vedioList!=null&&vedioList.size()<3){
			result.put("heart_vedio", vedioList.subList(0, b));
		}
		if(cartoonList!=null&&cartoonList.size()>=3){
			result.put("heart_cartoon", cartoonList.subList(0, 3));
		}else if(cartoonList!=null&&cartoonList.size()<3){
			result.put("heart_cartoon", cartoonList.subList(0, c));
		}*/
		
		if(articleList!=null&&articleList.size()>0){
			result.put("heart_knowledge", articleList.get(0));
		}
		if(vedioList!=null&&vedioList.size()>0){
			result.put("heart_vedio", vedioList.get(0));
		}
		if(cartoonList!=null&&cartoonList.size()>0){
			result.put("heart_cartoon", cartoonList.get(0));
		}
		String ret = JsonUtil.object2String(result);
		return ret;
	}
	
	
	
	@RequestMapping(value = "/article/list/{type}/{category}", method = RequestMethod.GET)
	@ResponseBody
	public String getDoctorArticleByCategory(HttpSession session,HttpServletRequest request,@PathVariable("type") String type,@PathVariable("category") String category) throws IOException{
		List<Article> list = articleService.getArticleByTypeAndCategory(Integer.parseInt(type),Integer.parseInt(category));
		if(list!=null&&list.size()>0){
			for(Article a : list){
				a.setCoverImageUrl(ConstantUtil.DOMAIN+a.getCoverImageUrl());
				a.setPostTime(DateUtil.date2Str(DateUtil.parse(a.getPostTime(), DateUtil.DATE_PATTERN), DateUtil.DATE_PATTERN));
			}
		}
		return JsonUtil.object2String(list);
	}
	@RequestMapping(value = "/article/remark", method = RequestMethod.POST)
	@ResponseBody
	public String articleRemark(HttpSession session,HttpServletRequest request) throws IOException{
		String articleId = request.getParameter("articleId");
		String remark = request.getParameter("remark");
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		ArticleRemark articleRemark = new ArticleRemark();
		articleRemark.setArticleId(Integer.parseInt(articleId));
		articleRemark.setDelFlag(0);
		articleRemark.setPostTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		articleRemark.setRemark(remark);
		articleRemark.setUserId(u.getId());
		articleService.addArticleRemark(articleRemark);
		
		//评论数
		Article a = articleService.getArticleById(Integer.parseInt(articleId));
		Integer remarkCount = a.getRemarkCount();
		if(remarkCount==null){
			remarkCount = 0;
		}
		a = new Article();
		a.setId(Integer.parseInt(articleId));
		a.setRemarkCount(remarkCount+1);
		articleService.update(a);
		
		return success();
	}
	@RequestMapping(value = "/article/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getArticleDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession != null){
			User u = (User) memSession.getAttribute("user");
			if(u!=null){
				//是否是绑定关系
				List<DoctorPatientRelation> bindDoctors = patientService.getBindDoctors(u.getId());
				if(bindDoctors!=null&&bindDoctors.size()>0){
					for(DoctorPatientRelation relation : bindDoctors){
						//daylive 2016-10-10 pid did
						MemSession dayliveSession = MemSession.getSession("daylive" + DateUtil.date2Str(new Date(), DateUtil.DATE_PATTERN)+u.getId()+relation.getDoctorId(),false,"default");
						if(dayliveSession!=null && dayliveSession.getMap()==null){
							//记录当前患者对应医生的日活
							DayLive dayLive = new DayLive();
							dayLive.setDoctorId(relation.getDoctorId());
							dayLive.setPatientId(u.getId());
							dayLive.setOperTime(DateUtil.date2Str(new Date(), DateUtil.DATE_PATTERN));
							patientService.addDayLive(dayLive);
							
							//给对应医生添加积分
							Doctor d = doctorService.getDoctorById(relation.getDoctorId());
							if(d!=null){
								Doctor doctor = new Doctor();
								doctor.setId(d.getId());
								doctor.setScore(d.getScore()+10);
								doctorService.updateDoctor(doctor);
							}
							MemSession.getSession("daylive" + DateUtil.date2Str(new Date(), DateUtil.DATE_PATTERN)+u.getId()+relation.getDoctorId(),true,"default");
						}
					}
				}
			}
		}
		Article a = articleService.getArticleDetail(Integer.parseInt(id));
		if(a!=null){
			List<ArticleRemark> remarkList = a.getRemarkList();
			if(remarkList!=null&&remarkList.size()>0){
				for(ArticleRemark aa : remarkList){
					if(!ValidateUtil.isEmpty(aa.getProfileImageUrl())){
						aa.setProfileImageUrl(ConstantUtil.DOMAIN+aa.getProfileImageUrl());
					}
				}
			}
			if(!ValidateUtil.isEmpty(a.getCoverImageUrl())){
				a.setCoverImageUrl(ConstantUtil.DOMAIN+a.getCoverImageUrl());
			}
		}
		return JsonUtil.object2String(a);
	}
	
}
