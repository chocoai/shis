package com.mibo.common.route;

import com.jfinal.config.Routes;
import com.mibo.common.constant.Global;
import com.mibo.common.interceptor.SignInterceptor;
import com.mibo.common.interceptor.TimestampsInterceptor;
import com.mibo.common.interceptor.UserTokenInterceptor;
import com.mibo.modules.app.action.CommonAction;
import com.mibo.modules.app.action.FileAction;
import com.mibo.modules.app.action.ProductAction;
import com.mibo.modules.app.action.UserAction;
import com.mibo.modules.app.action.UserSceneAction;

public class AppRouter extends Routes {
	private static final String API = "/api/";

	public void config() {
		addInterceptor(new UserTokenInterceptor());
		if (!Global.DEVMODE) {
			addInterceptor(new TimestampsInterceptor());
			addInterceptor(new SignInterceptor());
		}
		add(API + "common", CommonAction.class);
		add(API + "file", FileAction.class);
		add(API + "user", UserAction.class);
		add(API + "product", ProductAction.class);
		add(API + "userscene", UserSceneAction.class);
	}
}

/*
 * Location:
 * C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\common\route\
 * AppRouter.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */