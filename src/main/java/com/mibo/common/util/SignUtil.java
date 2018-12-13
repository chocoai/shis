package com.mibo.common.util;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import com.jfinal.kit.Base64Kit;
import com.jfinal.kit.HashKit;

public class SignUtil {

	public static void main(String[] args) {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("purpose", "CodeRegister");
		map.put("phone", "13085795302");
		String strs = "";
        Set<Map.Entry<String,String>> entrySet=map.entrySet();  
        for(Map.Entry<String, String> entry:entrySet){  
            String key=entry.getKey();  
            String value=entry.getValue();
            strs += key+"="+value+"&";
        }
		String reqTime = ""+System.currentTimeMillis();
		strs += "reqTime="+reqTime;
//		strs += "reqTime="+"1535345365297";
		System.out.println(Base64Kit.encode(strs));
		String sign = getSign(strs);
		System.out.println(strs);
		System.out.println(sign);
		System.out.println(checkSign(sign,strs));
	}
	
	/**
	 * 根据参数获取签名
	 * @param pars	参数
	 * @return
	 */
	public static String getSign(String pars) {
		return getMD5(Base64Kit.encode(pars));
	}
	
	/**
	 * 校验签名
	 * @param sign
	 * @param pars
	 * @return
	 */
	public static boolean checkSign(String sign, String pars) {
		return getSign(pars).equals(sign);
	}

	/**
	 * 对字符串md5加密(小写+字母) 
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {  
		return HashKit.md5(str);
    } 
}
