package com.dpc.web.controller.back;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.service.IBackDoctorService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/back",produces = {"application/json;charset=UTF-8"})
public class CoreController extends BaseController{
	
	@Autowired
	IBackDoctorService backDoctorService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpSession session,HttpServletRequest request) throws IOException{
		return "/back/index";
	}
	
	
}
