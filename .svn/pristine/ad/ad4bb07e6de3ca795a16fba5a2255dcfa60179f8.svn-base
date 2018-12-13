package com.mibo.common.base;

import com.alibaba.fastjson.JSON;
import com.jfinal.core.Controller;
import com.mibo.common.constant.ConstUtils;
import com.mibo.common.result.Response;
import com.mibo.common.util.JedisUtil;
import com.mibo.common.util.StringUtil;
import com.mibo.modules.data.model.User;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

public class BaseAction extends Controller {
	protected int getPageNo() {
		String no = getPara("pageNo");
		int initial = 1;
		if (StringUtils.isBlank(no)) {
			return initial;
		}
		if (StringUtil.isNotNumeric(no)) {
			return initial;
		}
		int pageNo = Integer.parseInt(no);
		if (pageNo <= 0) {
			return initial;
		}
		return pageNo;
	}

	protected void renderResponse(Response response) {
		renderJson(response);
	}

	public void renderErrorRequest() {
		renderJson(new Response(Integer.valueOf(101), "请求方式错误!"));
	}

	public boolean isPost() {
		return getRequest().getMethod().equalsIgnoreCase("post");
	}

	public String getIp() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("X-Forwarded-For");
		if ((StringUtils.isNotEmpty(ip)) && (!"unKnown".equalsIgnoreCase(ip))) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			}
			return ip;
		}

		ip = request.getHeader("X-Real-IP");
		if ((StringUtils.isNotEmpty(ip)) && (!"unKnown".equalsIgnoreCase(ip))) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	public static HttpServletResponse setCrossDomainRequest(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Max-Age", "86400");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		return response;
	}

	public String getSign() {
		return getRequest().getHeader("sign");
	}

	public String getReqTime() {
		return getRequest().getHeader("reqTime");
	}

	public String getUserToken() {
		return getPara("userToken", "");
	}

	public String getUserId() {
		Map<String, String> map = JedisUtil.getMap(ConstUtils.USER_KEY.IDS.getValue());
		if (map == null) {
			return null;
		}
		if (StringUtil.isBlanks(new String[] { (String) map.get(getUserToken()) })) {
			return null;
		}
		return (String) map.get(getUserToken());
	}

	public User getUser() {
		User user = (User) JSON.parseObject(
				(String) JedisUtil.getMap(ConstUtils.USER_KEY.USERS.getValue()).get(getUserId()), User.class);
		if (user == null) {
			user = (User) User.dao.findById(getUserId());
			if (user != null) {
				BaseService.updateRedisUserToken(user.getLoginToken(), user.getId().toString());
				BaseService.updateRedisUser(user);
			}
		}
		return user;
	}
}