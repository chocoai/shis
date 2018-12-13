package com.mibo.modules.data.model;

import com.jfinal.kit.Kv;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.mibo.modules.data.base.BaseDevice;
import java.util.Date;
import java.util.List;
@SuppressWarnings("serial")
public class Device extends BaseDevice<Device> {
	/* 16 */ public static final Device dao = (Device) new Device().dao();

	public Device queryDeviceById(Integer id) {
		/* 19 */ String sql = "SELECT * FROM t_device WHERE id = ?";
		/* 20 */ return (Device) findFirst(sql, new Object[] { id });
	}

	public Device addDevice(Integer gatewayId, String productName, String productKey, String deviceName,
			String deviceSecret, Date date) {
		/* 34 */ Device device = new Device();
		try {
			/* 36 */ device.setGatewayId(gatewayId);
			/* 37 */ device.setProductName(productName);
			/* 38 */ device.setProductKey(productKey);
			/* 39 */ device.setDeviceName(deviceName);
			/* 40 */ device.setDeviceSecret(deviceSecret);
			/* 41 */ device.setAddTime(date);
			/* 42 */ if (device.save()) {
				/* 43 */ return device;
			}
		} catch (Exception e) {
			/* 46 */ LogKit.error("addDevice", e);
		}
		/* 48 */ return null;
	}

	public Device queryDeviceByDeviceName(String deviceName) {
		/* 57 */ String sql = "SELECT * FROM t_device WHERE device_name = ?";
		/* 58 */ return (Device) findFirst(sql, new Object[] { deviceName });
	}

	public List<Device> queryDevicListByGatewayId(Integer gatewayId) {
		/* 69 */ return find(getSql("device.queryDevicListByGatewayId"), new Object[] { gatewayId });
	}

	public List<Device> qeruyProductKeyListByUserId(String userId) {
		/* 78 */ return find(getSql("device.qeruyProductKeyListByUserId"), new Object[] { Integer.valueOf(userId) });
	}

	public Page<Device> qeruyDeviceNameByProductKeyUserIdPage(String productKey, String userId, int pageNo) {
		/* 88 */ Kv kv = Kv.by("productKey", productKey).set("userId", Integer.valueOf(userId));
		/* 89 */ return paginate(pageNo, 50, getSqlPara(getSql("device.qeruyDeviceNameListByProductKeyUserId"), kv));
	}

	public List<Device> queryDeviceByUserIdProductNameList(String userId, String productName) {
		/* 99 */ return find(getSql("device.queryDeviceByUserIdProductNameList"),
				new Object[] { Integer.valueOf(Integer.parseInt(userId)), "%" + productName + "%" });
	}

	public List<Device> queryDeviceByUserIdAliasList(String userId, String alias) {
		/* 109 */ return find(getSql("device.queryDeviceByUserIdAliasList"),
				new Object[] { Integer.valueOf(Integer.parseInt(userId)), alias });
	}

	public int delteALLByGatewayId(Integer gatewayId) {
		/* 117 */ String sql = "DELETE FROM t_device WHERE gateway_id = ?";
		/* 118 */ return Db.delete(sql, new Object[] { gatewayId });
	}

	public List<Device> queryAllDeviceByUserId(Integer userId) {
		/* 122 */ String sql = "SELECT a.*,b.device_name AS gatewayName FROM t_device a LEFT JOIN t_gateway b ON a.gateway_id = b.id WHERE gateway_id IN (SELECT gateway_id FROM t_user_gateway WHERE user_id = ?);";

		/* 126 */ return find(sql, new Object[] { userId });
	}

	public void updateBatteryPush(Integer userId) {
		/* 133 */ String sql = "UPDATE t_device SET battery_push = 0 WHERE gateway_id\tIN (SELECT gateway_id FROM t_user_gateway WHERE user_id = ?)";

		/* 135 */ Db.update(sql, new Object[] { userId });
	}
}

/*
 * Location:
 * C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\data\
 * model\Device.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */