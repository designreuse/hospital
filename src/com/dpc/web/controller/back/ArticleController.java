package com.dpc.web.controller.back;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.dpc.utils.ConstantUtil;
import com.dpc.utils.DateUtil;
import com.dpc.utils.JsonUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.VO.Pager;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.service.IArticleService;

@Controller
@RequestMapping(value="/back/article",produces = {"application/json;charset=UTF-8"})
public class ArticleController extends BaseController{
	
	@Autowired
	IArticleService articleService;
	
	
	@RequestMapping(value = "/list/{type}/{category}", method = RequestMethod.GET)
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
	
	
	@RequestMapping(value = "/list/{type}", method = RequestMethod.GET)
	public ModelAndView doctorArticleList(HttpSession session,HttpServletRequest request,@PathVariable("type") String type) throws IOException{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String delFlag = request.getParameter("delFlag");
		String category = request.getParameter("category");
		String illType = request.getParameter("illType");
		Article article = new Article();
		if(!ValidateUtil.isEmpty(startDate)){
			article.setStartDate(startDate);
		}
		if(!ValidateUtil.isEmpty(endDate)){
			article.setEndDate(endDate);
		}
		if(!ValidateUtil.isEmpty(delFlag) && !delFlag.equals("-1")){
			article.setDelFlag(Integer.parseInt(delFlag));
		}
		if(!ValidateUtil.isEmpty(category) && !category.equals("-1")){
			article.setCategory(Integer.parseInt(category));
		}
		if(!ValidateUtil.isEmpty(illType) && !illType.equals("-1")){
			article.setIllType(Integer.parseInt(illType));
		}
		article.setType(Integer.parseInt(type));
		Map<String, Object> result = new HashMap<String, Object>();
		Pager<Article> page = articleService.findByPagination(article);
		result.put("page", page);
		if(type.equals("1")){
			return new ModelAndView("/back/article/doctor/articleList", result);
		}else{
			return new ModelAndView("/back/article/patient/articleList", result);
		}
		
	}
	@RequestMapping(value = "/view/doctor/add", method = RequestMethod.GET)
	public String addDoctorArticleView(HttpSession session,HttpServletRequest request) throws IOException{
		
		return "/back/article/doctor/addArticle";
	}
	@RequestMapping(value = "/view/patient/add", method = RequestMethod.GET)
	public String addPatientArticleView(HttpSession session,HttpServletRequest request) throws IOException{
		
		return "/back/article/patient/addArticle";
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String getArticleDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		Article article = articleService.getArticleDetailById(Integer.parseInt(id));
		article.setCoverImageUrl(ConstantUtil.DOMAIN+article.getCoverImageUrl());
		request.setAttribute("article", article);
		if(article.getType()==1){
			return "/back/article/doctor/articleDetail";
		}else{
			return "/back/article/patient/articleDetail";
		}
	}
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateArticleView(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		Article article = articleService.getArticleById(Integer.parseInt(id));
		article.setCoverImageUrl(ConstantUtil.DOMAIN+article.getCoverImageUrl());
		request.setAttribute("article", article);
		if(article.getType()==1){
			return "/back/article/doctor/updateArticle";
		}else{
			return "/back/article/patient/updateArticle";
		}
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delArticle(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		Article article = articleService.getArticleById(Integer.parseInt(id));
		Integer type = article.getType();
		articleService.delArticle(Integer.parseInt(id));
		return "redirect:/back/article/list/"+type;
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addDoctorArticle(HttpSession session,HttpServletRequest request,Article article) throws IOException{
		List<MultipartFile> images = null;
		List<String> imageUrls = null;
		if (request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			images = req.getFiles("coverImage");
			if(images!=null&&images.size()>0){
				for(MultipartFile file : images){
					imageUrls = upload(session,request,file);
				}
			}
		}
		if(!ValidateUtil.isEmpty(imageUrls)){
			article.setCoverImageUrl(imageUrls.get(0));
		}
		article.setDelFlag(0);
		articleService.saveArticle(article);
		return "redirect:/back/article/list/"+article.getType();
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateArticle(HttpSession session,HttpServletRequest request,Article article) throws IOException{
		List<MultipartFile> images = null;
		List<String> imageUrls = null;
		if (request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			images = req.getFiles("coverImage");
			if(images!=null&&images.size()>0){
				for(MultipartFile file : images){
					imageUrls = upload(session,request,file);
				}
			}
		}
		if(!ValidateUtil.isEmpty(imageUrls)){
			article.setCoverImageUrl(imageUrls.get(0));
		}
		article.setDelFlag(0);
		articleService.update(article);
		return "redirect:/back/article/list/"+article.getType();
	}
	
	
}
