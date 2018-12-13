package com.mibo.modules.app.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mibo.common.constant.ConstUtils;
import com.mibo.common.constant.Global;
import com.mibo.common.result.Response;
import com.mibo.common.util.JedisUtil;
import com.mibo.common.util.StringUtil;
import com.mibo.modules.data.model.User;
import com.mibo.modules.sms.SmsUtil;
import java.util.Date;

public class UserService extends com.mibo.common.base.BaseService {
	/* 17 */ public static final UserService userService = new UserService();
	/* 18 */ private static final User userDao = User.dao;

	public Response register(String phone, String password, String code) {
		/* 21 */ if (StringUtil.isBlanks(new String[] { phone, password, code })) {
			/* 22 */ return renderErrorParameter();
		}
		/* 24 */ if (!StringUtil.isPhone(phone)) {
			/* 25 */ return renderErrorPhone();
		}
		/* 27 */ if (password.length() != 32) {
			/* 28 */ return renderError("密码不符合规范！");
		}
		/* 30 */ String oldCode = JedisUtil.get(ConstUtils.CODE_PURPOSE.REGISTER.getValue() + phone);
		/* 31 */ if (StringUtil.isBlank(oldCode)) {
			/* 32 */ return renderError("验证码已超时！");
		}
		/* 34 */ if (!code.equals(oldCode)) {
			/* 35 */ return renderErrorCode();
		}
		/* 37 */ User user = userDao.queryUserByPhone(phone);
		/* 38 */ if (user != null) {
			/* 39 */ return renderError("手机号码已注册！");
		}
		/* 41 */ Date regDate = new Date();
		/* 42 */ userDao.register(phone, password, regDate);
		/* 43 */ return renderMeg();
	}

	@Before({ Tx.class })
	public Response login(String phone, String password) {
		/* 48 */ if (StringUtil.isBlanks(new String[] { phone, password })) {
			/* 49 */ return renderErrorParameter();
		}
		/* 51 */ if (!StringUtil.isPhone(phone)) {
			/* 52 */ return renderErrorPhone();
		}
		/* 54 */ if (password.length() != 32) {
			/* 55 */ return renderError("密码不符合规范！");
		}
		/* 57 */ User user = userDao.queryUserByPhone(phone);
		/* 58 */ if (user == null) {
			/* 59 */ return renderError("手机号未注册！");
		}
		/* 61 */ if (!password.equals(user.getPassword())) {
			/* 62 */ return renderError("密码错误！");
		}
		/* 64 */ if (StringUtil.isNotBlank(user.getLoginToken())) {
			/* 66 */ JedisUtil.mapRemove(ConstUtils.USER_KEY.IDS.getValue(), user.getLoginToken());
		}

		/* 69 */ String token = StringUtil.getUuId();
		/* 70 */ user.setLoginToken(token);
		/* 71 */ ((User) user.setLoginTime(new Date())).update();
		/* 72 */ updateRedisUserToken(token, user.getId().toString());
		/* 73 */ updateRedisUser(user);
		/* 74 */ return renderResult(user);
	}

	public Response forgetPwd(String phone, String password, String code) {
		/* 78 */ if (StringUtil.isBlanks(new String[] { phone, password, code })) {
			/* 79 */ return renderErrorParameter();
		}
		/* 81 */ if (!StringUtil.isPhone(phone)) {
			/* 82 */ return renderErrorPhone();
		}
		/* 84 */ User user = userDao.queryUserByPhone(phone);
		/* 85 */ if (user == null) {
			/* 86 */ return renderError("手机号未注册！");
		}
		/* 88 */ if (password.length() != 32) {
			/* 89 */ return renderError("密码不符合规范！");
		}
		/* 91 */ String oldCode = JedisUtil.get(ConstUtils.CODE_PURPOSE.FORGET.getValue() + phone);
		/* 92 */ if (StringUtil.isBlank(oldCode)) {
			/* 93 */ return renderError("验证码已超时！");
		}
		/* 95 */ if (!code.equals(oldCode)) {
			/* 96 */ return renderErrorCode();
		}
		/* 98 */ ((User) user.setPassword(password)).update();
		/* 99 */ return renderMeg();
	}

	public Response verifyPwd(String password, User user) {
		/* 103 */ if (StringUtil.isBlanks(new String[] { password })) {
			/* 104 */ return renderErrorParameter();
		}
		/* 106 */ if (password.length() != 32) {
			/* 107 */ return renderError("密码不符合规范！");
		}
		/* 109 */ if (!password.equals(user.getPassword())) {
			/* 110 */ return renderError("密码错误！");
		}
		/* 112 */ return renderMeg();
	}

