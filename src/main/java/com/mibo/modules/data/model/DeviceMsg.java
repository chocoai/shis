package com.mibo.modules.data.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.mibo.modules.data.base.BaseDeviceMsg;
import java.util.List;

@SuppressWarnings("serial")
public class DeviceMsg extends BaseDeviceMsg<DeviceMsg> {
	/* 14 */ public static final DeviceMsg dao = (DeviceMsg) new DeviceMsg().dao();

	public String getProductName() {
		/* 17 */ return (String) get("productName");
	}

	/* 20 */ public String getDeviceName() {
		return (String) get("deviceName");
	}

	public Page<DeviceMsg> queryPageDeviceMessage(String userId, int pageNo) {
		/* 30 */ String sql = "SELECT a.*,b.product_name as productName ,b.device_name as deviceName";
		/* 31 */ String from = "FROM t_device_msg a LEFT JOIN t_device b ON a.device_id = b.id WHERE a.device_id IN (SELECT b1.id FROM t_user_gateway a1 LEFT JOIN t_device b1 ON a1.gateway_id = b1.gateway_id WHERE a1.user_id = ?) ORDER BY msg_time DESC";

		/* 35 */ return paginate(pageNo, 50, sql, from, new Object[] { userId });
	}

	public Page<DeviceMsg> queryPageDeviceMessageByDeviceName(String userId, List<Device> listDevice, int pageNo) {
		/* 46 */ String sql = "SELECT a.product_name AS productName, a.device_name AS deviceName,b.* ";
		/* 47 */ String from = "FROM t_device a LEFT JOIN t_device_msg b ON a.device_name = SUBSTRING_INDEX(SUBSTRING_INDEX(b.topic, '/',- 2 ), '/', 1) WHERE a.user_id = ? ";

		/* 50 */ for (Device dev : listDevice) {
			/* 51 */ from = from + "AND a.device_name = '" + dev.getDeviceName() + "' ";
		}
		/* 53 */ from = from + "ORDER BY msg_time DESC";
		/* 54 */ return paginate(pageNo, 50, sql, from, new Object[] { userId });
	}

	public void batchDelteById(String[] id) {
		/* 58 */ String sql = "DELETE FROM t_device_msg WHERE id IN(";
		/* 59 */ for (int i = 0; i < id.length; i++) {
			/* 60 */ sql = sql + id[i] + ",";
		}
		/* 62 */ sql = sql.substring(0, sql.length() - 1);
		/* 63 */ sql = sql + ")";
		/* 64 */ Db.update(sql);
	}

	public Page<DeviceMsg> searchDeviceMsgByUserIdProductName(int pageNo, Integer userId, String productName) {
		/* 74 */ String sql = "SELECT a.*,b.device_name AS deviceName,b.product_name AS productName";
		/* 75 */ String from = "FROM t_device_msg a LEFT JOIN t_device b ON a.device_id = b.id WHERE a.device_id IN (SELECT a.id FROM t_device a WHERE a.gateway_id IN (SELECT a.gateway_id FROM t_user_gateway a LEFT JOIN t_gateway b ON a.gateway_id = b.id WHERE a.user_id = ?) AND a.product_name LIKE '%"
				+

				/* 78 */ productName + "%') ORDER BY a.msg_time DESC";
		/* 79 */ return paginate(pageNo, 50, sql, from, new Object[] { userId });
	}

	public Page<DeviceMsg> searchDeviceMsgByUserIdalias(int pageNo, Integer userId, String alias) {
		/* 90 */ String sql = "SELECT a.*,b.device_name AS deviceName,b.product_name AS productName";
		/* 91 */ String from = "FROM t_device_msg a LEFT JOIN t_device b ON a.device_id = b.id WHERE a.device_id IN (SELECT a.id FROM t_device a WHERE a.gateway_id IN (SELECT a.gateway_id FROM t_user_gateway a LEFT JOIN t_gateway b ON a.gateway_id = b.id WHERE a.user_id = ?) AND a.device_alias LIKE '%"
				+

				/* 94 */ alias + "%') ORDER BY a.msg_time DESC";
		/* 95 */ return paginate(pageNo, 50, sql, from, new Object[] { userId });
	}

	public void deleteDeviceMessageByDeviceId(Integer deviceId) {
		/* 103 */ String sql = "DELETE FROM t_device_msg WHERE device_id = ?";
		/* 104 */ Db.delete(sql, new Object[] { deviceId });
	}

	public void deleteALLMessage(Integer userId) {
		/* 112 */ String sql = "DELETE FROM t_device_msg WHERE device_id IN (SELECT id FROM t_device WHERE gateway_id IN (SELECT gateway_id FROM t_user_gateway WHERE user_id = ?))";

		/* 115 */ Db.delete(sql, new Object[] { userId });
	}

	public Page<DeviceMsg> searchDeviceMsgByTagProductKeyword(Integer userId, int pageNo,
															  List<TagProduct> tagProductList, String keyword) {
		String sql = "SELECT a.*,b.device_name AS deviceName,b.product_name AS productName";
		String from = "FROM t_device_msg a LEFT JOIN t_device b ON a.device_id = b.id "
				+ "WHERE a.device_id IN (SELECT a.id FROM t_device a "
				+ "WHERE a.gateway_id IN (SELECT a.gateway_id "
				+ "FROM t_user_gateway a LEFT JOIN "
				+ "t_gateway b ON a.gateway_id = b.id WHERE a.user_id = ?) "
				+ "AND a.device_alias LIKE '%"+keyword+"%' ";
		if ((tagProductList != null) && (tagProductList.size() > 0)) {
			from = from + "OR (";
			for (TagProduct tagProduct : tagProductList) {
				from = from + "a.product_name LIKE '%" + tagProduct.getProductKeyword() + "%' OR ";
			}
			from = from.substring(0, from.length() - 4);
			from = from + ") ";
		}
		from = from + ") ORDER BY a.msg_time DESC";
		return paginate(pageNo, 50, sql, from, userId);
	}
}