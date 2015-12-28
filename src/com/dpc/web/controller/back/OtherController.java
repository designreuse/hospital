package com.dpc.web.controller.back;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dpc.utils.ConstantUtil;
import com.dpc.utils.DateUtil;
import com.dpc.utils.JsonUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.BannerSlide;
import com.dpc.web.service.IOtherService;

@Controller
@RequestMapping(value="/back/other",produces = {"application/json;charset=UTF-8"})
public class OtherController extends BaseController{
	
	@Autowired
	IOtherService otherServic;
	
	@RequestMapping(value = "/bannerSlide/list", method = RequestMethod.GET)
	@ResponseBody
	public String getBannerSlideList(HttpSession session,HttpServletRequest request) throws IOException{
		String platform = request.getParameter("platform");
		
		List<BannerSlide> list = otherServic.getAllBannerSlide(platform);
		if(list!=null&&list.size()>0){
			for(BannerSlide bannerSlide : list){
				bannerSlide.setPicUrl(ConstantUtil.DOMAIN+bannerSlide.getPicUrl());
				Integer redirectType = bannerSlide.getRedirectType();
				
				
			}
		}
		return JsonUtil.object2String(list);
	}
//	
//	@RequestMapping(value = "/bannerSlide/list", method = RequestMethod.GET)
//	public String list(HttpSession session,HttpServletRequest request) throws IOException{
//		List<BannerSlide> list = otherServic.getAllBannerSlide(null);
//		request.setAttribute("list", list);
//		return "/back/other/bannerSlide/list";
//	}
	@RequestMapping(value = "/bannerSlide/view/add", method = RequestMethod.GET)
	public String addBannerSlideView(HttpSession session,HttpServletRequest request) throws IOException{
		return "/back/other/bannerSlide/add";
	}
	
	@RequestMapping(value = "/bannerSlide/add", method = RequestMethod.POST)
	public String addBannerSlide(HttpSession session,HttpServletRequest request) throws IOException{
		String redirectType = request.getParameter("redirectType");
		String redirectAddr = request.getParameter("redirectAddr");
		String description = request.getParameter("description");
		String platform = request.getParameter("platform");
		String sequence = request.getParameter("sequence");
		 
		List<MultipartFile> images = null;
		List<String> imageUrls = null;
		if (request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			images = req.getFiles("images");
			if(images!=null&&images.size()>0){
				for(MultipartFile file : images){
					imageUrls = upload(session,request,file);
				}
			}
		}
		
		if(imageUrls!=null&&imageUrls.size()>0){
			for(String url : imageUrls){
				BannerSlide bannerSlide = new BannerSlide();
				bannerSlide.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
				bannerSlide.setDelFlag(0);
				bannerSlide.setPicUrl(url);
				bannerSlide.setRedirectType(Integer.parseInt(redirectType));
				if(!ValidateUtil.isEmpty(redirectAddr)){
					bannerSlide.setRedirectAddr(redirectAddr);
				}
				if(!ValidateUtil.isEmpty(description)){
					bannerSlide.setDescription(description);
				}
				bannerSlide.setPlatform(Integer.parseInt(platform));
				bannerSlide.setSequence(Integer.parseInt(sequence));
				bannerSlide.setRedirectAddr(redirectAddr);
				otherServic.saveBannerSlide(bannerSlide);
			}
		}
		return "redirect:/back/other/bannerSlide/list";
	}
}
