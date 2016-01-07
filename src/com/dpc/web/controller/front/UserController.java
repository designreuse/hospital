package com.dpc.web.controller.front;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dpc.utils.Base64;
import com.dpc.utils.DateUtil;
import com.dpc.utils.ErrorCodeProperties;
import com.dpc.utils.ErrorCodeUtil;
import com.dpc.utils.JsonUtil;
import com.dpc.utils.MD5Encoder;
import com.dpc.utils.RandomNumberGenerator;
import com.dpc.utils.SMSUtil;
import com.dpc.utils.StringUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.utils.memcached.MemSession;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.Patient;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.service.IDoctorService;
import com.dpc.web.service.IPatientService;
import com.dpc.web.service.IUserService;

@Controller
@RequestMapping(value="/u",produces = {"application/json;charset=UTF-8"})
public class UserController extends BaseController{
	
	@Autowired
	IUserService userService;
	@Autowired
	IDoctorService doctorService;
	@Autowired
	IPatientService patientService;
	
	@RequestMapping(value = "/verificationCode", method = RequestMethod.GET)
	@ResponseBody
	public String getVerificationCode(HttpServletRequest request) throws IOException{
		ErrorCodeProperties pro = ErrorCodeProperties.getInstance();
		String mobile = request.getParameter("mobile");
		if(ValidateUtil.isEmpty(mobile)){
			return error(ErrorCodeUtil.e11001);
		}
		String[] ret = SMSUtil.sendSMS(mobile);
		if(!ret[0].equals("000000")){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error_code", ret[0]);
			map.put("error", pro.getProperties(ret[0]));
			return JsonUtil.object2String(map);
		}else{
			//存入session
			MemSession session = MemSession.getSession(mobile,true,"ten_min");
			session.setAttribute("verifycode", ret[1], "ten_min");
		}
		return success();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(HttpServletRequest request) throws IOException{
		String registerType = request.getParameter("registerType"); //注册类型：1-医生注册；2-病人注册
		String mobile = request.getParameter("mobile"); //密码
		String verifycode = request.getParameter("verifycode"); //密码
		String password = request.getParameter("password"); //密码
		String salt = UUID.randomUUID().toString();
		if(ValidateUtil.isEmpty(registerType)){
			return error(ErrorCodeUtil.e11004);
		}
		if(ValidateUtil.isEmpty(mobile)){
			return error(ErrorCodeUtil.e11001);
		}
		if(!ValidateUtil.isMobile(mobile)){
			return error(ErrorCodeUtil.e11002);
		}
		if(ValidateUtil.isEmpty(password)){
			return error(ErrorCodeUtil.e11003);
		}
		//是否已经注册
		User user = new User();
		user.setUsername(mobile);
		user = userService.getUser(user);
		if(user!=null){
			return error(ErrorCodeUtil.e11008);
		}
		//短信验证码校验
		MemSession session = MemSession.getSession(mobile,false,"ten_min");
		if(session == null){
			return error(ErrorCodeUtil.e11006);
		}
		if(session.getMap() == null){
			return error(ErrorCodeUtil.e11006);
		}
		if(session.getAttribute("verifycode")!=null){
			String randomCode = session.getAttribute("verifycode").toString();
			if(!randomCode.equals(verifycode)){
				return error(ErrorCodeUtil.e11007);
			}
		}else{
			return error(ErrorCodeUtil.e11006);
		}
		//注册用户
		User u = new User();
		u.setUsername(mobile);
		u.setPassword(MD5Encoder.encrypt(password, salt));
		u.setSalt(salt);
		u.setRegisterType(Integer.parseInt(registerType));
		u.setRegisterTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		userService.register(u);
		//添加医生基本信息
		if(registerType.equals("1")){
			//医生
			Doctor doctor = new Doctor();
			doctor.setUserId(u.getId());
			doctor.setScore(0);
			doctor.setTotalPatient(0);
			doctor.setVerifyed(0);
			doctorService.addDoctorWithRegister(doctor);
			
			Doctor d = new Doctor();
			d.setId(doctor.getId());
			d.setDoctorIdentityPlain(doctor.getUserId().toString()+RandomNumberGenerator.generateNumber(3));
			d.setDoctorIdentity(MD5Encoder.encrypt(doctor.getUserId().toString()+RandomNumberGenerator.generateNumber(3)));
			doctorService.updateDoctor(d);
		}
		if(registerType.equals("2")){
			//病人
			Patient patient = new Patient();
			patient.setScore(0);
			patient.setUserId(u.getId());
			patientService.addPatient(patient);
		}
		return success();
	}
	
	@RequestMapping(value = "/pwd/reset", method = RequestMethod.POST)
	@ResponseBody
	public String updatePwd(HttpSession session,HttpServletRequest request) throws IOException{
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String accessToken = request.getParameter("accessToken");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		Integer uid = 0;
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}else{
			uid = u.getId();
		}
		u = new User();
		u.setId(uid);
		u = userService.getUser(u);
		if(!u.getPassword().equals(MD5Encoder.encrypt(oldPwd, u.getSalt()))){
			return error(ErrorCodeUtil.e10003);
		}
		String salt = u.getSalt();
		u = new User();
		u.setId(uid);
		u.setPassword(MD5Encoder.encrypt(newPwd, salt));
		userService.updateUser(u);
		return success();
	}
	
