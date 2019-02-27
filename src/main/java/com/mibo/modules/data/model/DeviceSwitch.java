package com.mibo.modules.data.model;

import com.jfinal.plugin.activerecord.Db;
import com.mibo.modules.data.base.BaseDeviceSwitch;
import java.util.List;

@SuppressWarnings("serial")
public class DeviceSwitch extends BaseDeviceSwitch<DeviceSwitch> {
	/* 13 */ public static final DeviceSwitch dao = (DeviceSwitch) new DeviceSwitch().dao();

	public void addDeviceSwitch(Integer deviceId, int count) {
		/* 21 */ for (int i = 0; i <= count; i++) {
			/* 22 */ DeviceSwitch ds = new DeviceSwitch();
			/* 23 */ ds.setDeviceId(deviceId);
			/* 24 */ ds.setPlaceNo(Integer.valueOf(i));
			/* 25 */ ds.setSwitchState(Boolean.valueOf(false));
			/* 26 */ ds.save();
		}
	}

	//添加wifi插座
	public void addWifiDeviceSwitch(Integer deviceId) {
		DeviceSwitch ds = new DeviceSwitch();
		ds.setDeviceId(deviceId);
		ds.setPlaceNo(0);
		ds.setSwitchState(Boolean.valueOf(false));
		ds.save();
	}

	public List<DeviceSwitch> queryDeviceSwitchByDeviceId(Integer deviceId) {
		/* 36 */ String sql = "SELECT * FROM t_device_switch WHERE device_id = ?";
		/* 37 */ return find(sql, new Object[] { deviceId });
	}

	public void deleteDeviceSwitchByDeviceId(Integer deviceId) {
		/* 45 */ String sql = "DELETE FROM t_device_switch WHERE device_id = ?";
		/* 46 */ Db.delete(sql, new Object[] { deviceId });
	}
}

/*
 * Location:
 * C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\data\
 * model\DeviceSwitch.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */