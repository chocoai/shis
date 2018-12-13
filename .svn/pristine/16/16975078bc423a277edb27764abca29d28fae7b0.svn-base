package com.mibo.modules.data.model;

import com.mibo.modules.data.base.BaseAppVersion;

@SuppressWarnings("serial")
public class AppVersion extends BaseAppVersion<AppVersion> {
	/* 10 */ public static final AppVersion dao = (AppVersion) new AppVersion().dao();

	public AppVersion queryAppVersionByTypeVersionNo(String appType, int version) {
		/* 19 */ String sql = "SELECT app_mark,is_forcibly,content,app_package,update_date FROM t_app_version WHERE del_flag = '0' AND is_publish = '1' AND app_type = ? AND app_mark > ? ";

		/* 23 */ return (AppVersion) findFirst(sql, new Object[] { appType, Integer.valueOf(version) });
	}
}