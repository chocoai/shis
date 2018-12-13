package com.mibo.common.base;

import com.alibaba.fastjson.JSON;
import com.mibo.common.constant.ConstUtils;
import com.mibo.common.result.Response;
import com.mibo.common.util.JedisUtil;
import com.mibo.common.util.StringUtil;
import com.mibo.modules.data.model.Device;
import com.mibo.modules.data.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService {
	public List<Object> list;
	public Map<String, Object> map;
	public List<Map<String, Object>> listMap;

	public static List<Object> getList() {
		return new ArrayList<Object>();
	}

	public static Map<String, Object> getMap() {
		return new HashMap<String, Object>();
	}

	public static List<Map<String, Object>> getListMap() {
		return new ArrayList<Map<String, Object>>();
	}

	public Response renderError(int code, String msg) {
		return new Response(Integer.valueOf(code), msg);
	}

	public Response renderErrorParameter() {
		return new Response(Integer.valueOf(101), "参数错误,必填参数不能为空！");
	}

	public Response renderErrorPhone() {
		return new Response(Integer.valueOf(101), "手机号码不正确！");
	}

	public Response renderErrorCode() {
		return new Response(Integer.valueOf(101), "验证码错误！");
	}

	public Response renderError(String msg) {
		return new Response(Integer.valueOf(101), msg);
	}

	public Response renderResult(Object obj) {
		return new Response(Integer.valueOf(200), "成功！", obj);
	}

	public Response renderMeg() {
		return new Response(Integer.valueOf(200), "成功！");
	}

	public static void updateRedisUserToken(String token, String id) {
		Map<String, String> idMap = new HashMap<String, String>();
		idMap.put(token, id);
		JedisUtil.mapPut(ConstUtils.USER_KEY.IDS.getValue(), idMap);
	}

	public static void updateRedisUser(User user) {
		Map<String, String> mapUser = new HashMap<String, String>();
		mapUser.put(user.getId().toString(), JSON.toJSONString(user));
		JedisUtil.mapPut(ConstUtils.USER_KEY.USERS.getValue(), mapUser);
	}

	public Device getRedisDevice(String deviceName) {
		Map<String, String> map = JedisUtil.getMap("RedisDevices");
		if (map == null) {
			return null;
		}
		String tem = (String) map.get(deviceName);
		if (StringUtil.isBlanks(new String[] { tem })) {
			return null;
		}
		return (Device) JSON.parseObject(tem, Device.class);
	}

	public void setRedisDevice(Device device) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(device.getDeviceName(), JSON.toJSONString(device));
		JedisUtil.mapPut("RedisDevices", map);
	}

	public void removeRedisDevice(String deviceName) {
		JedisUtil.mapRemove("RedisDevices", deviceName);
	}
}