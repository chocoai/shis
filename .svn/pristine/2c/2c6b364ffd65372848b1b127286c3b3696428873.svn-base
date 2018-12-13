package com.mibo.modules.sms;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.LogKit;

import cn.hutool.http.HttpUtil;

public class SmsUtil {

	private static final String URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	
	private static final String ACCOUNT_SID = "21bf5497196f4a34a7e3f87fdb0d8203";

	private static final String AUTH_TOKEN = "6628b5c7b1394db283440cc179d0b8e8";
	
	private static final String RESP_DATA_TYPE = "JSON";
	
	public static void main(String[] args) {
		sendSMS("13823227110","123456");
	}
	
	/**
	 * 发送短信
	 * @param phone
	 * @param code
	 */
	public static boolean sendSMS(String phone,String code) {
		long timestamp = System.currentTimeMillis();
		String sig = ACCOUNT_SID+AUTH_TOKEN+timestamp;
		sig = HashKit.md5(sig);
		Map<String,Object> parMap = new HashMap<>();
		parMap.put("accountSid",ACCOUNT_SID);
		String SMS_CONTENT = "【丰润达科技】您的验证码为"+code+"，请于3分钟内正确输入，如非本人操作，请忽略此短信。";
		parMap.put("smsContent",SMS_CONTENT);
//		parMap.put("param",code);
		parMap.put("to",phone);
		parMap.put("timestamp",timestamp);
		parMap.put("sig",sig);
		parMap.put("respDataType",RESP_DATA_TYPE);
		String data = HttpUtil.post(URL,parMap);
		SmsResult result = JSON.parseObject(data,SmsResult.class);
		if (!"00000".equals(result.getRespCode())) {
			LogKit.warn("短信发送失败！===========》"+result.getRespDesc());
			return false;
		}
		return true;
	}
	
}
