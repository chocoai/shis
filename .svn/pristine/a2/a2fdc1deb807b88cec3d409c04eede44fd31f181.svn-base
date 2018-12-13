package com.mibo.modules.data.model;

import com.mibo.modules.data.base.BaseGateway;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Gateway extends BaseGateway<Gateway> {
	/* 13 */ public static final Gateway dao = (Gateway) new Gateway().dao();

	public Gateway addGateway(String productName, String productKey, String deviceName, String deviceSecret,
			Date date) {
		/* 25 */ Gateway gateway = new Gateway();
		/* 26 */ gateway.setProductName(productName);
		/* 27 */ gateway.setProductKey(productKey);
		/* 28 */ gateway.setDeviceName(deviceName);
		/* 29 */ gateway.setDeviceSecret(deviceSecret);
		/* 30 */ gateway.setAddTime(date);
		/* 31 */ gateway.save();
		/* 32 */ return gateway;
	}

	public Gateway queryGatewayByUserIdDeviceName(String userId, String deviceName) {
		/* 42 */ return (Gateway) findFirst(getSql("gateway.queryGatewayByUserIdDeviceName"),
				new Object[] { Integer.valueOf(Integer.parseInt(userId)), deviceName });
	}

	public Gateway queryGatewayByDeviceName(String deviceName) {
		/* 51 */ return (Gateway) findFirst(getSql("gateway.queryGatewayByDeviceName"), new Object[] { deviceName });
	}

	public List<Gateway> getGatewayByUserId(Integer id) {
		/* 60 */ return find(getSql("gateway.getGatewayByUserId"), new Object[] { id });
	}

	public List<Gateway> queryGatewayListByUserId(Integer userId) {
		/* 69 */ String sql = "SELECT b.* FROM t_user_gateway a LEFT JOIN t_gateway b ON a.gateway_id = b.id WHERE a.user_id = ?";
		/* 70 */ return find(sql, new Object[] { userId });
	}
}

/*
 * Location:
 * C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\data\
 * model\Gateway.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */