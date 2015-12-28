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
@RequestMapping(value="/back/doctor",produces = {"application/json;charset=UTF-8"})
public class BackDoctorController extends BaseController{
	
	@Autowired
	IBackDoctorService backDoctorService;
	
	@RequestMapping(value = "/view/list", method = RequestMethod.GET)
	public String loginView(HttpSession session,HttpServletRequest request) throws IOException{
		return "/back/doctor/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String register(HttpSession session,HttpServletRequest request) throws IOException{
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sord=request.getParameter("sord");
		String sidx=request.getParameter("sidx");
		String params = request.getParameter("params");
		
		PageEntity<DoctorVO> pageEntity = new PageEntity<DoctorVO>();
		pageEntity.setRows(Integer.parseInt(rows));
		pageEntity.setPage(Integer.parseInt(page));
		pageEntity.setOffset();
		pageEntity.setSord(sord);
		pageEntity.setSidx(sidx);
		DoctorVO doctor = new DoctorVO();
		pageEntity.setParams(doctor);
		
		PageResult<DoctorVO> pageResult = backDoctorService.getAllDoctorList(pageEntity);
		Gson gson = new Gson();
		String data = gson.toJson(pageResult);
		return data;
	}
	
	
}
