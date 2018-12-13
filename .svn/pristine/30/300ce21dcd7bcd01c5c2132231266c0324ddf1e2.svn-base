package com.mibo.modules.data.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;
import java.util.Date;

@SuppressWarnings({ "serial", "unchecked" })
public abstract class BaseAppVersion<M extends BaseAppVersion<M>> extends Model<M> implements IBean {
	public M setId(Integer id) {
		set("id", id);
		return (M) this;
	}

	public Integer getId() {
		return getInt("id");
	}

	public M setAppType(String appType) {
		set("app_type", appType);
		return (M) this;
	}

	public String getAppType() {
		return getStr("app_type");
	}

	public M setAppMark(Integer appMark) {
		set("app_mark", appMark);
		return (M) this;
	}

	public Integer getAppMark() {
		return getInt("app_mark");
	}

	public M setIsForcibly(String isForcibly) {
		set("is_forcibly", isForcibly);
		return (M) this;
	}

	public String getIsForcibly() {
		return getStr("is_forcibly");
	}

	public M setContent(String content) {
		set("content", content);
		return (M) this;
	}

	public String getContent() {
		return getStr("content");
	}

	public M setAppPackage(String appPackage) {
		set("app_package", appPackage);
		return (M) this;
	}

	public String getAppPackage() {
		return getStr("app_package");
	}

	public M setIsPublish(String isPublish) {
		set("is_publish", isPublish);
		return (M) this;
	}

	public String getIsPublish() {
		return getStr("is_publish");
	}

	public M setCreateBy(String createBy) {
		set("create_by", createBy);
		return (M) this;
	}

	public String getCreateBy() {
		return getStr("create_by");
	}

	public M setCreateDate(Date createDate) {
		set("create_date", createDate);
		return (M) this;
	}

	public Date getCreateDate() {
		return (Date) get("create_date");
	}

	public M setUpdateBy(String updateBy) {
		set("update_by", updateBy);
		return (M) this;
	}

	public String getUpdateBy() {
		return getStr("update_by");
	}

	public M setUpdateDate(Date updateDate) {
		set("update_date", updateDate);
		return (M) this;
	}

	public Date getUpdateDate() {
		return (Date) get("update_date");
	}

	public M setRemarks(String remarks) {
		set("remarks", remarks);
		return (M) this;
	}

	public String getRemarks() {
		return getStr("remarks");
	}

	public M setDelFlag(String delFlag) {
		set("del_flag", delFlag);
		return (M) this;
	}

	public String getDelFlag() {
		return getStr("del_flag");
	}
}