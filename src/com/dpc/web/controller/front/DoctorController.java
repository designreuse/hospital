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
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.PatientVO;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.Announcement;
import com.dpc.web.mybatis3.domain.CaseAnalysis;
import com.dpc.web.mybatis3.domain.CaseAnalysisCollection;
import com.dpc.web.mybatis3.domain.CaseAnalysisRemark;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceImage;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceRemark;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.DoctorPatientRelation;
import com.dpc.web.mybatis3.domain.HeartCircle;
import com.dpc.web.mybatis3.domain.HeartCircleImage;
import com.dpc.web.mybatis3.domain.HeartCircleRemark;
import com.dpc.web.mybatis3.domain.MedicalDynamic;
import com.dpc.web.mybatis3.domain.Patient;
import com.dpc.web.mybatis3.domain.TakeAcademicSupport;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.mapper.CityMapper;
import com.dpc.web.mybatis3.mapper.CountyMapper;
import com.dpc.web.mybatis3.mapper.ProvinceMapper;
import com.dpc.web.service.IArticleService;
import com.dpc.web.service.IBackDoctorService;
import com.dpc.web.service.IDistrictService;
import com.dpc.web.service.IDoctorService;
import com.dpc.web.service.IPatientService;
import com.dpc.web.service.IUserService;

@Controller
@RequestMapping(value="/doctor",produces = {"application/json;charset=UTF-8"})
public class DoctorController extends BaseController{
	
	@Autowired
	IDoctorService doctorService;
	
	@Autowired
	IArticleService articleService;

	@Autowired
	IPatientService patientService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IDistrictService districtService;
	
	@Autowired
	IBackDoctorService backDoctorService;
	
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CountyMapper countyMapper;
	
