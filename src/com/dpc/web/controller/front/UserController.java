package com.dpc.web.controller.front;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dpc.utils.DateUtil;
import com.dpc.utils.ErrorCodeUtil;
import com.dpc.utils.MD5Encoder;
import com.dpc.utils.ValidateUtil;
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
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(HttpSession session,HttpServletRequest request) throws IOException{
		String registerType = request.getParameter("registerType"); //注册类型：1-医生注册；2-病人注册
		String mobile = request.getParameter("mobile"); //密码
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
		User u = (User) session.getAttribute("u");
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
		u = new User();
		u.setId(uid);
		u.setPassword(MD5Encoder.encrypt(newPwd, u.getSalt()));
		userService.updateUser(u);
		return success();
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpSession session,HttpServletRequest request) throws IOException{
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
		//获得盐值
		String salt = user.getSalt();
		//获得密码
		String pwd = user.getPassword();
		password = MD5Encoder.encrypt(password, salt);
		//比对密码
		if (!pwd.equals(password)) {
			return error(ErrorCodeUtil.e11101);
		}
		session.setAttribute("u", user);
		return success();
	}
	
	@RequestMapping(value = "/view/login", method = RequestMethod.GET)
	public String loginView(HttpSession session,HttpServletRequest request) throws IOException{
		return "login";
	}
}
