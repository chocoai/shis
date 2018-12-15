package com.mibo.modules.data.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setPhone(java.lang.String phone) {
		set("phone", phone);
		return (M)this;
	}
	
	public java.lang.String getPhone() {
		return getStr("phone");
	}

	public M setPassword(java.lang.String password) {
		set("password", password);
		return (M)this;
	}
	
	public java.lang.String getPassword() {
		return getStr("password");
	}

	public M setLoginToken(java.lang.String loginToken) {
		set("login_token", loginToken);
		return (M)this;
	}
	
	public java.lang.String getLoginToken() {
		return getStr("login_token");
	}

	public M setLoginTime(java.util.Date loginTime) {
		set("login_time", loginTime);
		return (M)this;
	}
	
	public java.util.Date getLoginTime() {
		return get("login_time");
	}

	public M setRegisterTime(java.util.Date registerTime) {
		set("register_time", registerTime);
		return (M)this;
	}
	
	public java.util.Date getRegisterTime() {
		return get("register_time");
	}

	public M setAccountStatus(java.lang.String accountStatus) {
		set("account_status", accountStatus);
		return (M)this;
	}
	
	public java.lang.String getAccountStatus() {
		return getStr("account_status");
	}

}