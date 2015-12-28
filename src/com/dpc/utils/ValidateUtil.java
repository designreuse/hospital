package com.dpc.utils;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.math.NumberUtils;

public class ValidateUtil
{

	public static final Set<String> ALLOWABLE_IMAGE_TYPES = new HashSet<String>(Arrays.asList("image/png", "image/jpg", "image/gif", "image/jpeg"));
	
	public static final Set<String> ALLOWABLE_VIDEO_TYPES = new HashSet<String>(Arrays.asList("video/mp4"));


	/**
	 * 邮箱
	 */
	public static final String REGEX_EMAIL = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	

	/**
	 * 手机号
	 */
	public static final String REGEX_MOBILE = "^1[3,4,5,7,8][\\d]{9}$";
	
	/**
	 * 手机号和固定电话
	 */
	public static final String REGEX_CONTACT="((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
	/**
	 * 密码6至16位
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}+$";

	/**
	 * 中英文、数字-_
	 */
	public static final String REGEX_NICKNAME = "^[a-zA-Z0-9\u4E00-\u9FA5_-]*$";
	
	/**
	 * 数字和点
	 */
	public static final String REGEX_VERSION ="^[0-9][0-9.]{0,10}[0-9]$";
	
	/**
	 * 中英文、数字，不能纯数字
	 */
	public static final String REGEX_TEXT_NOTONLY_DIGIT = "^(?!\\d+$)[a-zA-Z0-9\u4E00-\u9FA5_-]*$";
	
	/**
	 * 中文或者英文
	 */
	public static final String REGEX_REALNAME ="^[a-zA-Z\u4e00-\u9fa5· ]+$";

	/**
	 * 身份证号码
	 */
	public static final String REGEX_IDCARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";


