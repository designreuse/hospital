package com.dpc.web.controller.back;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dpc.utils.ErrorCodeUtil;
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.utils.StringUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.utils.memcached.MemSession;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.VO.PatientVO;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.Discovery;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.Patient;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.service.IBackDoctorService;
import com.dpc.web.service.IBackPatientService;
import com.dpc.web.service.IPatientService;
import com.dpc.web.service.IUserService;
import com.google.gson.Gson;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
@RequestMapping(value="/back/patient",produces = {"application/json;charset=UTF-8"})
public class BackPatientController extends BaseController{
	
	@Autowired
	IBackPatientService backPatientService;
	@Autowired
	IPatientService patientService;
	@Autowired
	IUserService userService;
	
	@RequestMapping(value = "/list")
	public String register(HttpSession session,HttpServletRequest request) throws IOException{
		MemSession mem = MemSession.getSession("menu_" + session.getId(),true,"default");
		mem.setAttribute("menu", "patient", "default");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String startScore = request.getParameter("startScore");
		String endScore = request.getParameter("endScore");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		PatientVO p = new PatientVO();
		if(!ValidateUtil.isEmpty(startDate) && !ValidateUtil.isEmpty(endDate)){
			p.setStartDate(startDate);
			p.setEndDate(endDate);
		}
		if(!ValidateUtil.isEmpty(startScore) && !ValidateUtil.isEmpty(endScore)){
			p.setStartScore(Integer.parseInt(startScore));
			p.setEndScore(Integer.parseInt(endScore));
		}
		if(!ValidateUtil.isEmpty(username)){
			p.setUsername(username);
		}
		if(!ValidateUtil.isEmpty(name)){
			p.setName(name);
		}
		Pager<PatientVO> page = backPatientService.findByPaginaton(p);
		request.setAttribute("page", page);
		return "/back/patient/list";
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ModelAndView export(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		  //获得输出流，该输出流的输出介质是客户端浏览器
		  OutputStream output=response.getOutputStream();
		  response.reset();
		  response.setHeader("Content-disposition","attachment;filename=patient.xls");
		  response.setContentType("application/msexcel");
		  //创建可写入的Excel工作薄，且内容将写入到输出流，并通过输出流输出给客户端浏览
		  WritableWorkbook wk = Workbook.createWorkbook(output);
		  ///创建可写入的Excel工作表
		  WritableSheet sheet=wk.createSheet("患者列表", 0);
		 
		//把单元格（column, row）到单元格（column1, row1）进行合并。
		//mergeCells(column, row, column1, row1);
	    sheet.mergeCells(0,0,11,0);//单元格合并方法
		//创建WritableFont 字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、亮蓝色
		WritableFont titleFont=new WritableFont(WritableFont.createFont("黑体"),12,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		//创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
		WritableCellFormat titleFormat=new WritableCellFormat();
		//设置字体格式
		titleFormat.setFont(titleFont);
		//设置文本水平居中对齐
		titleFormat.setAlignment(Alignment.CENTRE);
		//设置文本垂直居中对齐
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		//设置自动换行
		titleFormat.setWrap(true);
		//添加Label对象，参数依次表示在第一列，第一行，内容，使用的格式
		Label lab_00=new Label(0,0,"患者一览表",titleFormat);
		//将定义好的Label对象添加到工作表上，这样工作表的第一列第一行的内容为‘学员考试成绩一览表’并应用了titleFormat定义的样式
		sheet.addCell(lab_00);
		WritableCellFormat cloumnTitleFormat=new WritableCellFormat();
		cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD,false));
		cloumnTitleFormat.setAlignment(Alignment.CENTRE);
		 
		Label lab_01=new Label(0,1,"用户名",cloumnTitleFormat);
		Label lab_11=new Label(1,1,"姓名",cloumnTitleFormat);
		Label lab_21=new Label(2,1,"性别",cloumnTitleFormat);
		Label lab_31=new Label(3,1,"出生日期",cloumnTitleFormat);
		Label lab_41=new Label(4,1,"手机号",cloumnTitleFormat);
		Label lab_51=new Label(5,1,"注册时间",cloumnTitleFormat);
		Label lab_61=new Label(6,1,"患者积分",cloumnTitleFormat);
		sheet.addCell(lab_01);
		sheet.addCell(lab_11);
		sheet.addCell(lab_21);
		sheet.addCell(lab_31);
		sheet.addCell(lab_41);
		sheet.addCell(lab_51);
		sheet.addCell(lab_61);
		
		sheet.getSettings().setDefaultColumnWidth(15);
		//获取所有的医生
		List<PatientVO> list = patientService.getAllPatient();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
			   PatientVO p = list.get(i);
			   WritableFont   wf2   =   new   WritableFont(WritableFont.ARIAL,11,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
		       WritableCellFormat wcfTitle = new WritableCellFormat(wf2);
		       wcfTitle.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.NONE,jxl.format.Colour.AUTOMATIC); //BorderLineStyle边框
		       wcfTitle.setAlignment(Alignment.CENTRE); //设置垂直对齐
			      
				sheet.addCell(new Label(0,i+2,p.getUsername(),wcfTitle));
				sheet.addCell(new Label(1,i+2,p.getName(),wcfTitle));
				sheet.addCell(new Label(2,i+2,p.getAgender(),wcfTitle));
				sheet.addCell(new Label(3,i+2,p.getBirthday(),wcfTitle));
				sheet.addCell(new Label(4,i+2,p.getMobile(),wcfTitle));
				sheet.addCell(new Label(5,i+2,p.getRegisterTime(),wcfTitle));
				sheet.addCell(new Label(6,i+2,p.getScore().toString(),wcfTitle));
			}
		}
		//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
		wk.write();
		//操作完成时，关闭对象，释放占用的内存空间  
		wk.close();
		//加下划线这部分代码是B/S模式中采用的输出方式，而不是输出到本地指定的磁盘目录。该代码表示将temp.xls的Excel文件通过应答实体（response）输出给请求的客户端浏览器，下载到客户端本地（保存或直接打开）。若要直接输出到磁盘文件可采用下列代码替换加下划线这部分代码
		File file=new File("D://temp.xls");
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		return new ModelAndView("redirect:/back/patient/list");
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String getPatientDetail(HttpSession session,HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
	
		PatientVO p = patientService.getProfile(Integer.parseInt(id));
		request.setAttribute("p", p);
		return "/back/patient/detail";
	}
	
	@RequestMapping(value = "/wish/list", method = RequestMethod.GET)
	public String getWishList(HttpSession session,HttpServletRequest request) throws IOException{
		MemSession mem = MemSession.getSession("menu_" + session.getId(),true,"default");
		mem.setAttribute("menu", "will", "default");
		String patientName = request.getParameter("patientName");
		String mobile = request.getParameter("mobile");
		String isComeTrue = request.getParameter("isComeTrue");
		Wish wish = new Wish();
		if(!ValidateUtil.isEmpty(patientName)){
			wish.setPatientName(patientName);
		}
		if(!ValidateUtil.isEmpty(isComeTrue) && !isComeTrue.equals("-1")){
			wish.setIsComeTrue(Integer.parseInt(isComeTrue));
		}
		if(!ValidateUtil.isEmpty(mobile)){
			wish.setMobile(mobile);
		}
		Pager<Wish> page = backPatientService.findWishListByPaginaton(wish);
		request.setAttribute("page", page);
		return "/back/patient/wishList";
	}
	@RequestMapping(value = "/wish/detail/{id}", method = RequestMethod.GET)
	public String getWishDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		Wish wish = backPatientService.getWishDetail(Integer.parseInt(id));
		request.setAttribute("wish", wish);
		return "/back/patient/wishDetail";
	}
	@RequestMapping(value = "/updatePatientView", method = RequestMethod.GET)
	public String updatePatientView(HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		
		PatientVO p = patientService.getProfile(Integer.parseInt(id));
		request.setAttribute("p", p);
		return "/back/patient/updatePatient";
	}
	@RequestMapping(value = "/updatePatient", method = RequestMethod.POST)
	public String updatePatient(HttpServletRequest request) throws IOException{
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String agender = request.getParameter("agender");
		String birthday = request.getParameter("birthday");
		String mobile = request.getParameter("mobile");
		String illProfile = request.getParameter("illProfile");
		
		String weight = request.getParameter("weight");
		
		User user = new User();
		user.setId(Integer.parseInt(userId));
		
		user.setMobile(mobile);
		user.setName(name);
		user.setAgender(Integer.parseInt(agender));
		user.setBirthday(birthday);
		userService.updateUser(user);
		Patient patient = new Patient();
		patient.setUserId(Integer.parseInt(userId));
		patient.setWeight(Double.parseDouble(weight));
		patient.setIllProfile(illProfile);
		patientService.updatePatient(patient);
		return "redirect:/back/patient/list";
	}
	
	
}
