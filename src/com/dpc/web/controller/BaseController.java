package com.dpc.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.dpc.utils.ConstantUtil;
import com.dpc.utils.ErrorCodeUtil;
import com.dpc.utils.JsonUtil;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.service.IUserService;
import sun.misc.BASE64Decoder;


/**
 * 
 * @author DiWu
 *
 */
public class BaseController
{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected final Log loggerCtrl = LogFactory.getLog("controller");
	
	@Autowired
	IUserService userService;	

	
	/**
	 * base64批量上传图片
	 * 
	 * @param session
	 * @param images
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public List<String> upload(HttpSession session,HttpServletRequest request,String[] imageBase64s)
			throws IllegalStateException, IOException{
		List<String> results = new ArrayList<String>();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		
		String savePath = request.getServletContext().getRealPath("/upload");
		String filePath =  year + File.separator + month + File.separator + day + File.separator;
		
		for (String imageBase64 : imageBase64s) {
			
			if(ValidateUtil.isEmpty(imageBase64))
				continue;
			String fileNamePrefix = System.currentTimeMillis()+"";
			filePath = filePath+fileNamePrefix+".png";	
			
			BASE64Decoder decoder = new BASE64Decoder(); 
	        byte[] bytes1 = decoder.decodeBuffer(imageBase64);                  
	        ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);    
	        BufferedImage bi1 =ImageIO.read(bais); 
			  
			String realPath = savePath+File.separator+year+File.separator+month+File.separator+day;
	        
	        File dir=new File(realPath);
	        if(!dir.exists()){
	        	dir.mkdirs();
	        }
	        String fileName = realPath + File.separator + fileNamePrefix+".png";
	        File w2 = new File(fileName);//可以是jpg,png,gif格式    
	        ImageIO.write(bi1, "png", w2);//不管输出什么格式图片，此处不需改动   
	        String externalPath = year + "/" + month + "/" + day + "/" + fileNamePrefix+".png";
			results.add(ConstantUtil.IMAGE_PATH_EXTERNAL+externalPath);
		}
		return results;
	}
	/**
	 * base64批量上传图片
	 * 
	 * @param session
	 * @param images
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public List<String> upload(HttpSession session,HttpServletRequest request,MultipartFile file)throws IllegalStateException, IOException{
		List<String> results = new ArrayList<String>();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		
		String savePath = request.getServletContext().getRealPath("/upload");
		String filePath =  year + File.separator + month + File.separator + day + File.separator;
		String fileName = System.currentTimeMillis()+".png";
		String finalpath = savePath+File.separator+filePath+fileName;
		
		if(file!=null && !file.isEmpty()){
            //获取存储文件路径
            File fileDir=new File(savePath+File.separator+filePath);
            if(!fileDir.exists()){
                //如果文件夹没有：新建
                fileDir.mkdirs();
            }
            FileOutputStream fos=null;
            try {
                fos=new FileOutputStream(finalpath);
                fos.write(file.getBytes());
                fos.flush();
                String externalPath =  year + "/" + month + "/" + day + "/" + fileName;
                results.add(ConstantUtil.IMAGE_PATH_EXTERNAL+externalPath);
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                try {
                    if(fos!=null){
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return results;
	}
	

	/**
	 * 返回成功结果
	 * @param result
	 * @return
	 */
	protected String success(Map<String, Object> result)
	{
		result.put("status", "suc");
		return JsonUtil.object2String(result);
	}

	/**
	 * 返回成功结果
	 * @return
	 */
	protected String success()
	{
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "suc");
		return JsonUtil.object2String(result);
	}

	/**
	 * 返回错误结果
	 * 
	 * @param errorCode
	 *            错误码
	 * @return
	 */
	protected String error(String errorCode)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		return ErrorCodeUtil.errorMsg(result, errorCode);
	}

}
