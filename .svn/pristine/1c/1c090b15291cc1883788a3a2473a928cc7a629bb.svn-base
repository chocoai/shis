package com.mibo.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.mibo.common.base.BaseAction;
import com.mibo.common.result.Response;
import com.mibo.common.util.JedisUtil;
import com.mibo.common.util.SignUtil;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class SignInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		BaseAction controller = (BaseAction) inv.getController();
		String sign = controller.getSign();
		if (StringUtils.isBlank(sign)) {
			controller.renderJson(new Response(Integer.valueOf(101), "签名不能为空!"));
			return;
		}
		if (checkRedisSign(sign)) {
			controller.renderJson(new Response(Integer.valueOf(103), "签名非法!"));
			return;
		}
		setRedisSign(sign);
		Map<String, String> map = new TreeMap<String, String>();
		HttpServletRequest request = controller.getRequest();
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			map.put(key, request.getParameter(key));
		}
		String pars = "";
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		for (Map.Entry<String, String> entry : entrySet) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			pars = pars + key + "=" + value + "&";
		}
		pars = pars + "reqTime=" + controller.getReqTime();
		if (!SignUtil.checkSign(sign, pars)) {
			controller.renderJson(new Response(Integer.valueOf(101), "参数签名错误!"));
			return;
		}
		inv.invoke();
	}

	private static boolean checkRedisSign(String sign) {
		return JedisUtil.exists(sign);
	}

	private static void setRedisSign(String sign) {
		JedisUtil.set("HttpApiSing-" + sign, sign, 600);
	}
}