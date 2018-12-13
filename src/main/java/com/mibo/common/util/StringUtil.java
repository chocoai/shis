package com.mibo.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import com.jfinal.kit.LogKit;

public class StringUtil extends StringUtils {

	public static void main(String[] args) {
//		for (int i = 0; i < 10000; i++) {
//			System.out.println(generateRandomStr(6));
//		}
		System.out.println(getUuId());
		System.out.println(isNotNumeric("a"));
		System.out.println(getId());
		System.out.println(isPhone("17665201977"));
	}

	/**
	 * 
	 * 随机生成验证码（数字+字母）
	 *
	 * @param len
	 *            邀请码长度
	 * @return
	 * 
	 * @author ailo555
	 * @date 2016年10月23日 上午9:27:09
	 */
	public static String generateRandomStr(int len) {
		// 字符源，可以根据需要删减
		String generateSource = "23456789abcdefghkmnpqrstuvwxyz";// 去掉1和i及l ，0和o
		String rtnStr = "";
		for (int i = 0; i < len; i++) {
			// 循环随机获得当次字符，并移走选出的字符
			String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
			rtnStr += nowStr;
			generateSource = generateSource.replaceAll(nowStr, "");
		}
		return rtnStr;
	}

	/**
	 * 6位的随机数
	 * 
	 * @return
	 */
	public static String getCount() {
		Double db = Math.random();
		return db.toString().substring(2, 8);
	}

	/**
	 * 是否手机号码
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if (StringUtils.isBlank(phone)) {
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[5,7]))\\d{8}$");
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	/**
	 * 生成编号
	 * 
	 * @return
	 */
	public static String getId() {
		return getRandom();
	}

	public static String getUuId() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	/**
	 * 生成18位数订单编号
	 * 
	 * @return
	 */
	public static String getOrderId() {
		StringBuffer sb = new StringBuffer(DateUtil.getFormatTime("yyMMddHHmmss"));
		Double db = Math.random();
		return sb.append(db.toString().substring(2, 8)).toString();
	}

	/**
	 * 获取指定随机数
	 * 
	 * @param count
	 *            最大32位
	 * @return
	 */
	public static String getRandom(int count) {
		String str = getRandom();
		return str.substring(str.length() - count, str.length());
	}

	/**
	 * 生成32位随机数
	 * 
	 * @return
	 */
	public static String getRandom() {
		StringBuffer sb = new StringBuffer(DateUtil.getFormatTime("yyyyMMddHHmmss"));
		Double db = Math.random();
		sb.append(db.toString().substring(2, 14));
		Long code = System.currentTimeMillis();
		sb.append(code.toString().substring(0, 6));
		return sb.toString();
	}

	/**
	 * 是否数值 只能是正整数
	 * 
	 * @param str
	 * @return 正整数=false  
	 */
	public static boolean isNotNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 文章格式化
	 * 
	 * @param source
	 * @return
	 */
	public static String replace(String source) {
		if (StringUtils.isEmpty(source)) {
			return "";
		}
		return StringEscapeUtils.escapeHtml4(source);
	}

	/**
	 * 判断参数是否为空
	 * 
	 * @param str
	 *            参数
	 * @return
	 */
	public static boolean isBlanks(String... str) {
		for (String s : str) {
			if (StringUtils.isBlank(s)) {
				return true;
			}
		}
		return false;
	}

	public static String readToString(String filePath) {
		String encoding = "UTF-8";  
        File file = new File(filePath);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
        	FileInputStream in = new FileInputStream(file);  
        	in.read(filecontent);  
        	in.close();  
        	return new String(filecontent, encoding);  
		} catch (Exception e) {
			LogKit.error("读取文件失败！", e);
			e.printStackTrace();  
            return null;  
		}
	}
}
