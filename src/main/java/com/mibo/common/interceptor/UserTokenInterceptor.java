package com.mibo.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.mibo.common.base.BaseAction;
import com.mibo.common.constant.ConstUtils;
import com.mibo.common.result.Response;
import com.mibo.modules.data.model.User;
import org.apache.commons.lang3.StringUtils;

public class UserTokenInterceptor implements Interceptor {
	public void intercept(Invocation inv) {
		BaseAction controller = (BaseAction) inv.getController();
		String token = controller.getUserToken();
		if (StringUtils.isBlank(token)) {
			controller.renderJson(new Response(Integer.valueOf(101), "userToken不能为空！"));
			return;
		}
		if (StringUtils.isBlank(controller.getUserId())) {
			controller.renderJson(new Response(Integer.valueOf(102), "userToken已失效，请重新登录！"));
			return;
		}
		User user = controller.getUser();
		if ((user == null) || (!user.getLoginToken().equals(token))) {
			controller.renderJson(new Response(Integer.valueOf(100), "登录身份已过期,请重新登录！"));
			return;
		}
		if (user.getAccountStatus().equals(ConstUtils.ACCOUNT_STATUS.FORBID.getValue())) {
			controller.renderJson(new Response(Integer.valueOf(104), "账号异常，已被管理员禁止登陆！"));
			return;
		}
		inv.invoke();
	}
}