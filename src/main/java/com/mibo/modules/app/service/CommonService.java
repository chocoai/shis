package com.mibo.modules.app.service;

import com.mibo.common.constant.ConstUtils;
import com.mibo.common.constant.Global;
import com.mibo.common.result.Response;
import com.mibo.common.util.JedisUtil;
import com.mibo.common.util.StringUtil;
import com.mibo.modules.data.model.AppVersion;
import com.mibo.modules.data.model.User;
import com.mibo.modules.sms.SmsUtil;

public class CommonService extends com.mibo.common.base.BaseService {
	/* 15 */ public static final CommonService commonService = new CommonService();
	/* 16 */ private static final AppVersion appVersionDao = AppVersion.dao;
	/* 17 */ private static final User userDao = User.dao;

	public Response updateVersion(String type, String no) {
		/* 26 */ if (StringUtil.isBlanks(new String[] { type, no })) {
			/* 27 */ return renderErrorParameter();
		}
		/* 29 */ if (!ConstUtils.APP_TYPE.CLIENT.getValue().equals(type)) {
			/* 30 */ return renderError("APP类型错误!");
		}
		/* 32 */ return renderResult(appVersionDao.queryAppVersionByTypeVersionNo(type, Integer.parseInt(no)));
	}

	public Response getCode(String phone, String purpose) {
		/* 43 */ if (StringUtil.isBlanks(new String[] { phone, purpose })) {
			/* 44 */ return renderErrorParameter();
		}
		/* 46 */ if (!StringUtil.isPhone(phone)) {
			/* 47 */ return renderErrorPhone();
		}
		/* 49 */ if ((!purpose.equals(ConstUtils.CODE_PURPOSE.REGISTER.getValue())) &&
		/* 50 */ (!purpose.equals(ConstUtils.CODE_PURPOSE.FORGET.getValue()))) {
			/* 51 */ return renderError("验证码方式错误！");
		}
		/* 53 */ String code = StringUtil.getCount();
		/* 54 */ if (purpose.equals(ConstUtils.CODE_PURPOSE.REGISTER.getValue())) {
			/* 55 */ User user = userDao.queryUserByPhone(phone);
			/* 56 */ if (user != null) {
				/* 57 */ return renderError("手机号码已注册！");
			}
			/* 59 */ JedisUtil.set(ConstUtils.CODE_PURPOSE.REGISTER.getValue() + phone, code, 180);
		}
		/* 61 */ if (purpose.equals(ConstUtils.CODE_PURPOSE.FORGET.getValue())) {
			/* 62 */ User user = userDao.queryUserByPhone(phone);
			/* 63 */ if (user == null) {
				/* 64 */ return renderError("手机号码未注册！");
			}
			/* 66 */ JedisUtil.set(ConstUtils.CODE_PURPOSE.FORGET.getValue() + phone, code, 180);
		}
		/* 68 */ if (Global.DEVMODE) {
			/* 69 */ return renderResult(code);
		}
		/* 71 */ SmsUtil.sendSMS(phone, code);
		/* 72 */ return renderMeg();
	}
}