	//添加诊后心得
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	@ResponseBody
	public String getProfile(HttpSession session,HttpServletRequest request) throws IOException{
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
		
		DoctorVO d = doctorService.getDoctorProfile(u.getId());
		if(!ValidateUtil.isEmpty(d.getAddress())){
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
		if(!ValidateUtil.isEmpty(d.getProfileImageUrl())){
			d.setProfileImageUrl(ConstantUtil.DOMAIN+d.getProfileImageUrl());
		}
		return JsonUtil.object2String(d);
	}
	//病例精析列表
	@RequestMapping(value = "/caseAnalysis/list", method = RequestMethod.GET)
	@ResponseBody
	public String getCaseAnalysisList(HttpSession session,HttpServletRequest request) throws IOException{
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
		List<CaseAnalysis> list = backDoctorService.getCaseAnalysisList();
		if(list!=null&&list.size()>0){
			for(CaseAnalysis c : list){
				if(!ValidateUtil.isEmpty(c.getPostTime())){
					c.setPostTime(DateUtil.timeDiffer(DateUtil.parse(c.getPostTime(), DateUtil.DATETIME_PATTERN), DateUtil.parse(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN), DateUtil.DATETIME_PATTERN)));
				}
				if(!ValidateUtil.isEmpty(c.getIllCaseImage())){
					c.setIllCaseImage(ConstantUtil.DOMAIN+c.getIllCaseImage());
				}
			}
		}
		return JsonUtil.object2String(list);
	}
	//收藏病例精析
	@RequestMapping(value = "/caseAnalysis/collection", method = RequestMethod.POST)
	@ResponseBody
	public String caseAnalysisCollect(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		String caseId = request.getParameter("caseId");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		//是否已经收藏
		CaseAnalysisCollection c = new CaseAnalysisCollection();
		c.setUserId(u.getId());
		c.setCaseId(Integer.parseInt(caseId));
		c.setDelFlag(0);
		if(doctorService.hasCollectCaseAnalysis(c)){
			return error(ErrorCodeUtil.e12000);
		}
		CaseAnalysisCollection collection = new CaseAnalysisCollection();
		collection.setDelFlag(0);
		collection.setCollectTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		collection.setUserId(u.getId());
		collection.setCaseId(Integer.parseInt(caseId));
		doctorService.addCaseAnalysisCollection(collection);
		return success();
	}
	//收藏病例精析
	@RequestMapping(value = "/caseAnalysis/collection/list", method = RequestMethod.GET)
	@ResponseBody
	public String getCaseAnalysisCollectList(HttpSession session,HttpServletRequest request) throws IOException{
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
		List<CaseAnalysisCollection> list = doctorService.getCaseAnalysisCollectList(u.getId());
		if(list!=null&&list.size()>0){
			for(CaseAnalysisCollection c : list){
				List<CaseAnalysis> clist = c.getCaseAnalysisList();
				if(clist!=null&&clist.size()>0){
					for(CaseAnalysis ca : clist){
						if(!ValidateUtil.isEmpty(ca.getIllCaseImage())){
							ca.setIllCaseImage(ConstantUtil.DOMAIN+ca.getIllCaseImage());
						}
					}
				}
			}
		}
		return JsonUtil.object2String(list);
	}
	@RequestMapping(value = "/caseAnalysis/collection/del", method = RequestMethod.POST)
	@ResponseBody
	public String delCaseAnalysisCollect(HttpSession session,HttpServletRequest request) throws IOException{
		String collectId = request.getParameter("collectId");
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
		CaseAnalysisCollection collection = new CaseAnalysisCollection();
		collection.setId(Integer.parseInt(collectId));
		collection.setDelFlag(1);
		doctorService.delCaseAnalysisCollect(collection);
		return success();
	}
	//病例精析详情
	@RequestMapping(value = "/caseAnalysis/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getCaseAnalysisDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
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
		
		CaseAnalysis c = backDoctorService.getCaseAnalysisDetail(Integer.parseInt(id));
		if(c!=null){
			List<CaseAnalysisRemark> rmkList = c.getRemarkList();
			if(rmkList!=null&&rmkList.size()>0){
				for(CaseAnalysisRemark remark : rmkList){
					if(!ValidateUtil.isEmpty(remark.getProfileImageUrl())){
						remark.setProfileImageUrl(ConstantUtil.DOMAIN+remark.getProfileImageUrl());
					}
				}
			}
			c.setPostTime(DateUtil.timeDiffer(DateUtil.parse(c.getPostTime(), DateUtil.DATETIME_PATTERN), DateUtil.parse(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN), DateUtil.DATETIME_PATTERN)));
			if(!ValidateUtil.isEmpty(c.getIllCaseImage())){
				c.setIllCaseImage(ConstantUtil.DOMAIN+c.getIllCaseImage());
			}
		}
		return JsonUtil.object2String(c);
	}
	//病例精析详情
	@RequestMapping(value = "/caseAnalysis/remark", method = RequestMethod.POST)
	@ResponseBody
	public String caseAnalysisRemark(HttpSession session,HttpServletRequest request) throws IOException{
		String caseId = request.getParameter("caseId");
		String content = request.getParameter("content");
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
		
		CaseAnalysisRemark analysisRemark = new CaseAnalysisRemark();
		analysisRemark.setCaseId(Integer.parseInt(caseId));
		analysisRemark.setContent(content);
		analysisRemark.setDelFlag(0);
		analysisRemark.setPostTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		analysisRemark.setUserId(u.getId());
		doctorService.caseAnalysisRemark(analysisRemark);
		
		return success();
	}
	
	
	//添加诊后心得
	@RequestMapping(value = "/diagnose_experience/add", method = RequestMethod.POST)
	@ResponseBody
	public String addDiagnoseExperience(HttpSession session,HttpServletRequest request) throws IOException{
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
		diagnoseExperience.setIllDesc(illDesc);
		diagnoseExperience.setReward(0);
		diagnoseExperience.setDelFlag(0);
		diagnoseExperience.setStatus(Integer.parseInt(status));
		doctorService.addDiagnoseExperience(diagnoseExperience,imageUrls);
		
		return success();
	}
	
	
	//诊后心得
	@RequestMapping(value = "/diaexp/reward", method = RequestMethod.POST)
	public String diaExpReward(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		
		String diaExpId = request.getParameter("diaExpId");
		String score = request.getParameter("score");
		
		DiagnoseExperience dia = doctorService.getDiagnoseExperienceById(Integer.parseInt(diaExpId));
		Integer reward = dia.getReward();
		dia = new DiagnoseExperience();
		dia.setReward(reward+Integer.parseInt(score));
		dia.setId(Integer.parseInt(diaExpId));
		doctorService.updateDiagnoseExp(dia);
		
		//打赏人的积分减少
		Doctor d = doctorService.getDoctorById(u.getId());
		Integer ramainScore = d.getScore();
		d = new Doctor();
		d.setScore(ramainScore-Integer.parseInt(score));
		d.setUserId(u.getId());
		doctorService.updateDoctor(d);
		//积分增加
		DiagnoseExperience diaexp = doctorService.getDiagnoseExperienceById(Integer.parseInt(diaExpId));
		Integer doctorId = diaexp.getDoctorId();
		Doctor doctor = doctorService.getDoctorById(doctorId);
		Integer rs = doctor.getScore();
		doctor = new Doctor();
		doctor.setUserId(doctorId);
		doctor.setScore(rs+Integer.parseInt(score));
		doctorService.updateDoctor(doctor);
		return success();
	}
		
		
	@RequestMapping(value = "/diagnose_experience/remark", method = RequestMethod.POST)
	@ResponseBody
	public String diagnoseExperienceRemark(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		String diaExpId = request.getParameter("diaExpId"); 
		String content = request.getParameter("content"); 
		if(ValidateUtil.isEmpty(diaExpId)){
			return error(ErrorCodeUtil.e11212);
		}
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e11213);
		}
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		u = userService.getUserById(u.getId());
		
		DiagnoseExperienceRemark remark = new DiagnoseExperienceRemark();
		remark.setContent(content);
		remark.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		remark.setDiaExpId(Integer.parseInt(diaExpId));
		remark.setUserId(u.getId());
		remark.setDelFlag(0);
		
		doctorService.addDiagnoseExperienceRemark(remark);
		//评论数加1
		DiagnoseExperience dx = doctorService.getDiagnoseExperienceById(Integer.parseInt(diaExpId));
		DiagnoseExperience diaexp = new DiagnoseExperience();
		diaexp.setId(Integer.parseInt(diaExpId));
		diaexp.setRemarkCount(dx.getRemarkCount()+1);
		doctorService.updateDiagnoseExp(diaexp);
		return success();
	}
	@RequestMapping(value = "/diagnose_experience/detail/{diaExpId}", method = RequestMethod.GET)
	@ResponseBody
	public String getDiagnoseExperienceDetail(HttpSession session,HttpServletRequest request,@PathVariable("diaExpId") String diaExpId) throws IOException{
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
		DiagnoseExperience diagnoseExperience = doctorService.getDiagnoseExperienceDetail(Integer.parseInt(diaExpId));
		List<DiagnoseExperienceRemark>  remarkList = diagnoseExperience.getRemarkList();
		if(diagnoseExperience!=null && remarkList!=null && remarkList.size()>0){
			for(DiagnoseExperienceRemark remark : remarkList){
				remark.setProfileImage(ConstantUtil.DOMAIN+remark.getProfileImage());
			}
		}
		List<DiagnoseExperienceImage> images = doctorService.getDiagnoseExperienceImageByDiaExpId(Integer.parseInt(diaExpId));
		if(images!=null&&images.size()>0){
			for(DiagnoseExperienceImage image : images){
				image.setImageUrl(ConstantUtil.DOMAIN+image.getImageUrl());
			}
		}
		diagnoseExperience.setDiagnoseExpImgList(images);
		return JsonUtil.object2String(diagnoseExperience);
	}
	@RequestMapping(value = "/diagnose_experience/list", method = RequestMethod.GET)
	@ResponseBody
	public String getDiagnoseExperienceList(HttpSession session,HttpServletRequest request) throws IOException{
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
		u = userService.getUserById(u.getId());
		if(u.getRegisterType()==2){
			return error(ErrorCodeUtil.e10000);
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
		circle.setProfileImage(u.getProfileImageUrl());
		circle.setRemarkCount(0);
		circle.setDelFlag(0);
		circle.setType(2);
		doctorService.addHeartCircle(circle,imageUrls);
		return success();
	}
	@RequestMapping(value = "/heartcircle/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getHeartCircleDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
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
		
		if(ValidateUtil.isEmpty(id)){
			return error(ErrorCodeUtil.e11301);
		}
		HeartCircle circle = doctorService.getHeartCircleDetail(Integer.parseInt(id));
		if(circle!=null&&!ValidateUtil.isEmpty(circle.getProfileImage())){
			circle.setProfileImage(ConstantUtil.DOMAIN+circle.getProfileImage());
		}
		List<HeartCircleRemark> remarkList = circle.getRemarkList();
		if(remarkList!=null&&remarkList.size()>0){
			for(HeartCircleRemark remark : remarkList){
				remark.setProfileImage(ConstantUtil.DOMAIN+remark.getProfileImage());
			}
		}
		List<HeartCircleImage> imageList = doctorService.getHeartCircleImageListByHeartCircleId(Integer.parseInt(id));
		if(imageList!=null&&imageList.size()>0){
			for(HeartCircleImage image : imageList){
				image.setImageUrl(ConstantUtil.DOMAIN+image.getImageUrl());
			}
		}
		circle.setImageList(imageList);
		return JsonUtil.object2String(circle);
	}
	@RequestMapping(value = "/heartcircle/remark", method = RequestMethod.POST)
	@ResponseBody
	public String addHeartCircleRemark(HttpSession session,HttpServletRequest request) throws IOException{
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
		u = userService.getUserById(u.getId());
		String heartCircleId = request.getParameter("heartCircleId");
		String remark = request.getParameter("remark");
		if(ValidateUtil.isEmpty(heartCircleId)){
			return error(ErrorCodeUtil.e11301);
		}
		if(ValidateUtil.isEmpty(remark)){
			return error(ErrorCodeUtil.e11302);
		}
		HeartCircleRemark heartCircleRemark = new HeartCircleRemark();
		
		heartCircleRemark.setDoctorId(u.getId());
		heartCircleRemark.setRemark(remark);
		heartCircleRemark.setHeartCircleId(Integer.parseInt(heartCircleId));
		heartCircleRemark.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		doctorService.addHeartCircleRemark(heartCircleRemark);
		
		HeartCircle heartCircle = doctorService.getHeartCircleById(Integer.parseInt(heartCircleId));
		HeartCircle circle = new HeartCircle();
		circle.setId(Integer.parseInt(heartCircleId));
		circle.setRemarkCount(heartCircle.getRemarkCount()+1);
		doctorService.updateHeartCircle(circle);
		return success();
	}
	@RequestMapping(value = "/heartcircle/remark/list", method = RequestMethod.POST)
	@ResponseBody
	public String getHeartCircleRemarkList(HttpSession session,HttpServletRequest request) throws IOException{
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
	
	@RequestMapping(value = "/heartcircle/list", method = RequestMethod.GET)
	@ResponseBody
	public String getHeartCircleList(HttpSession session,HttpServletRequest request) throws IOException{
		List<HeartCircle> list = doctorService.getHeartCircleList();
		if(list!=null&&list.size()>0){
			for(HeartCircle circle : list){
				circle.setProfileImage(ConstantUtil.DOMAIN+circle.getProfileImage());
			}
		}
		return JsonUtil.object2String(list);
	}
	
	//医生上传认证证件
	@RequestMapping(value = "/verify/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadVerify(HttpSession session,HttpServletRequest request) throws IOException{
		String[] crtWithPhoto=request.getParameterValues("crtWithPhoto");
		String[] crtWithName=request.getParameterValues("crtWithName");
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
		List<String> crtWithPhotoUrls;
		List<String> crtWithNameUrls;
		if(!ValidateUtil.isEmpty(crtWithPhoto) && crtWithPhoto.length==1 && !ValidateUtil.isEmpty(crtWithName) && crtWithName.length==1){
			crtWithPhotoUrls =upload(session,request,crtWithPhoto);
			crtWithNameUrls =upload(session,request,crtWithName);
		}else{
			return error(ErrorCodeUtil.e11204);
		}
		if(crtWithPhotoUrls!=null&&crtWithPhotoUrls.size()>0&&crtWithNameUrls!=null&&crtWithNameUrls.size()>0){
			String crtWithPhotoUrl = crtWithPhotoUrls.get(0);
			String crtWithNameUrl = crtWithNameUrls.get(0);
			Doctor doctor = new Doctor();
			doctor.setUserId(u.getId());
			doctor.setCrtWithNameUrl(crtWithNameUrl);
			doctor.setCrtWithPhotoUrl(crtWithPhotoUrl);
			doctor.setCrtOperTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
			doctorService.updateDoctor(doctor);
		}
		return success();
	}
	
	//修改医生个人资料
	@RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateProfile(HttpSession session,HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		String[] imageBase64s=request.getParameterValues("imageBase64s");
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
		Doctor doctor = new Doctor();
		doctor.setUserId(u.getId());
		
		String hospital = request.getParameter("hospital");
		String technicalTitle = request.getParameter("technicalTitle");
		String teachingTitle = request.getParameter("teachingTitle");
		String department = request.getParameter("department");
		String address = request.getParameter("address");
		
		String name = request.getParameter("name");
		String agender = request.getParameter("agender");
		String birthday = request.getParameter("birthday");
		String mobile = request.getParameter("mobile");
		
		//t_doctor
		if(!ValidateUtil.isEmpty(hospital)){
			doctor.setHospital(hospital);
		}
		if(!ValidateUtil.isEmpty(technicalTitle)){
			doctor.setTechnicalTitle(technicalTitle);
		}
		if(!ValidateUtil.isEmpty(teachingTitle)){
			doctor.setTeachingTitle(teachingTitle);
		}
		if(!ValidateUtil.isEmpty(department)){
			doctor.setDepartment(department);
		}
		if(!ValidateUtil.isEmpty(address)){
			doctor.setAddress(address);
		}
		if(!ValidateUtil.isEmpty(hospital) || !ValidateUtil.isEmpty(technicalTitle) || !ValidateUtil.isEmpty(teachingTitle) || !ValidateUtil.isEmpty(department) || !ValidateUtil.isEmpty(address)){
			doctorService.updateDoctor(doctor);
		}
		
		//t_user
		User user = new User();
		user.setId(u.getId());
		if(!ValidateUtil.isEmpty(name)){
			user.setName(name);
		}
		if(!ValidateUtil.isEmpty(agender)){
			user.setAgender(Integer.parseInt(agender));
		}
		if(!ValidateUtil.isEmpty(birthday)){
			user.setBirthday(birthday);
		}
		if(!ValidateUtil.isEmpty(mobile)){
			if(!ValidateUtil.isMobile(mobile)){
				return error(ErrorCodeUtil.e11002);
			}
			user.setMobile(mobile);
		}
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
		if(!ValidateUtil.isEmpty(name) || !ValidateUtil.isEmpty(agender) || !ValidateUtil.isEmpty(mobile) || !ValidateUtil.isEmpty(birthday) || !ValidateUtil.isEmpty(imageUrl)){
			userService.updateUser(user);
		}
		return success();
	}
	//医生发布公告
	@RequestMapping(value = "/announcement/add", method = RequestMethod.POST)
	@ResponseBody
	public String addAnnouncement(HttpSession session,HttpServletRequest request) throws IOException{
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
		String content = request.getParameter("content");
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e11211);
		}
		Announcement announcement = new Announcement();
		announcement.setDoctorId(u.getId());
		announcement.setContent(content);
		announcement.setDelFlag(0);
		announcement.setPostTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		//暂时处理为空
		announcement.setImageUrl("");
		doctorService.addAnnouncement(announcement);
		return success();
	}
	//医生更改公告状态
	@RequestMapping(value = "/announcement/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAnnouncement(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
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
		if(ValidateUtil.isEmpty(id)){
			return error(ErrorCodeUtil.e11210);
		}
		Announcement announcement = new Announcement();
		announcement.setId(Integer.parseInt(id));
		announcement.setDelFlag(1);
		doctorService.updateAnnouncement(announcement);
		return success();
	}
	//获取医生公告列表
	@RequestMapping(value = "/announcement/list/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getAnnouncementListByDoctorId(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
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
		if(ValidateUtil.isEmpty(id)){
			return error(ErrorCodeUtil.e11210);
		}
		List<Announcement> list = doctorService.getAnnouncementListByDoctorId(Integer.parseInt(id));
		if(list!=null&&list.size()>0){
			for(Announcement a : list){
				if(!ValidateUtil.isEmpty(a.getImageUrl())){
					a.setImageUrl(ConstantUtil.DOMAIN+a.getImageUrl());
				}
				if(!ValidateUtil.isEmpty(a.getPostTime())){
					a.setPostTime(DateUtil.timeDiffer(DateUtil.parse(a.getPostTime(), DateUtil.DATETIME_PATTERN), DateUtil.parse(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN), DateUtil.DATETIME_PATTERN)));
				}
			}
		}
		return JsonUtil.object2String(list);
	}
	
	//获取患者绑定邀请列表（已绑定和未绑定）
	@RequestMapping(value = "/bind/list", method = RequestMethod.GET)
	@ResponseBody
	public String getBindList(HttpSession session,HttpServletRequest request) throws IOException{
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
		List<DoctorPatientRelation> list = doctorService.getBindList(u.getId());
		
		return JsonUtil.object2String(list);
	}
	//获取患者绑定邀请列表（已绑定和未绑定）
	@RequestMapping(value = "/mypatients", method = RequestMethod.GET)
	@ResponseBody
	public String getMyPatients(HttpSession session,HttpServletRequest request) throws IOException{
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
		List<DoctorPatientRelation> list = doctorService.getMyPatients(u.getId());
		
		return JsonUtil.object2String(list);
	}
	//医生接受或拒绝患者的绑定请求
	@RequestMapping(value = "/bind/acceptOrNot", method = RequestMethod.POST)
	@ResponseBody
	public String bindAcceptOrNot(HttpSession session,HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		String acceptOrNot = request.getParameter("acceptOrNot");//0拒绝  1接受
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
		//检查是否已经存在关系
		DoctorPatientRelation doctorPatientRelation = patientService.getDoctorPatientRelationById(Integer.parseInt(id));
		if(doctorPatientRelation!=null&&doctorPatientRelation.getRelation()==2&&doctorPatientRelation.getChecked()==1){
			return error(ErrorCodeUtil.e11601);
		}
		if(doctorPatientRelation!=null&&doctorPatientRelation.getRelation()==1&&doctorPatientRelation.getChecked()==1){
			return error(ErrorCodeUtil.e11602);
		}
		if(Integer.parseInt(acceptOrNot) == 0){
			doctorService.delRelation(Integer.parseInt(id));
		}else{
			doctorService.bindAcceptOrNot(Integer.parseInt(id),Integer.parseInt(acceptOrNot));
		}
		
		if(Integer.parseInt(acceptOrNot) == 1){
			//更新医生表的totalPatient
			Doctor d = doctorService.getDoctorById(u.getId());
			Integer totalPatient = d.getTotalPatient();
			Integer doctorId = d.getId();
			d = new Doctor();
			d.setId(doctorId);
			d.setTotalPatient(totalPatient+1);
			doctorService.updateDoctor(d);
		}
		
		return success();
	}
	
	//获取学术支持列表
	@RequestMapping(value = "/academicSupport/list", method = RequestMethod.GET)
	@ResponseBody
	public String getAcademicSupportList(HttpServletRequest request) throws IOException{
		List<AcademicSupport> list = doctorService.getAcademicSupportList();
		if(list!=null&&list.size()>0){
			for(AcademicSupport support : list){
				if(!ValidateUtil.isEmpty(support.getPromoteImage())){
					support.setPromoteImage(ConstantUtil.DOMAIN+support.getPromoteImage());
				}
			}
		}
		return JsonUtil.object2String(list);
	}
	
	//获取学术支持详情
	@RequestMapping(value = "/academicSupport/detail", method = RequestMethod.GET)
	@ResponseBody
	public String getAcademicSupportDetail(HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		String accessToken = request.getParameter("accessToken");
		Integer userScore = null;
		if(!ValidateUtil.isEmpty(accessToken)){
			MemSession memSession = userService.getSessionByAccessToken(accessToken);
			User u  = (User) memSession.getAttribute("user");
			//已经登录，需要获取该用户的当前积分
			Doctor d = new Doctor();
			d.setUserId(u.getId());
			List<Doctor> dlist = doctorService.getDoctorList(d);
			userScore = dlist.get(0).getScore();
		}
		AcademicSupport support = doctorService.getAcademicSupportDetail(Integer.parseInt(id));
		if(support!=null){
			if(!ValidateUtil.isEmpty(support.getPromoteImage())){
				support.setPromoteImage(ConstantUtil.DOMAIN+support.getPromoteImage());
			}
			if(userScore!=null){
				support.setUserScore(userScore);
			} 
			return JsonUtil.object2String(support);
		}
		return JsonUtil.object2String(null);
	}
	
	//参加学术会议
	@RequestMapping(value = "/academicSupport/takepart", method = RequestMethod.POST)
	@ResponseBody
	public String takePartActivity(HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		String accessToken = request.getParameter("accessToken");
		if(!ValidateUtil.isEmpty(accessToken)){
			MemSession memSession = userService.getSessionByAccessToken(accessToken);
			if(memSession==null){
				return error(ErrorCodeUtil.e10008);
			}
			User u = (User) memSession.getAttribute("user");
			//是否已经参会成功
			TakeAcademicSupport t = new TakeAcademicSupport();
			t.setAcademicSupId(Integer.parseInt(id));
			t.setUserId(u.getId());
			t = doctorService.getTakeAcademicSupport(t);
			if(t!=null){
				if(t.getExchangeStatus() == 0){
					return error(ErrorCodeUtil.e11305);
				}
				if(t.getExchangeStatus() == 1){
					return error(ErrorCodeUtil.e11304);
				}
			}
			//查看当前积分
			Doctor d = new Doctor();
			d.setUserId(u.getId());
			List<Doctor> dlist = doctorService.getDoctorList(d);
			Integer userScore = dlist.get(0).getScore();
			Integer     score = doctorService.getAcademicSupportDetail(Integer.parseInt(id)).getScore();
			if(userScore >= score){
				//用户积分够，直接扣除，参会成功
				TakeAcademicSupport takeAcademicSupport = new TakeAcademicSupport();
				takeAcademicSupport.setAcademicSupId(Integer.parseInt(id));
				takeAcademicSupport.setExchangeStatus(1);
				takeAcademicSupport.setUserId(u.getId());
				takeAcademicSupport.setExchangeTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
				doctorService.takePartActivity(takeAcademicSupport);
				
				//扣除积分
				Doctor doctor = new Doctor();
				doctor.setScore(userScore-score);
				doctor.setUserId(u.getId());
				doctorService.updateDoctor(doctor);
			}else{
				//用户积分不够，暂存
				TakeAcademicSupport takeAcademicSupport = new TakeAcademicSupport();
				takeAcademicSupport.setAcademicSupId(Integer.parseInt(id));
				takeAcademicSupport.setExchangeStatus(0);
				takeAcademicSupport.setUserId(u.getId());
				doctorService.takePartActivity(takeAcademicSupport);
			}
		}else{
			//系统外参会
			
			String name = request.getParameter("name");
			String mobile = request.getParameter("mobile");
			String address = request.getParameter("address");
			if(ValidateUtil.isEmpty(name)){
				return error(ErrorCodeUtil.e10006);
			}
			if(ValidateUtil.isEmpty(mobile)){
				return error(ErrorCodeUtil.e11001);
			}
			if(!ValidateUtil.isMobile(mobile)){
				return error(ErrorCodeUtil.e11002);
			}
			TakeAcademicSupport takeAcademicSupport = new TakeAcademicSupport();
			takeAcademicSupport.setAcademicSupId(Integer.parseInt(id));
			takeAcademicSupport.setExchangeStatus(0);
			takeAcademicSupport.setName(name);
			takeAcademicSupport.setMobile(mobile);
			takeAcademicSupport.setAddress(address);
			doctorService.takePartActivity(takeAcademicSupport);
		}
		
		return success();
	}
	@RequestMapping(value = "/mypatient/updateRemark", method = RequestMethod.POST)
	@ResponseBody
	public String updateRemark(HttpServletRequest request) throws IOException{
		//患者ID
		String userId = request.getParameter("userId");
		//备注
		String remark = request.getParameter("remark");
		String accessToken = request.getParameter("accessToken");
		if(ValidateUtil.isEmpty(accessToken)){
			return error(ErrorCodeUtil.e10007);
		}
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		if(memSession==null){
			return error(ErrorCodeUtil.e10008);
		}
		User u = (User) memSession.getAttribute("user");
		Integer doctorId = u.getId();
		DoctorPatientRelation doctorPatientRelation = new DoctorPatientRelation();
		doctorPatientRelation.setDoctorId(doctorId);
		doctorPatientRelation.setPatientId(Integer.parseInt(userId));
		doctorPatientRelation.setRemark(remark);
		doctorService.updateDoctorPatientRelation(doctorPatientRelation);
		return success();
	}
	@RequestMapping(value = "/mypatient/updateIllProfile", method = RequestMethod.POST)
	@ResponseBody
	public String updateIllProfile(HttpServletRequest request) throws IOException{
		//患者ID
		String userId = request.getParameter("userId");
		//备注
		String illProfile = request.getParameter("illProfile");
		String accessToken = request.getParameter("accessToken");
		if(ValidateUtil.isEmpty(accessToken)){
			return error(ErrorCodeUtil.e10007);
		}
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		if(memSession==null){
			return error(ErrorCodeUtil.e10008);
		}
		Patient patient = new Patient();
		patient.setUserId(Integer.parseInt(userId));
		patient.setIllProfile(illProfile);
		patientService.updatePatient(patient);
		return success();
	}
	//去兑换
	@RequestMapping(value = "/academicSupport/toExchange", method = RequestMethod.GET)
	@ResponseBody
	public String toExchange(HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		String accessToken = request.getParameter("accessToken");
		if(ValidateUtil.isEmpty(accessToken)){
			return error(ErrorCodeUtil.e10007);
		}
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		if(memSession==null){
			return error(ErrorCodeUtil.e10008);
		}
		User u = (User) memSession.getAttribute("user");
		//查看当前积分
		Doctor d = new Doctor();
		d.setUserId(u.getId());
		List<Doctor> dlist = doctorService.getDoctorList(d);
		Integer userScore = dlist.get(0).getScore();
		Integer     score = doctorService.getAcademicSupportDetail(Integer.parseInt(id)).getScore();
		if(userScore < score){
			return error(ErrorCodeUtil.e11303);
		}
		return success();
	}
	//去兑换
	@RequestMapping(value = "/bindinfo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getBindInfo(HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		Integer bindCountToday = doctorService.getBindListToday(Integer.parseInt(id));
		
		Integer bindCountTotal = doctorService.getBindListTotal(Integer.parseInt(id));

		Integer dayLiveTotal = doctorService.getDayLiveTotal(Integer.parseInt(id));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bindCountToday", bindCountToday);
		result.put("bindCountTotal", bindCountTotal);
		result.put("dayLiveTotal", dayLiveTotal);
		
		return JsonUtil.object2String(result);
	}
	//医生绑定患者
	@RequestMapping(value = "/doctorBindPatient", method = RequestMethod.POST)
	@ResponseBody
	public String doctorBindPatient(HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		String mobile = request.getParameter("mobile");
		if(ValidateUtil.isEmpty(accessToken)){
			return error(ErrorCodeUtil.e10007);
		}
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		if(memSession==null){
			return error(ErrorCodeUtil.e10008);
		}
		User u = (User) memSession.getAttribute("user");
		if(u.getRegisterType()==2){
			return error(ErrorCodeUtil.e10000);
		}
		if(ValidateUtil.isEmpty(mobile)){
			return error(ErrorCodeUtil.e11001);
		}
		if(!ValidateUtil.isMobile(mobile)){
			return error(ErrorCodeUtil.e11002);
		}
		Integer doctorId = u.getId();
		User user = new User();
		user.setMobile(mobile);
		user = userService.getUser(user);
		if(user==null){
			return error(ErrorCodeUtil.e11011);
		}
		Integer patientId = user.getId();
		//查看当前患者和绑定医生是否已经绑定或已经是好友
		DoctorPatientRelation doctorPatientRelation = new DoctorPatientRelation();
		doctorPatientRelation.setDoctorId(doctorId);
		doctorPatientRelation.setPatientId(patientId);
		
		//是否是绑定关系
		doctorPatientRelation.setChecked(1);
		doctorPatientRelation.setRelation(1);
		if(patientService.hasRelationshipWithDoctor(doctorPatientRelation)){
			return error(ErrorCodeUtil.e11606);
		}
		doctorPatientRelation.setRelation(2);
		if(patientService.hasRelationshipWithDoctor(doctorPatientRelation)){
			return error(ErrorCodeUtil.e11607);
		}
		DoctorPatientRelation dp = new DoctorPatientRelation();
		dp.setPatientId(patientId);
		dp.setDoctorId(doctorId);
		dp.setDirection(2);
		dp.setBindTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		//该患者是否已经和其他医生绑定
		if(patientService.hasBindWithDoctor(patientId)){
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
	@RequestMapping(value = "/doctorunBindPatient", method = RequestMethod.POST)
	@ResponseBody
	public String doctorunBindPatient(HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		String patientId = request.getParameter("patientId");
		if(ValidateUtil.isEmpty(accessToken)){
			return error(ErrorCodeUtil.e10007);
		}
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		if(memSession==null){
			return error(ErrorCodeUtil.e10008);
		}
		User u = (User) memSession.getAttribute("user");
		doctorService.doctorunBindPatient(u.getId(),Integer.parseInt(patientId));
		return success();
	}
	@RequestMapping(value = "/score/dayincrease", method = RequestMethod.POST)
	@ResponseBody
	public String getScoreIncreaseByDay(HttpServletRequest request) throws IOException{
		String accessToken = request.getParameter("accessToken");
		String patientId = request.getParameter("patientId");
		if(ValidateUtil.isEmpty(accessToken)){
			return error(ErrorCodeUtil.e10007);
		}
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		if(memSession==null){
			return error(ErrorCodeUtil.e10008);
		}
		User u = (User) memSession.getAttribute("user");
		doctorService.doctorunBindPatient(u.getId(),Integer.parseInt(patientId));
		return success();
	}
	@RequestMapping(value = "/mypatient/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public String getMyPatientInfo(HttpServletRequest request,@PathVariable("patientId") String patientId) throws IOException{
		String accessToken = request.getParameter("accessToken");
		if(ValidateUtil.isEmpty(accessToken)){
			return error(ErrorCodeUtil.e10007);
		}
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		if(memSession==null){
			return error(ErrorCodeUtil.e10008);
		}
		User u = (User) memSession.getAttribute("user");
		if(u.getRegisterType()==2){
			return error(ErrorCodeUtil.e10000);
		}
		Integer doctorId = u.getId();
		PatientVO patientVO = doctorService.getMyPatientInfo(doctorId,Integer.parseInt(patientId));
		if(patientVO==null){
			return error(ErrorCodeUtil.e11605);
		}
		if(!ValidateUtil.isEmpty(patientVO.getProfileImageUrl())){
			patientVO.setProfileImageUrl(ConstantUtil.DOMAIN+patientVO.getProfileImageUrl());
		}
		return JsonUtil.object2String(patientVO);
	}
}
