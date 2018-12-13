package com.mibo.modules.data.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;
import java.util.Date;

@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTagProduct<M extends BaseTagProduct<M>> extends Model<M> implements IBean {
	public M setId(Integer id) {
		set("id", id);
		return (M) this;
	}

	public Integer getId() {
		return getInt("id");
	}

	public M setProductName(String productName) {
		set("product_name", productName);
		return (M) this;
	}

	public String getProductName() {
		return getStr("product_name");
	}

	public M setProductKeyword(String productKeyword) {
		set("product_keyword", productKeyword);
		return (M) this;
	}

	public String getProductKeyword() {
		return getStr("product_keyword");
	}

	public M setProductTime(Date productTime) {
		set("product_time", productTime);
		return (M) this;
	}

	public Date getProductTime() {
		return (Date) get("product_time");
	}
}