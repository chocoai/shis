package com.mibo.modules.data.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;
import java.util.Date;

@SuppressWarnings({ "serial", "unchecked" })
public abstract class BaseGateway<M extends BaseGateway<M>> extends Model<M> implements IBean {
	public M setId(Integer id) {
		/* 13 */ set("id", id);
		/* 14 */ return (M) this;
	}

	public Integer getId() {
		/* 18 */ return getInt("id");
	}

	public M setProductName(String productName) {
		/* 22 */ set("product_name", productName);
		/* 23 */ return (M) this;
	}

	public String getProductName() {
		/* 27 */ return getStr("product_name");
	}

	public M setProductKey(String productKey) {
		/* 31 */ set("product_key", productKey);
		/* 32 */ return (M) this;
	}

	public String getProductKey() {
		/* 36 */ return getStr("product_key");
	}

	public M setDeviceName(String deviceName) {
		/* 40 */ set("device_name", deviceName);
		/* 41 */ return (M) this;
	}

	public String getDeviceName() {
		/* 45 */ return getStr("device_name");
	}

	public M setDeviceSecret(String deviceSecret) {
		/* 49 */ set("device_secret", deviceSecret);
		/* 50 */ return (M) this;
	}

	public String getDeviceSecret() {
		/* 54 */ return getStr("device_secret");
	}

	public M setDeviceState(Boolean deviceState) {
		/* 58 */ set("device_state", deviceState);
		/* 59 */ return (M) this;
	}

	public Boolean getDeviceState() {
		/* 63 */ return (Boolean) get("device_state");
	}

	public M setDeviceAlias(String deviceAlias) {
		/* 67 */ set("device_alias", deviceAlias);
		/* 68 */ return (M) this;
	}

	public String getDeviceAlias() {
		/* 72 */ return getStr("device_alias");
	}

	public M setVersionName(String versionName) {
		/* 76 */ set("version_name", versionName);
		/* 77 */ return (M) this;
	}

	public String getVersionName() {
		/* 81 */ return getStr("version_name");
	}

	public M setVersionNo(String versionNo) {
		/* 85 */ set("version_no", versionNo);
		/* 86 */ return (M) this;
	}

	public String getVersionNo() {
		/* 90 */ return getStr("version_no");
	}

	public M setAddTime(Date addTime) {
		/* 94 */ set("add_time", addTime);
		/* 95 */ return (M) this;
	}

	public Date getAddTime() {
		/* 99 */ return (Date) get("add_time");
	}
}