package com.mibo.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.mibo.common.base.BaseAction;
import com.mibo.common.result.Response;
import org.apache.commons.lang3.StringUtils;

public class TimestampsInterceptor implements Interceptor {
	public void intercept(Invocation inv) {
		BaseAction controller = (BaseAction) inv.getController();
		String time = controller.getReqTime();
		if (StringUtils.isBlank(time)) {
			controller.renderJson(new Response(Integer.valueOf(101), "请求时间戳不能为空！"));
			return;
		}
		long reqTime = Long.parseLong(time);
		long minute = (System.currentTimeMillis() - reqTime) / 60000L;
		if ((minute > 5L) || (minute < -5L)) {
			controller.renderJson(new Response(Integer.valueOf(103), "请求时间戳非法！"));
			return;
		}
		inv.invoke();
	}
}