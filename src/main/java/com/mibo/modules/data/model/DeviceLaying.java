package com.mibo.modules.data.model;

import com.jfinal.plugin.activerecord.Db;
import com.mibo.modules.data.base.BaseDeviceLaying;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class DeviceLaying extends BaseDeviceLaying<DeviceLaying> {
	/* 14 */ public static final DeviceLaying dao = (DeviceLaying) new DeviceLaying().dao();

	public List<DeviceLaying> queryDeviceLayingByDeviceId(Integer deviceId) {
		/* 22 */ String sql = "SELECT * FROM t_device_laying\tWHERE device_id = ?";
		/* 23 */ return find(sql, new Object[] { deviceId });
	}

	public void addDeviceLaying(Integer deviceId, String staTime, String endTime) {
		/* 33 */ DeviceLaying deviceLaying = new DeviceLaying();
		/* 34 */ deviceLaying.setDeviceId(deviceId);
		/* 35 */ deviceLaying.setStaTime(staTime);
		/* 36 */ deviceLaying.setEndTime(endTime);
		/* 37 */ deviceLaying.setAddTime(new Date());
		/* 38 */ deviceLaying.save();
	}

	public void deleteDeviceLayingByDeviceId(Integer deviceId) {
		/* 46 */ String sql = "DELETE FROM t_device_laying WHERE device_id = ?";
		/* 47 */ Db.delete(sql, new Object[] { deviceId });
	}
}

/*
 * Location:
 * C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\data\
 * model\DeviceLaying.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */