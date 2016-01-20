package com.dpc.utils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {
	private static String randChars = "0123456789abcdefghigklmnopqrstuvtxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
	private static String randCharsNoBig = "0123456789abcdefghigklmnopqrstuvtxyz";
	private static Random random = new Random();	
	
    public static String encodeStr(String str) {  
        try {  
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
	
	public static String getSummary(String input,int length){
		
		input = input.replace("&nbsp;", "");
		
		//删除标记
		boolean flag=false;
		
		StringBuffer output = new StringBuffer();
		
		//临时存储的字符串
		StringBuffer piece = new StringBuffer();
		
		for(int i=0;i<input.length();i++){
			//判定是否是最后一个字符
			if(i == (input.length()-1) && '>' != input.charAt(i) ){
				piece.append(input.charAt(i));
				output.append(piece);
				continue;
			}
			
			//需要先判断'<',再判断'>',最后判定是否加入新串
			
			if('<' == input.charAt(i) && flag == false){
				flag = true;
				piece.append(input.charAt(i));
				continue;
			}
			
			if('<' == input.charAt(i) && flag == true){
				output.append(piece);
				piece = new StringBuffer();
				continue;
			}
			
			if('<' != input.charAt(i) && flag == true){
				piece.append(input.charAt(i));
				
				if('>' == input.charAt(i)){
					flag = false;
					piece = new StringBuffer();
				}
				
				continue;
			}
			
			if(flag == false){
				output.append(input.charAt(i));
				continue;
			}
		}
		
		return output.toString().substring(0, output.toString().length() > length ? length:output.toString().length());
		
	}
	
	public static Date StringToDate(String dateStr,String formatStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String dateToStr(Date date, String pattern) {
		String strDate = "";
		try {
			SimpleDateFormat fmt = new SimpleDateFormat(pattern);
			strDate = fmt.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strDate;
	}
	
	public  static boolean isEmpty(Object obj) {
		if (obj == null || obj.equals("") || obj.equals("null")) {
			return true;
		} else {
			return false;
		}
	}
	
	public  static String getRandStr(int length, boolean isOnlyNum) {
		int size = isOnlyNum ? 10 : 62;
		StringBuffer hash = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			hash.append(randChars.charAt(random.nextInt(size)));
		}
		return hash.toString();
	}
	
	public static String getRandStrNoBig(int length) {
		int size =36;
		StringBuffer hash = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			hash.append(randCharsNoBig.charAt(random.nextInt(size)));
		}
		return hash.toString();
	}
	
	public static Date strToDate(String datestr, String pattern) {
		DateFormat dd = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = dd.parse(datestr);
		} catch (ParseException e) {
			date = new Date(Date.parse(datestr));
		}
		return date;
	}
	
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}

		return true;
	}
	
	/*public boolean isNumericOrChineseChar(String str){
		String regex= "[0-9a-zA-Z\u4e00-\u9fa5]*";
		
	    return str.matches(regex);
	    
	}*/
	
	public static String md5(String arg0) {
		return MD5.MD5Encode(arg0);
	}
	
	public static boolean isChinese(char c) {  
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
			return true;  
		}  
		
	    return false;  
	}  
	  
	public static boolean isMessyCode(String strName) {  
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");  
		Matcher m = p.matcher(strName);  
	    String after = m.replaceAll("");  
	    String temp = after.replaceAll("\\p{P}", "");  
	    char[] ch = temp.trim().toCharArray();  
	    float chLength = ch.length;  
	    float count = 0;  
	    for (int i = 0; i < ch.length; i++) {  
	    	char c = ch[i];  
	    	if (!Character.isLetterOrDigit(c)) {  
	    		if (!isChinese(c)) {  
	    			count = count + 1;  
	    			System.out.print(c);  
	    		}  
	    	}  
	    }  
	    float result = count / chLength;  
	    if (result > 0.4) {  
	    	return true;  
	    } else {  
	    	return false;  
	    }  
	}
	
	//获得指定日期的前一天 
    public static String getSpecifiedDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1);  
  
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());  
        return dayBefore;  
    } 
    
    //获得指定日期的后一天 
    public static String getSpecifiedDayAfter(String specifiedDay) {  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day + 1);  
  
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());  
        return dayAfter;  
    }
    
    //技能价格处理
    public static String getSkillPriceRange(String price) {
    	String[] prices=price.split(";");
    	for(int i=0;i<prices.length-1;i++){
		for(int j=i+1;j<prices.length;j++){
            if(Float.parseFloat(prices[i])>Float.parseFloat(prices[j])) {
                String temp=prices[i];
                prices[i]=prices[j];
                prices[j]=temp;
            }
        }
	    }
		if(prices.length!=1){
			if(!prices[0].equals(prices[prices.length-1])){
				price=prices[0]+"-"+prices[prices.length-1];
			}else{
				price=prices[0];
			}
			
		}	
		return price;
    }

	public static String inviteCodeGenerate(Integer userId) {
		String inviteCode = Long.toString(userId, 36);
		return inviteCode;
	}
}