	@RequestMapping(value = "/pwd/forget/verifycode", method = RequestMethod.GET)
	@ResponseBody
	public String getVerifyCodeGetPwd(HttpServletRequest request) throws IOException{
		ErrorCodeProperties pro = ErrorCodeProperties.getInstance();
		String mobile = request.getParameter("mobile");
		if(ValidateUtil.isEmpty(mobile)){
			return error(ErrorCodeUtil.e11001);
		}
		String[] ret = SMSUtil.sendSMS(mobile);
		if(!ret[0].equals("000000")){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error_code", ret[0]);
			map.put("error", pro.getProperties(ret[0]));
			return JsonUtil.object2String(map);
		}else{
			//存入session
			MemSession session = MemSession.getSession("fgtpwd"+mobile,true,"ten_min");
			session.setAttribute("verifycode", ret[1], "ten_min");
		}
		return success();
	}
	@RequestMapping(value = "/pwd/forget", method = RequestMethod.POST)
	@ResponseBody
	public String forgetPwd(HttpServletRequest request) throws IOException{
		String verifycode = request.getParameter("verifycode");
		String mobile = request.getParameter("mobile");
		String newPwd = request.getParameter("newPwd");
		String confirmPwd = request.getParameter("confirmPwd");
		if(ValidateUtil.isEmpty(newPwd)){
			return error(ErrorCodeUtil.e11003);
		}
		if(ValidateUtil.isEmpty(confirmPwd)){
			return error(ErrorCodeUtil.e11009);
		}
		if(!confirmPwd.equals(newPwd)){
			return error(ErrorCodeUtil.e11010);
		}
		User user = new User();
		user.setMobile(mobile);
		User u = userService.getUser(user);
		Integer uid = u.getId();
		String salt = u.getSalt();
		//短信验证码校验
		MemSession s = MemSession.getSession("fgtpwd"+mobile,false,"ten_min");
		if(s == null){
			return error(ErrorCodeUtil.e11006);
		}
		if(s.getMap() == null){
			return error(ErrorCodeUtil.e11006);
		}
		if(s.getAttribute("verifycode")!=null){
			String randomCode = s.getAttribute("verifycode").toString();
			if(!randomCode.equals(verifycode)){
				return error(ErrorCodeUtil.e11007);
			}
		}else{
			return error(ErrorCodeUtil.e11006);
		}
		u = new User();
		u.setId(uid);
		u.setPassword(MD5Encoder.encrypt(newPwd, salt));
		userService.updateUser(u);
		
		return success();
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		String username = request.getParameter("username"); 
		String password = request.getParameter("password"); //密码
		if(ValidateUtil.isEmpty(username)){
			return error(ErrorCodeUtil.e11001);
		}
		if(!ValidateUtil.isMobile(username)){
			return error(ErrorCodeUtil.e11002);
		}
		if(ValidateUtil.isEmpty(password)){
			return error(ErrorCodeUtil.e11003);
		}
		User user=new User();
		user.setUsername(username);
		user=userService.getUser(user);

		//用户不存在
		if (user == null){
			return error(ErrorCodeUtil.e11100);
		}		
		//获得密码
		String pwd = user.getPassword();
		password =MD5Encoder.encrypt(password, user.getSalt());
		
		//比对密码
		if (!pwd.equals(password)) {
			return error(ErrorCodeUtil.e11101);
		}
		//session 默认30天
		MemSession session = MemSession.getSession("u" + user.getId(),true,"default");
		session.setAttribute("user",user,"default");
		//保存session
		String accessToken = StringUtil.getRandStr(6, false) + "-"+ (System.currentTimeMillis() + 12 * 60 * 60 * 1000) + "_"+ user.getId();
		session.setAttribute("accessToken", accessToken,"default");
		
		//设置返回值
		result.put("accessToken",Base64.encodeToString(accessToken.getBytes(),11));
		result.put("user", user);
				
		return success(result);
	}
	
	
	
	@RequestMapping(value = "/view/login", method = RequestMethod.GET)
	public String loginView(HttpSession session,HttpServletRequest request) throws IOException{
		return "login";
	}
}
