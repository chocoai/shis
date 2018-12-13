package com.mibo.common.config;

import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.mibo.common.constant.Global;
import com.mibo.common.handler.NotFoundHandler;
import com.mibo.common.interceptor.ErrorInterceptor;
import com.mibo.common.route.AppRouter;
import com.mibo.common.util.JedisUtil;
import com.mibo.modules.data.model._MappingKit;

public class SysConfig extends JFinalConfig {
	private static Prop p = Global.loadConfig();

	public void configConstant(Constants me) {
		me.setJsonFactory(MixedJsonFactory.me());
		me.setDevMode(p.getBoolean("devMode", Boolean.valueOf(false)).booleanValue());
		me.setBaseUploadPath(p.get("uploadPath"));
		me.setMaxPostSize(104857600);
		me.setError404View("/common/404/404.html");
	}

	public void configRoute(Routes me) {
		me.add(new AppRouter());
	}

	public void configEngine(Engine me) {
		me.setDevMode(p.getBoolean("engineDevMode", Boolean.valueOf(false)).booleanValue());
	}

	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
	}

	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = createDruidPlugin();
		WallFilter wallFilter = new WallFilter();
		wallFilter.setDbType("mysql");
		me.add(druidPlugin);

		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);

		arp.setBaseSqlTemplatePath(PathKit.getRootClassPath() + "/sql");
		arp.addSqlTemplate("all.sql");

		arp.setShowSql(p.getBoolean("showSql", Boolean.valueOf(false)).booleanValue());

		_MappingKit.mapping(arp);
		me.add(arp);
	}

	public void configInterceptor(Interceptors me) {
		me.add(new ErrorInterceptor());
	}

	public void configHandler(Handlers me) {
		me.add(new NotFoundHandler());
		me.add(new ContextPathHandler("ctx"));
	}

	public void afterJFinalStart() {
		JedisUtil.del("RedisDevices");
		LogKit.warn("执行清除所有设备key==========>");
	}

	public void beforeJFinalStop() {
	}
}