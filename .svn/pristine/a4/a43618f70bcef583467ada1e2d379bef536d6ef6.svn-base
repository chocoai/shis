package com.mibo.modules.data.model;

import com.mibo.common.constant.ConstUtils;
import com.mibo.modules.data.base.BaseUser;
import java.util.Date;

@SuppressWarnings("serial")
public class User extends BaseUser<User> {
	/* 12 */ public static final User dao = (User) new User().dao();

	public User queryUserByPhone(String phone) {
		/* 20 */ return (User) findFirst(getSql("user.queryUserByPhone"), new Object[] { phone });
	}

	public User register(String phone, String password, Date regDate) {
		/* 31 */ User user = new User();
		/* 32 */ user.setPhone(phone);
		/* 33 */ user.setPassword(password);
		/* 34 */ user.setRegisterTime(regDate);
		/* 35 */ user.setAccountStatus(ConstUtils.ACCOUNT_STATUS.NORMAL.getValue());
		/* 36 */ user.save();
		/* 37 */ return user;
	}

	public User queryUserByGgatewayId(Integer gatewayId) {
		/* 46 */ String sql = "SELECT b.* FROM t_user_gateway a LEFT JOIN t_user b ON a.user_id = b.id WHERE a.gateway_id = ? ORDER BY register_time DESC LIMIT 1";

		/* 48 */ return (User) findFirst(sql, new Object[] { gatewayId });
	}
}