	/**
	 * 通过正则表达式,验证参数合法性
	 * 
	 * @param value
	 *            待验证的值
	 * @param regex
	 *            正则表达式
	 * @return
	 */
	public static boolean validate(String value, String regex)
	{
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * 验证字符串长度
	 * 
	 * @param value
	 *            待验证的值
	 * @param min
	 *            最小长度
	 * @param max
	 *            最大长度
	 * @return
	 */
	public static boolean validateLength(String value, int min, int max)
	{
		return validateLength(value, min, max, false);
	}

	/**
	 * /** 验证字符串长度
	 * 
	 * @param value
	 *            待验证的值
	 * @param min
	 *            最小长度
	 * @param max
	 *            最大长度
	 * @param nullable
	 *            是否可为空
	 * @return
	 */
	public static boolean validateLength(String value, int min, int max,
			boolean nullable)
	{
		if (!nullable && (value == null || value.equals("")))
		{
			return false;
		} else
		{
			if (value == null || value.equals(""))
				return true;
		}
		int length = 0;
        String chinese = "[\u4E00-\u9FA5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
            	length += 2;
            } else {
                /* 其他字符长度为1 */
            	length += 1;
            }
        }
		return (length >= min && length <= max);

	}

	/**
	 * 
	 * 验证字符串的长度
	 * 
	 * @param value
	 *            待验证的值
	 * @param length
	 *            字符串的匹配长度
	 * @return
	 */
	public static boolean validateLength(String value, int length)
	{
		if (value == null)
			return false;
		int len = 0;
        String chinese = "[\u4E00-\u9FA5]";
		for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
            	len += 2;
            } else {
                /* 其他字符长度为1 */
            	len += 1;
            }
        }
		return (len == length);
	}

	/**
	 * 
	 * 验证参数是否是字母或数字,且长度满足min至max的范围
	 * 
	 * @param value
	 *            待验证的值
	 * @param min
	 *            最小长度
	 * @param max
	 *            最大长度
	 * @return
	 */
	public static boolean isNumbericOrLetter(String value, int min, int max)
	{
		String regex = "^[a-zA-Z0-9]{" + min + "," + max + "}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static boolean isNumbericOrLetter(String value, int len)
	{
		String regex = "^[a-zA-Z0-9]{" + len + "}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	public static boolean isNumbericOrLOWERCASE(String value, int len)
	{
		String regex = "^[a-z0-9]{" + len + "}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * 中英文、数字,且长度为4至30位
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNickname(String value)
	{
		Pattern pattern = Pattern.compile(REGEX_NICKNAME);
		Matcher matcher = pattern.matcher(value);
		int valueLength = 0;
        String chinese = "[\u4E00-\u9FA5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
		return (matcher.matches() && valueLength>=4 && valueLength<=40);
	}
	
	/**
	 * 中英文长度为4至30位
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isRealname(String value)
	{
		Pattern pattern = Pattern.compile(REGEX_REALNAME);
		Matcher matcher = pattern.matcher(value);
		int valueLength = 0;
        String chinese = "[\u4E00-\u9FA5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
		return (matcher.matches() &&valueLength<=20);
	}
	
	/**
	 * 版本号
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isVersion(String value)
	{
		Pattern pattern = Pattern.compile(REGEX_VERSION);
		Matcher matcher = pattern.matcher(value);       
		return matcher.matches();
	}

	/**
	 * 
	 * 验证手机号码
	 * 
	 * @param mobile
	 *            手机号码
	 * @return
	 */
	public static boolean isMobile(String mobile)
	{
		Pattern pattern = Pattern.compile(REGEX_MOBILE);
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}
	
	public static boolean isValidDate(String str) {
		
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {				
			format.setLenient(false);
			format.parse(str);
			String minute=str.split(":")[1];
			if(!minute.equals("30")&& !minute.equals("00"))
				return false;
		} catch (Exception e) {		
			return false;
		} 
		return true;
	}
	/**
	 * 判断日期是否大于等于当前日期
	 * @param str
	 * @return
	 */
	public static boolean afterDate(String str){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try
		{
			Date nowDate = new Date();
			Date date = format.parse(str);
			Calendar cl1 = Calendar.getInstance();
			cl1.setTime(date);
			Calendar cl2 = Calendar.getInstance();
			cl2.setTime(nowDate);
			int result = cl1.compareTo(cl2);
			if(result == 1)
				return true;
			else
				return false;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * 验证手机号码或固定电话
	 * 
	 * @param contact
	 *            联系方式
	 * @return
	 */
	public static boolean isContact(String contact)
	{
		Pattern pattern = Pattern.compile(REGEX_CONTACT);
		Matcher matcher = pattern.matcher(contact);
		return matcher.matches();
	}
	
	/**
	 * 
	 * 验证邮箱
	 * 
	 * @param email
	 *            邮箱
	 * @return
	 */
	public static boolean isEmail(String email)
	{
		Pattern pattern = Pattern.compile(REGEX_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	
	/**
	 * 判断价格是否大于1元
	 * @author add by huazai
	 * @param price
	 * @return
	 */
	public static boolean isBigOne(String price){
		Integer a = new Integer(price);
		Integer b = new Integer(1);
		int reault = a.compareTo(b);
		if(reault >= 0)
			return true;
		else
			return false;
	}
	/**
	 * 判断是否是一个正整数
	 * @param num
	 * @return
	 */
	public static boolean isPositiveInt(String num){
		String rex = "/^[1-9]+\\d*$";
		Pattern p = Pattern.compile(rex);
		Matcher m = p.matcher(num);
		if (m.find()){
			return true;
		}
		return false;
	}
	/**
	 * 判断价格是否为整数
	 * @param price
	 * @return
	 */
	public static boolean isNumber(String price){
		if(price !=null && price !=""){
			return price.matches("^[0-9]*$");
		}else
			return false;
	}
	/**
	 * 判断是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value)
	{
		return (value == null || value.trim().equals("") || value.trim()
				.equals("null"));
	}

	/**
	 * 判断文本不为空时，长度是否合法
	 * @param value
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public static boolean validateLengthWithNoEmpty(String value,Integer minLength,Integer maxLength){
		if(value != null && !value.trim().equals("") && !value.trim().equals("null")){
			return validateLength(value, minLength, maxLength);
		}
		return true;
	}
	
	/**
	 * 判断是否不为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value)
	{
		return !(value == null || value.trim().equals("") || value.trim()
				.equals("null"));
	}

	/**
	 * 匹配密码,一般是6~16位
	 * 
	 * @param password
	 *            密码
	 * @return
	 */
	public static boolean isPassword(String password)
	{
		Pattern pattern = Pattern.compile(REGEX_PASSWORD);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	/**
	 * 验证性别
	 * 
	 * @param sex
	 * @return
	 */
	public static boolean isSex(String sex)
	{
		if (isEmpty(sex))
			return false;
		return (sex.equals("1") || sex.equals("0"));
	}
	
	/**
	 * 验证设备类型
	 * 
	 * @param deviceType
	 * @return
	 */
	public static boolean isDeviceType(String deviceType)
	{
		if (isEmpty(deviceType))
			return false;
		return (deviceType.equals("1") || deviceType.equals("2"));
	}

	/**
	 * 验证性别
	 * 
	 * @param sex
	 * @return
	 */
	public static boolean isSex(Integer sex)
	{
		if (sex == null)
			return false;
		return (sex == 0 || sex == 1);
	}

	/**
	 * 判断是否是个日期类型 例如 yyyy-MM-dd 2014-05-09
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isDate(String value)
	{
		if (isEmpty(value))
			return false;
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\-\\s]?((((0?"
						+ "[13578])|(1[02]))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))"
						+ "|(((0?[469])|(11))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|"
						+ "(0?2[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12"
						+ "35679])|([13579][01345789]))[\\-\\-\\s]?((((0?[13578])|(1[02]))"
						+ "[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))"
						+ "[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\-\\s]?((0?["
						+ "1-9])|(1[0-9])|(2[0-8]))))))");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * 验证城市
	 * 
	 * @param city
	 * @return
	 */
	public static boolean validateCity(String city)
	{
		return true;
	}

	/**
	 * 验证是否是允许的图片类型
	 * 
	 * @param imageType
	 *            图片类型
	 * @return
	 */
	public static boolean validateImageType(String imageType)
	{
		if (ALLOWABLE_IMAGE_TYPES.contains(imageType))
			return true;
		return false;
	}
	
	/**
	 * 验证是否是允许的视频类型
	 * 
	 * @param videoType
	 *            视频类型
	 * @return
	 */
	public static boolean validateVideoType(String videoType)
	{
		if (ALLOWABLE_VIDEO_TYPES.contains(videoType))
			return true;
		return false;
	}

	/**
	 * 验证长度
	 * 
	 * @param size
	 * @param maxSize
	 * @return
	 */
	public static boolean validateSize(long size, int maxSize)
	{
		if (size > maxSize)
			return false;
		return true;
	}

	/**
	 * 验证是否为一个数字
	 * 
	 * @param value
	 *            待验证的值
	 * @param nullable
	 *            是否为空,当nullable为true时,value为null返回true,否则返回false
	 * @return
	 */
	public static boolean isNumberic(String value, boolean nullable)
	{
		if (!nullable)
		{
			if (value == null || value.equals(""))
				return false;

		} else
		{
			if (value == null || value.equals(""))
				return true;

		}
		return NumberUtils.isNumber(value);
	}

	/**
	 * 是否是一个数字
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNumberic(String value)
	{
		return isNumberic(value, false);
	}

	/**
	 * 验证是否为一个数字,并且校验它的长度
	 * 
	 * @param value
	 * @param len
	 * @param nullable
	 * @return
	 */
	public static boolean isNumberic(String value, int len, boolean nullable)
	{
		if (!nullable)
		{
			if (value == null || value.equals(""))
				return false;

		} else
		{
			if (value == null || value.equals(""))
				return true;

		}
		return (NumberUtils.isNumber(value) && value.trim().length() == len);
	}
	
	/**
	 * 验证是否为一个数字,并且校验它的长度范围
	 * 
	 * @param value
	 * @param len
	 * @param nullable
	 * @return
	 */
	public static boolean isNumberic(String value, int minLength, int maxLength)
	{
		
		if (value == null || value.equals(""))
			return false;

		
		return (NumberUtils.isNumber(value) && value.trim().length() <= maxLength && value.trim().length() >=minLength);
	}
	
	/**
	 * 验证是否为一个纯数字
	 * 
	 * @param value
	 *            待验证的值
	 * @param nullable
	 *            是否为空,当nullable为true时,value为null返回true,否则返回false
	 * @return
	 */
	public static boolean isDigits(String value, boolean nullable)
	{
		if (!nullable)
		{
			if (value == null || value.equals(""))
				return false;

		} else
		{
			if (value == null || value.equals(""))
				return true;

		}
		return NumberUtils.isDigits(value);
	}
	
	public static boolean isId(String value){
		if(!NumberUtils.isDigits(value)){
			return false;
		}
		try{
			Integer.parseInt(value);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	/**
	 * 是否是一个纯数字
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isDigits(String value)
	{
		return isDigits(value, false);
	}

	/**
	 * 验证是否为一个纯数字,并且校验它的长度
	 * 
	 * @param value
	 * @param len
	 * @param nullable
	 * @return
	 */
	public static boolean isDigits(String value, int len, boolean nullable)
	{
		if (!nullable)
		{
			if (value == null || value.equals(""))
				return false;

		} else
		{
			if (value == null || value.equals(""))
				return true;

		}
		return (NumberUtils.isDigits(value) && value.trim().length() == len);
	}
	
	/**
	 * 验证是否为一个纯数字,并且校验它的长度范围
	 * 
	 * @param value
	 * @param len
	 * @param nullable
	 * @return
	 */
	public static boolean isDigits(String value, int minLength, int maxLength)
	{
		
		if (value == null || value.equals(""))
			return false;

		
		return (NumberUtils.isDigits(value) && value.trim().length() <= maxLength && value.trim().length() >=minLength);
	}
	
	/**
	 * 验证是否为一个纯数字,并且校验它的大小范围
	 * 
	 * @param value
	 * @param len
	 * @param nullable
	 * @return
	 */
	public static boolean isDigitRange(String value, int minValue, int maxValue)
	{
		
		if (value == null || value.equals(""))
			return false;

		
		return (NumberUtils.isDigits(value) && Integer.parseInt(value) <= maxValue && Integer.parseInt(value) >=minValue);
	}
	
	

	/**
	 * 验证身份证号
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isIdCard(String value)
	{
		Pattern pattern = Pattern.compile(REGEX_IDCARD);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param collection
	 *            集合
	 * @return
	 */
	public static boolean isEmpty(List collection)
	{
		return (collection == null || collection.size() == 0);
	}

	public static boolean isEmpty(String[] array)
	{
		if(array == null || array.length == 0){
			return true;
		}else{
			for(String s:array){
				if(!isEmpty(s)){
					return false;
				}
			}
			return true;
		}

	}

	/**
	 * 文本内容是否合法
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isText(String value)
	{
		return true;
	}
	/**
	 * 文本内容是否合法
	 */
	public static boolean isText(String value,int minLength,int maxLength){
		Pattern pattern = Pattern.compile(REGEX_NICKNAME);
		Matcher matcher = pattern.matcher(value);
		int valueLength = 0;
        String chinese = "[\u4E00-\u9FA5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
		return (matcher.matches() && valueLength>=minLength && valueLength<=maxLength);
	}
	
	/**
	 * 文本内容是否合法
	 */
	public static boolean isText(String value,int minLength,int maxLength,boolean notOnlyDigit,boolean nullable){
		if (!nullable)
		{
			if (value == null || value.equals(""))
				return false;

		} else
		{
			if (value == null || value.equals(""))
				return true;
		}
		Pattern pattern=null;
		if(notOnlyDigit==true){
			pattern = Pattern.compile(REGEX_TEXT_NOTONLY_DIGIT);			
		}else{
			pattern = Pattern.compile(REGEX_NICKNAME);
		}
		Matcher matcher = pattern.matcher(value);
		int valueLength = 0;
        String chinese = "[\u4E00-\u9FA5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
		return (matcher.matches() && valueLength>=minLength && valueLength<=maxLength);
	}

	/**
	 * 
	 * @param reasonType
	 * @return
	 */
	public static boolean isReasonType(String reasonType)
	{
		return ("1".equals(reasonType) || "2".equals(reasonType)
				|| "3".equals(reasonType) || "4".equals(reasonType)
				|| "5".equals(reasonType));
	}	
	/**
	 * 校验纬度是否合法
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isLongitude(String param)
	{
		if (isEmpty(param))
			return false;
		String reg = "^((-?[1-9]\\d*)|(0))(\\.\\d+)?$";
		return (param.matches(reg) && Double.parseDouble(param)>=-180 && Double.parseDouble(param)<=180);
	}
	
	/**
	 * 校验纬度是否合法
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isLatitude(String param)
	{
		if (isEmpty(param))
			return false;
		String reg = "^((-?[1-9]\\d*)|(0))(\\.\\d+)?$";
		return (param.matches(reg) && Double.parseDouble(param)>=-90 && Double.parseDouble(param)<=90);
	}

	

}
