package com.mibo.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.LogKit;
import com.mibo.common.constant.Global;
import com.mibo.common.result.Response;

public class ErrorInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		try {
			inv.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			LogKit.error("server error  =====>", e);
			inv.getController().renderJson(new Response(Global.CODE_SERVICE_ERROR, "server error =====>" + e));
		}
	}
}