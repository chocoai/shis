package com.mibo.modules.data.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseDevice<M extends BaseDevice<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setGatewayId(java.lang.Integer gatewayId) {
		set("gateway_id", gatewayId);
		return (M)this;
	}

	public java.lang.Integer getGatewayId() {
		return getInt("gateway_id");
	}

	public M setProductName(java.lang.String productName) {
		set("product_name", productName);
		return (M)this;
	}

	public java.lang.String getProductName() {
		return getStr("product_name");
	}

	public M setProductKey(java.lang.String productKey) {
		set("product_key", productKey);
		return (M)this;
	}

	public java.lang.String getProductKey() {
		return getStr("product_key");
	}

	public M setDeviceName(java.lang.String deviceName) {
		set("device_name", deviceName);
		return (M)this;
	}

	public java.lang.String getDeviceName() {
		return getStr("device_name");
	}

	public M setDeviceSecret(java.lang.String deviceSecret) {
		set("device_secret", deviceSecret);
		return (M)this;
	}

	public java.lang.String getDeviceSecret() {
		return getStr("device_secret");
	}

	public M setDeviceState(java.lang.Boolean deviceState) {
		set("device_state", deviceState);
		return (M)this;
	}

	public java.lang.Boolean getDeviceState() {
		return get("device_state");
	}

	public M setIsLaying(java.lang.Boolean isLaying) {
		set("is_laying", isLaying);
		return (M)this;
	}

	public java.lang.Boolean getIsLaying() {
		return get("is_laying");
	}

	public M setIsPush(java.lang.Boolean isPush) {
		set("is_push", isPush);
		return (M)this;
	}

	public java.lang.Boolean getIsPush() {
		return get("is_push");
	}

	public M setIsLayingTime(java.lang.Boolean isLayingTime) {
		set("is_laying_time", isLayingTime);
		return (M)this;
	}

	public java.lang.Boolean getIsLayingTime() {
		return get("is_laying_time");
	}

	public M setIsSwitch(java.lang.Boolean isSwitch) {
		set("is_switch", isSwitch);
		return (M)this;
	}

	public java.lang.Boolean getIsSwitch() {
		return get("is_switch");
	}

	public M setBatteryAlarm(java.lang.String batteryAlarm) {
		set("battery_alarm", batteryAlarm);
		return (M)this;
	}

	public java.lang.String getBatteryAlarm() {
		return getStr("battery_alarm");
	}

	public M setDeviceAlias(java.lang.String deviceAlias) {
		set("device_alias", deviceAlias);
		return (M)this;
	}

	public java.lang.String getDeviceAlias() {
		return getStr("device_alias");
	}

	public M setAddTime(java.util.Date addTime) {
		set("add_time", addTime);
		return (M)this;
	}

	public java.util.Date getAddTime() {
		return get("add_time");
	}

	public M setBatteryPush(java.lang.Boolean batteryPush) {
		set("battery_push",batteryPush);
		return (M)this;
	}

	public M setCurrentLevel(java.lang.Integer CurrentLevel) {
		set("current_level", CurrentLevel);
		return (M)this;
	}

	public java.lang.Integer getCurrentLevel() {
		return getInt("current_level");
	}


	public M setOnLevel(java.lang.Integer OnLevel) {
		set("on_level", OnLevel);
		return (M)this;
	}

	public java.lang.Integer getOnLevel() {
		return getInt("on_level");
	}


	public java.lang.Boolean getBatteryPush(){
		return get("battery_push");
	}

	public M setUserId(java.lang.Integer UserId) {
		set("user_id", UserId);
		return (M)this;
	}

	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	public M setDeviceProtocolType(java.lang.Integer DeviceProtocolType) {
		set("device_protocol_type", DeviceProtocolType);
		return (M)this;
	}

	public java.lang.Integer getDeviceProtocolType() {
		return getInt("device_protocol_type");
	}


}
