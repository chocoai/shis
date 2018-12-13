package com.mibo.modules.al.sma.util;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20180120.GetDeviceStatusRequest;
import com.aliyuncs.iot.model.v20180120.GetDeviceStatusResponse;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailRequest;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailResponse;
import com.aliyuncs.iot.model.v20180120.RegisterDeviceRequest;
import com.aliyuncs.iot.model.v20180120.RegisterDeviceResponse;
import com.jfinal.kit.LogKit;
import com.mibo.modules.al.sma.conf.SmaConfig;

public class DeviceUtil {

	private static final DefaultAcsClient CLIENT = SmaConfig.getClient();

	/**
	 * 注册设备
	 * 
	 * @param productKey
	 * @param deviceName
	 * @return
	 */
	public static RegisterDeviceResponse registerDevice(String productKey, String deviceName) {
		RegisterDeviceRequest request = new RegisterDeviceRequest();
		request.setActionName("RegisterDevice");
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);
		RegisterDeviceResponse response = null;
		try {
			response = CLIENT.getAcsResponse(request);
		} catch (Exception e) {
			LogKit.error("注册设备失败！", e);
		}
		return response;
	}

	public static GetDeviceStatusResponse getDeviceStatus(String productKey, String deviceName) {
		GetDeviceStatusRequest request = new GetDeviceStatusRequest();
		request.setActionName("GetDeviceStatus");
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);
		GetDeviceStatusResponse response = null;
		try {
			response = (GetDeviceStatusResponse) CLIENT.getAcsResponse(request);
		} catch (Exception e) {
			LogKit.error("指定设备的运行状态失败！", e);
		}
		return response;
	}

	public static QueryDeviceDetailResponse queryDeviceByProductKeyDeviceName(String deviceName) {
		List<String> listProdKey = ProductUtil.getProductKeyList();
		for (String str : listProdKey) {
			QueryDeviceDetailResponse response = getDevices(str, deviceName);
			if (response.getSuccess().booleanValue()) {
				return response;
			}
		}
		return null;
	}

	public static QueryDeviceDetailResponse getDevices(String productKey, String deviceName) {
		QueryDeviceDetailRequest request = new QueryDeviceDetailRequest();
		request.setActionName("QueryDeviceDetail");
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);
		QueryDeviceDetailResponse response = null;
		try {
			response = (QueryDeviceDetailResponse) CLIENT.getAcsResponse(request);
		} catch (Exception e) {
			LogKit.error("查询指定设备的详细信息失败！", e);
		}
		return response;
	}
}
