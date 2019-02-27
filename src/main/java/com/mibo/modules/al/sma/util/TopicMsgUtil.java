package com.mibo.modules.al.sma.util;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20170420.PubRequest;
import com.aliyuncs.iot.model.v20170420.PubResponse;
import com.aliyuncs.iot.model.v20180120.GetDeviceStatusRequest;
import com.aliyuncs.iot.model.v20180120.GetDeviceStatusResponse;
import com.jfinal.kit.LogKit;
import com.mibo.modules.al.sma.conf.SmaConfig;

public class TopicMsgUtil {

	private static final DefaultAcsClient CLIENT = SmaConfig.getClient();
	
	/**
	 * 向指定Topic发布消息
	 * @param productKey
	 * @param topicFullName
	 * @param messageContent
	 * @return
	 */
	public static PubResponse sendMessage(String productKey,String topicFullName,String messageContent) {
		LogKit.warn(new Date().getTime()+"发布消息前===>productKey=["+productKey+"],"
				+ "topicFullName=["+topicFullName+"],messageContent=["+messageContent+"]");
		PubRequest request = new PubRequest();
		request.setActionName("Pub");
		request.setProductKey(productKey);
		request.setTopicFullName(topicFullName);
		request.setMessageContent(messageContent);
		request.setQos(0);
		PubResponse response = null;
		try {
			response = CLIENT.getAcsResponse(request);
		} catch (Exception e) {
			LogKit.error("向指定Topic发布消息！",e);
		}
		LogKit.warn(new Date().getTime()+"发布消息后===>"+JSON.toJSONString(response));
		return response;
	}

	/**
	 * 获取设备在线状态
	 * @param productKey
	 * @param deviceName
	 * @return true 在线 false 离线
	 */
	public static boolean GetDeviceStatus(String productKey,String deviceName) {
		GetDeviceStatusRequest request = new GetDeviceStatusRequest();
		request.setActionName("GetDeviceStatus");
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);
		GetDeviceStatusResponse response = null;
		try {
			response = CLIENT.getAcsResponse(request);
		} catch (Exception e) {
			LogKit.error("查询设备状态失败！",e);
		}

		String str = response.getData().getStatus();

		if(str.equals("ONLINE")){
			return true;
		}

		return false;
	}
}
