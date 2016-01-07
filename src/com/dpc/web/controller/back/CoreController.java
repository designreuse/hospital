package com.dpc.web.controller.back;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dpc.utils.Base64;
import com.dpc.utils.DateUtil;
import com.dpc.utils.ErrorCodeUtil;
import com.dpc.utils.MD5;
import com.dpc.utils.MD5Encoder;
import com.dpc.utils.StringUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.utils.memcached.MemSession;
import com.dpc.web.VO.Pager;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Admin;
import com.dpc.web.mybatis3.domain.City;
import com.dpc.web.mybatis3.domain.County;
import com.dpc.web.mybatis3.domain.DistrictList;
import com.dpc.web.mybatis3.domain.FeedBack;
import com.dpc.web.mybatis3.domain.Hospital;
import com.dpc.web.mybatis3.domain.Province;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.service.IBackDoctorService;
import com.dpc.web.service.ICoreService;
import com.dpc.web.service.IDistrictService;
import com.dpc.web.service.IUserService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping(value="/back",produces = {"application/json;charset=UTF-8"})
public class CoreController extends BaseController{
	
	@Autowired
	IBackDoctorService backDoctorService;
	@Autowired
	ICoreService coreService;
	@Autowired
	IUserService userService;
	@Autowired
	IDistrictService districtService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpSession session,HttpServletRequest request) throws IOException{
		return "/back/index";
	}
	@RequestMapping(value = "/manager/view", method = RequestMethod.GET)
	public String managerView(HttpSession session,HttpServletRequest request) throws IOException{
		return "/back/manager";
	}
	@RequestMapping(value = "/manager/add/view", method = RequestMethod.GET)
	public String addManagerView(HttpSession session,HttpServletRequest request) throws IOException{
		return "/back/addManager";
	}
	@RequestMapping(value = "/manager/login/view", method = RequestMethod.GET)
	public String managerLoginView(HttpSession session,HttpServletRequest request) throws IOException{
		return "/login";
	}
	@RequestMapping(value = "/manager/login", method = RequestMethod.GET)
	public String managerLogin(HttpSession session,HttpServletRequest request) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin admin = new Admin();
		admin.setUsername(username);
		String msg = "";
		boolean flag = true;
		admin = userService.getAdmin(admin);
		//用户不存在
		if (admin == null){
			msg = "用户名不存在";
			flag = false;
		}		
		//获得密码
		String pwd = admin.getPassword();
		password =MD5Encoder.encrypt(password, admin.getSalt());
		
		//比对密码
		if (!pwd.equals(password)) {
			msg = "密码不正确";
			flag = false;
		}
		if(flag){
			session.setAttribute("admin", admin);
			return "/back/index";
		}else{
			request.setAttribute("msg", msg);
			return "login";
		}
	}
	@RequestMapping(value = "/manager/logout", method = RequestMethod.GET)
	public String managerLogout(HttpSession session,HttpServletRequest request) throws IOException{
		request.getSession().invalidate();
		return "/login";
	}
	@RequestMapping(value = "/manager/add", method = RequestMethod.POST)
	public String addManager(HttpSession session,HttpServletRequest request) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cfmPwd = request.getParameter("cfmPwd");
		String name = request.getParameter("name");
		
		List<MultipartFile> images = null;
		List<String> imageUrls = null;
		if (request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			images = req.getFiles("profileImageUrl");
			if(images!=null&&images.size()>0){
				for(MultipartFile file : images){
					imageUrls = upload(session,request,file);
				}
			}
		}
		String salt = UUID.randomUUID().toString();
		Admin admin = new Admin();
		admin.setSalt(salt);
		admin.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		admin.setDelFlag(0);
		admin.setLevel(1);
		admin.setName(name);
		admin.setPassword(MD5Encoder.encrypt(password, salt));
		admin.setUsername(username);
		if(imageUrls!=null&&imageUrls.size()>0){
			admin.setProfileImageUrl(imageUrls.get(0));
		}
		
		userService.addAdmin(admin);
		return "/back/addManager";
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
		if(!ValidateUtil.isEmpty(status) && !status.equals("-1")){
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
		String accessToken = request.getParameter("accessToken");
		String content = request.getParameter("content");
		String contact = request.getParameter("contact");
		MemSession memSession = userService.getSessionByAccessToken(accessToken);
		//无效授权
		if (memSession == null){
			return error(ErrorCodeUtil.e10002);
		}
		User u = (User) memSession.getAttribute("user");
		if(u==null){
			return error(ErrorCodeUtil.e10002);
		}
		
		if(ValidateUtil.isEmpty(content)){
			return error(ErrorCodeUtil.e11700);
		}
		FeedBack feedBack = new FeedBack();
		feedBack.setContent(content);
		feedBack.setUserId(u.getId());
		feedBack.setContact(contact);
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
	
	@RequestMapping(value = "/district/init", method = RequestMethod.GET)
	@ResponseBody
	public String initDistrict(HttpSession session,HttpServletRequest request) throws IOException{

		Document doc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2010/index.html").post();
		Elements pros = doc.getElementsByClass("provincetr");
		for(Element e : pros){
			Elements provinceList = e.select("a[href]");
			for(Element a : provinceList){
				String ahref = a.attr("href");
				String code = ahref.substring(0,2);
				String name = a.text();
				Province province = new Province();
				province.setCode(code);
				province.setName(name);
				districtService.addProvince(province);
				Document cityDoc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2010/"+ahref).post();
				Elements citys = cityDoc.getElementsByClass("citytr");
				for(Element city : citys){
					Elements cityA = city.select("a");
					String cityhref = cityA.attr("href");
					String cityCode = cityA.get(0).text();
					String cityName = cityA.get(1).text();
					City c = new City();
					c.setCode(cityCode);
					c.setName(cityName);
					c.setPid(province.getId());
					districtService.addCity(c);
					Document countyDoc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2010/"+cityhref).post();
					Elements countys = countyDoc.getElementsByClass("countytr");
					for(Element county : countys){
						Elements countyA = county.select("a");
						if(countyA.size()>0){
							String countyACode = countyA.get(0).text();
							String countyAName = countyA.get(1).text();
							County cc = new County();
							cc.setCode(countyACode);
							cc.setName(countyAName);
							cc.setPid(c.getId());
							districtService.addCounty(cc);
						}
					}
				}
			}
		}
	
		return success();
	}
	
	@RequestMapping(value = "/hopital/import/view", method = RequestMethod.GET)
	public String importHospitalView(HttpServletRequest request) throws IOException{
		return "/back/common/importHospital";
	}
	
	@RequestMapping(value = "/hopital/import", method = RequestMethod.POST)
	public String importHospital(HttpServletRequest request) throws IOException{
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = req.getFiles("file");
		Map<String,String> map = new HashMap<String, String>();
		if(files!=null&&files.size()>0){
			for(MultipartFile file : files){
				InputStream is = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("aa.xls").getPath());
			    try{
		            Workbook rwb = Workbook.getWorkbook(is);  
		            Sheet st = rwb.getSheet("Sheet1");  
		            int rs = st.getColumns();  
		            int rows = st.getRows();  
	                for(int k=1;k<rows;k++){//行  
                        Cell hospitalCell = st.getCell(0,k);  
                        Cell locateCell = st.getCell(1,k);  
                        String hopital = hospitalCell.getContents();
                        String locate = locateCell.getContents();
                        
                        map.put(hopital, locate);
	                }
		            rwb.close();  
		        }  
		        catch(Exception e){  
		            e.printStackTrace();  
		        } 
			}
		}
		for(Entry<String,String> entry : map.entrySet()){
			String locate = entry.getValue();
			String hospital = entry.getKey();
			
			String[] districtArray = locate.split(",");
			String province = districtArray[0];
			String city = districtArray[1];
			if(districtArray.length==2){
				System.out.println(province+city);
				DistrictList idList = districtService.getunFullIdByName(province,city);
				Hospital h = new Hospital();
				h.setHospital(hospital);
				h.setLocate(idList.getPid()+"-"+idList.getCid());
				districtService.addHospital(h);
			}
			if(districtArray.length==3){
				String county = districtArray[2];
				System.out.println(province+city+county);
				DistrictList idList = districtService.getFullIdByName(province,city,county);
				if(idList==null){
					Hospital h = new Hospital();
					h.setHospital(hospital);
					h.setLocate("+"+province+city+county);
					districtService.addHospital(h);
				}else{
					Hospital h = new Hospital();
					h.setHospital(hospital);
					h.setLocate(idList.getPid()+"-"+idList.getCid()+"-"+idList.getCyid());
					districtService.addHospital(h);
				}
				
			}
			
		}
		return "/back/common/importHospital";
	}
}
