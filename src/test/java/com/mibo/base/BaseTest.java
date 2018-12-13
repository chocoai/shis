package com.mibo.base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.mibo.common.constant.Global;
import com.mibo.modules.data.model._MappingKit;

public class BaseTest {
	protected static DruidPlugin dp;
	protected static ActiveRecordPlugin arp;

	public static Prop loadConfig() {
		return Global.loadConfig();
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Prop p = loadConfig();
		dp = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
		dp.start();
		arp = new ActiveRecordPlugin(dp);
		arp.setBaseSqlTemplatePath(PathKit.getWebRootPath() + "\\target\\classes\\sql");
		arp.addSqlTemplate("all.sql");
		arp.setShowSql(true);
		_MappingKit.mapping(arp);
		arp.start();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		arp.stop();
		dp.stop();
	}
	
	/**
	 * 打印对象
	 * 
	 * @param obj
	 */
	public void print(Object obj) {
		System.out.println(JsonKit.toJson(obj));
	}
}
