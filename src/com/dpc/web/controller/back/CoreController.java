package com.dpc.web.controller.back;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.FeedBack;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.service.IBackDoctorService;
import com.dpc.web.service.ICoreService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/back",produces = {"application/json;charset=UTF-8"})
public class CoreController extends BaseController{
	
	@Autowired
	IBackDoctorService backDoctorService;
	@Autowired
	ICoreService coreService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpSession session,HttpServletRequest request) throws IOException{
		return "/back/index";
	}
	
	//用户反馈列表
	@RequestMapping(value = "/feedback/list", method = RequestMethod.GET)
	public String getFeedBackList(HttpSession session,HttpServletRequest request) throws IOException{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String username = request.getParameter("username");
		String status = request.getParameter("status");
		
		FeedBack feedBack = new FeedBack();
		if(!ValidateUtil.isEmpty(startDate) && !ValidateUtil.isEmpty(endDate)){
			feedBack.setStartDate(startDate);
			feedBack.setEndDate(endDate);
		}
		if(!ValidateUtil.isEmpty(username)){
			feedBack.setUsername(username);
		}
		if(!ValidateUtil.isEmpty(status)){
			feedBack.setStatus(Integer.parseInt(status));
		}
		
		Pager<FeedBack> page = coreService.findFeedBackByPaginaton(feedBack);
		request.setAttribute("page", page);
		return "/back/common/feedbackList";

	}
	//添加意见反馈
	@RequestMapping(value = "/feedback/add", method = RequestMethod.POST)
	@ResponseBody
	public String addFeedBack(HttpSession session,HttpServletRequest request) throws IOException{
		String content = request.getParameter("content");
		User u = (User) session.getAttribute("u");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e11700);
		}
		FeedBack feedBack = new FeedBack();
		feedBack.setContent(content);
		feedBack.setUserId(u.getId());
		feedBack.setFeedBackTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		feedBack.setStatus(0);
		coreService.addFeedBack(feedBack);
		return success();
		
	}
	
	//回复
	@RequestMapping(value = "/feedback/replay", method = RequestMethod.POST)
	@ResponseBody
	public String feedbackReply(HttpSession session,HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		FeedBack feedBack = new FeedBack();
		feedBack.setId(Integer.parseInt(id));
		feedBack.setReply(content);
		feedBack.setStatus(1);
		coreService.updateFeedBack(feedBack);
		return success();
	}
}
