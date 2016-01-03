package com.dpc.web.controller.back;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.dpc.utils.DateUtil;
import com.dpc.utils.ErrorCodeUtil;
import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.controller.BaseController;
import com.dpc.web.mybatis3.domain.AcademicSupport;
import com.dpc.web.mybatis3.domain.Admin;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.User;
import com.dpc.web.mybatis3.domain.Wish;
import com.dpc.web.mybatis3.domain.WishRemark;
import com.dpc.web.service.IBackDoctorService;
import com.dpc.web.service.IDoctorService;
import com.dpc.web.service.IPatientService;
import com.dpc.web.service.IUserService;
import com.google.gson.Gson;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
@RequestMapping(value="/back/doctor",produces = {"application/json;charset=UTF-8"})
public class BackDoctorController extends BaseController{
	
	@Autowired
	IBackDoctorService backDoctorService;
	@Autowired
	IUserService userService;
	@Autowired
	IPatientService patientService;
	@Autowired
	IDoctorService doctorService;
	
	
	@RequestMapping(value = "/view/list", method = RequestMethod.GET)
	public String loginView(HttpSession session,HttpServletRequest request) throws IOException{
		return "/back/doctor/list";
	}
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String getDoctorDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		DoctorVO d = backDoctorService.getDoctorDetail(Integer.parseInt(id));
		request.setAttribute("d", d);
		return "/back/doctor/detail";
	}
	@RequestMapping(value = "/update/view/{id}", method = RequestMethod.GET)
	public String updateDoctorView(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		DoctorVO d = backDoctorService.getDoctorDetail(Integer.parseInt(id));
		request.setAttribute("d", d);
		return "/back/doctor/update";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateDoctor(HttpSession session,HttpServletRequest request,DoctorVO d) throws IOException{
		
		User u = new User();
		u.setId(d.getId());
		u.setAgender(Integer.parseInt(d.getAgender()));
		u.setName(d.getName());
		userService.updateUser(u);
		
		Doctor doctor = new Doctor();
		
		
		return "/back/doctor/detail";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getDoctorList(HttpSession session,HttpServletRequest request) throws IOException{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String exportRowCount = request.getParameter("exportRowCount");
		String verifyed = request.getParameter("verifyed");
		String startScore = request.getParameter("startScore");
		String endScore = request.getParameter("endScore");
		
		DoctorVO doctor = new DoctorVO();
		if(!ValidateUtil.isEmpty(startDate) && !ValidateUtil.isEmpty(endDate)){
			doctor.setStartDate(startDate);
			doctor.setEndDate(endDate);
		}
		if(!ValidateUtil.isEmpty(username)){
			doctor.setUsername(username);
		}
		if(!ValidateUtil.isEmpty(name)){
			doctor.setName(name);
		}
		if(!ValidateUtil.isEmpty(exportRowCount)){
			doctor.setExportRowCount(Integer.parseInt(exportRowCount));
		}
		if(!ValidateUtil.isEmpty(verifyed)){
			doctor.setVerifyed(Integer.parseInt(verifyed));
		}
		if(!ValidateUtil.isEmpty(startScore) && !ValidateUtil.isEmpty(endScore)){
			doctor.setStartScore(Integer.parseInt(startScore));
			doctor.setEndScore(Integer.parseInt(endScore));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		Pager<DoctorVO> page = backDoctorService.findByPaginaton(doctor);
		request.setAttribute("page", page);
		return "/back/doctor/list";
	}
	//诊后心得
	@RequestMapping(value = "/diaexp/list", method = RequestMethod.GET)
	public String getDiaExpList(HttpSession session,HttpServletRequest request) throws IOException{
		String doctorName = request.getParameter("doctorName");
		String status = request.getParameter("status");
		String creTime = request.getParameter("creTime");
		
		DiagnoseExperience dia = new DiagnoseExperience();
		if(!ValidateUtil.isEmpty(doctorName)){
			dia.setDoctorName(doctorName);
		}
		if(!ValidateUtil.isEmpty(status)){
			dia.setStatus(Integer.parseInt(status));
		}
		if(!ValidateUtil.isEmpty(creTime)){
			dia.setCreTime(creTime);
		}
		
		Pager<DiagnoseExperience> page = backDoctorService.findDiagnoseExperienceByPaginaton(dia);
		request.setAttribute("page", page);
		return "/back/doctor/diaexpList";
	}
	//诊后心得
	@RequestMapping(value = "/diaexp/detail/{id}", method = RequestMethod.GET)
	public String getDiaExpDetail(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		DiagnoseExperience dia = backDoctorService.getDiaExpDetail(id);
		request.setAttribute("dia", dia);
		return "/back/doctor/diaDetail";
	}
	//跳转到添加学术活动
	@RequestMapping(value = "/academicSupport/add/view", method = RequestMethod.GET)
	public String addAcademicSupportView(HttpSession session,HttpServletRequest request) throws IOException{
		
		
		return "/back/doctor/addAcademicSupport";
	}
	//添加学术活动
	@RequestMapping(value = "/academicSupport/add", method = RequestMethod.POST)
	public String addAcademicSupport(HttpSession session,HttpServletRequest request,AcademicSupport academicSupport) throws IOException{
		backDoctorService.addAcademicSupport(academicSupport);
		academicSupport.setDelFlag(0);
		return "/back/doctor/addAcademicSupport";
	}
	@RequestMapping(value = "/academicSupport/list", method = RequestMethod.GET)
	public String getAcademicSupportList(HttpSession session,HttpServletRequest request,AcademicSupport academicSupport) throws IOException{
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		String creTime = request.getParameter("creTime");
		
		AcademicSupport support = new AcademicSupport();
		if(!ValidateUtil.isEmpty(title)){
			support.setTitle(title);
		}
		if(!ValidateUtil.isEmpty(type)){
			support.setType(Integer.parseInt(type));
		}
		if(!ValidateUtil.isEmpty(creTime)){
			support.setCreTime(creTime);
		}
		
		Pager<AcademicSupport> page = backDoctorService.findAcademicSupportByPaginaton(support);
		request.setAttribute("page", page);
		return "/back/doctor/academicSupportList";
	}
	
	@RequestMapping(value = "/caseAnalysis/add/view", method = RequestMethod.GET)
	public String addCaseAnalysisView(HttpSession session,HttpServletRequest request,AcademicSupport academicSupport) throws IOException{
		return "/back/doctor/addCaseAnalysis";
	}
	@RequestMapping(value = "/wish/reply", method = RequestMethod.POST)
	@ResponseBody
	public String replyWish(HttpSession session,HttpServletRequest request,AcademicSupport academicSupport) throws IOException{
		Admin admin = (Admin) session.getAttribute("admin");
		
		String id = request.getParameter("id");
		String remark = request.getParameter("remark");
		WishRemark wr = new WishRemark();
		
		wr.setWishId(Integer.parseInt(id));
//		if(admin!=null){
//			wr.setRemarkUserName(admin.getName());
//		}else{
//			wr.setRemarkUserName("系统");
//		}
		wr.setDelFlag(0);
		wr.setRemark(remark);
		wr.setRemarkTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		wr.setRemark(remark);
		patientService.addWishRemark(wr);
		
		return success();
	}
	//患者许愿
	@RequestMapping(value = "/wish/cometrue/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String wishComeTrue(HttpSession session,HttpServletRequest request,@PathVariable("id") String id) throws IOException{
		Wish wish = new Wish();
		wish.setId(Integer.parseInt(id));
		wish.setIsComeTrue(1);
		patientService.updateWish(wish);
		return success();
	}
	//获取医生认证列表
	@RequestMapping(value = "/authentication/list", method = RequestMethod.GET)
	public String getAuthenticationList(HttpSession session,HttpServletRequest request) throws IOException{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		DoctorVO doctor = new DoctorVO();
		if(!ValidateUtil.isEmpty(startDate) && !ValidateUtil.isEmpty(endDate)){
			doctor.setStartDate(startDate);
			doctor.setEndDate(endDate);
		}
		Pager<DoctorVO> page = backDoctorService.findAuthenticationByPaginaton(doctor);
		request.setAttribute("page", page);
		
		return "/back/doctor/authenticationList";
	}
	
	//医生认证通过或不通过或删除
	//-1认证删除，0认证未审核，1认证通过，2认证未通过
	@RequestMapping(value = "/updateAuthStatus/{id}/{flag}", method = RequestMethod.POST)
	@ResponseBody
	public String updateAuthStatus(HttpSession session,HttpServletRequest request,@PathVariable("flag") String flag,@PathVariable("id") String id) throws IOException{
		Doctor d = new Doctor();
		d.setId(Integer.parseInt(id));
		d.setVerifyed(Integer.parseInt(flag));
		doctorService.updateDoctor(d);
		return success();
	}
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ModelAndView export(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		  //获得输出流，该输出流的输出介质是客户端浏览器
		  OutputStream output=response.getOutputStream();
		  response.reset();
		  response.setHeader("Content-disposition","attachment;filename=doctors.xls");
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
		Label lab_00=new Label(0,0,"医生一览表",titleFormat);
		//将定义好的Label对象添加到工作表上，这样工作表的第一列第一行的内容为‘学员考试成绩一览表’并应用了titleFormat定义的样式
		sheet.addCell(lab_00);
		WritableCellFormat cloumnTitleFormat=new WritableCellFormat();
		cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.NO_BOLD,false));
		cloumnTitleFormat.setAlignment(Alignment.CENTRE);
		 
		Label lab_01=new Label(0,1,"医生姓名",cloumnTitleFormat);
		Label lab_11=new Label(1,1,"注册手机",cloumnTitleFormat);
		Label lab_21=new Label(2,1,"医院名称",cloumnTitleFormat);
		Label lab_31=new Label(3,1,"科室",cloumnTitleFormat);
		Label lab_41=new Label(4,1,"技术职称",cloumnTitleFormat);
		Label lab_51=new Label(5,1,"二维码",cloumnTitleFormat);
		Label lab_61=new Label(6,1,"省份",cloumnTitleFormat);
		Label lab_71=new Label(7,1,"市",cloumnTitleFormat);
		Label lab_81=new Label(8,1,"区",cloumnTitleFormat);
		Label lab_91=new Label(9,1,"医生积分",cloumnTitleFormat);
		Label lab_101=new Label(10,1,"兑换现金",cloumnTitleFormat);
		Label lab_111=new Label(11,1,"注册时间",cloumnTitleFormat);
		sheet.addCell(lab_01);
		sheet.addCell(lab_11);
		sheet.addCell(lab_21);
		sheet.addCell(lab_31);
		sheet.addCell(lab_41);
		sheet.addCell(lab_51);
		sheet.addCell(lab_61);
		sheet.addCell(lab_71);
		sheet.addCell(lab_81);
		sheet.addCell(lab_91);
		sheet.addCell(lab_101);
		sheet.addCell(lab_111);
		
		//获取所有的医生
		List<DoctorVO> list = doctorService.getAllDoctorList();
		if(list!=null&&list.size()>0){
			for(int i=1;i<list.size();i++){
				DoctorVO d = list.get(i);
				sheet.addCell(new Label(0,i+1,d.getName()));
				sheet.addCell(new Label(1,i+1,d.getMobile()));
				sheet.addCell(new Label(2,i+1,"李明"));
				sheet.addCell(new Label(3,i+1,"李明"));
				sheet.addCell(new Label(4,i+1,"李明"));
				sheet.addCell(new Label(5,i+1,"李明"));
				sheet.addCell(new Label(6,i+1,"李明"));
				sheet.addCell(new Label(7,i+1,"李明"));
				sheet.addCell(new Label(8,i+1,"李明"));
				sheet.addCell(new Label(9,i+1,"李明"));
				sheet.addCell(new Label(10,i+1,"李明"));
				sheet.addCell(new Label(11,i+1,"李明"));
			}
		}
		//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
		wk.write();
		//操作完成时，关闭对象，释放占用的内存空间  
		wk.close();
		//加下划线这部分代码是B/S模式中采用的输出方式，而不是输出到本地指定的磁盘目录。该代码表示将temp.xls的Excel文件通过应答实体（response）输出给请求的客户端浏览器，下载到客户端本地（保存或直接打开）。若要直接输出到磁盘文件可采用下列代码替换加下划线这部分代码
		File file=new File("D://temp.xls");
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		return new ModelAndView("redirect:/back/doctor/list");
	}
}