	public Response updatePwd(String password, User user) {
		/* 116 */ if (StringUtil.isBlanks(new String[] { password })) {
			/* 117 */ return renderErrorParameter();
		}
		/* 119 */ if (password.length() != 32) {
			/* 120 */ return renderError("密码不符合规范！");
		}
		/* 122 */ ((User) user.setPassword(password)).update();
		/* 123 */ updateRedisUser(user);
		/* 124 */ return renderMeg();
	}

	public Response verifyPhone(String phone, String code, User user) {
		/* 128 */ if (StringUtil.isBlanks(new String[] { phone, code })) {
			/* 129 */ return renderErrorParameter();
		}
		/* 131 */ if (!StringUtil.isPhone(phone)) {
			/* 132 */ return renderErrorPhone();
		}
		/* 134 */ if (!phone.equals(user.getPhone())) {
			/* 135 */ return renderError("原手机号码错误！");
		}
		/* 137 */ String oldCode = JedisUtil.get(ConstUtils.CODE_PURPOSE.OLDREPLACE.getValue() + phone);
		/* 138 */ if (StringUtil.isBlanks(new String[] { oldCode })) {
			/* 139 */ return renderError("验证码已超时！");
		}
		/* 141 */ if (!code.equals(oldCode)) {
			/* 142 */ return renderErrorCode();
		}
		/* 144 */ return renderMeg();
	}

	public Response updatePhone(String phone, String code, User curUser) {
		/* 148 */ if (StringUtil.isBlanks(new String[] { phone, code })) {
			/* 149 */ return renderErrorParameter();
		}
		/* 151 */ if (!StringUtil.isPhone(phone)) {
			/* 152 */ return renderErrorPhone();
		}
		/* 154 */ String oldCode = JedisUtil.get(ConstUtils.CODE_PURPOSE.NEWREPLACE.getValue() + phone);
		/* 155 */ if (StringUtil.isBlanks(new String[] { oldCode })) {
			/* 156 */ return renderError("验证码已超时！");
		}
		/* 158 */ if (!code.equals(oldCode)) {
			/* 159 */ return renderErrorCode();
		}
		/* 161 */ User user = userDao.queryUserByPhone(phone);
		/* 162 */ if (user != null) {
			/* 163 */ return renderError("手机号码已注册！");
		}
		/* 165 */ ((User) curUser.setPhone(phone)).update();
		/* 166 */ updateRedisUser(curUser);
		/* 167 */ return renderMeg();
	}

	public Response userCode(String phone, String purpose, User curUser) {
		/* 171 */ if (StringUtil.isBlanks(new String[] { phone, purpose })) {
			/* 172 */ return renderErrorParameter();
		}
		/* 174 */ if (!StringUtil.isPhone(phone)) {
			/* 175 */ return renderErrorPhone();
		}
		/* 177 */ if ((!purpose.equals(ConstUtils.CODE_PURPOSE.OLDREPLACE.getValue())) &&
		/* 178 */ (!purpose.equals(ConstUtils.CODE_PURPOSE.NEWREPLACE.getValue()))) {
			/* 179 */ return renderError("验证码方式错误！");
		}
		/* 181 */ String code = StringUtil.getCount();
		/* 182 */ if (purpose.equals(ConstUtils.CODE_PURPOSE.OLDREPLACE.getValue())) {
			/* 183 */ if (curUser == null) {
				/* 184 */ return renderError("userToken参数错误或无效！");
			}
			/* 186 */ if (!phone.equals(curUser.getPhone())) {
				/* 187 */ return renderError("原手机号错误！");
			}
			/* 189 */ JedisUtil.set(ConstUtils.CODE_PURPOSE.OLDREPLACE.getValue() + phone, code, 180);
		}
		/* 191 */ if (purpose.equals(ConstUtils.CODE_PURPOSE.NEWREPLACE.getValue())) {
			/* 192 */ if (curUser == null) {
				/* 193 */ return renderError("userToken参数错误或无效！");
			}
			/* 195 */ User user = userDao.queryUserByPhone(phone);
			/* 196 */ if (user != null) {
				/* 197 */ return renderError("手机号码已注册！");
			}
			/* 199 */ JedisUtil.set(ConstUtils.CODE_PURPOSE.NEWREPLACE.getValue() + phone, code, 180);
		}
		/* 201 */ if (Global.DEVMODE) {
			/* 202 */ return renderResult(code);
		}
		/* 204 */ SmsUtil.sendSMS(phone, code);
		/* 205 */ return renderMeg();
	}
}
