package com.mibo.modules.data.model;

import com.mibo.modules.data.base.BaseUserGateway;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class UserGateway extends BaseUserGateway<UserGateway> {
	/* 13 */ public static final UserGateway dao = (UserGateway) new UserGateway().dao();

	public UserGateway queryUserGatewayByUserIdGatewayId(Integer userId, Integer gatewayId) {
		/* 21 */ String sql = "SELECT * FROM t_user_gateway WHERE user_id = ? AND gateway_id = ?";
		/* 22 */ return (UserGateway) findFirst(sql, new Object[] { userId, gatewayId });
	}

	public List<UserGateway> queryUserGatewayByGatewayId(Integer gatewayId) {
		/* 31 */ String sql = "SELECT * FROM t_user_gateway WHERE gateway_id = ?";
		/* 32 */ return find(sql, new Object[] { gatewayId });
	}

	public UserGateway addUserGateway(Integer userId, Integer gatewayId, Date addTime) {
		/* 43 */ UserGateway ug = new UserGateway();
		/* 44 */ ug.setUserId(userId);
		/* 45 */ ug.setGatewayId(gatewayId);
		/* 46 */ ug.setAddTime(addTime);
		/* 47 */ ug.save();
		/* 48 */ return ug;
	}

	public List<UserGateway> queryUserGatewayByUserId(Integer userId) {
		/* 57 */ String sql = "SELECT * FROM t_user_gateway WHERE user_id = ?";
		/* 58 */ return find(sql, new Object[] { userId });
	}
}