package com.mibo.common.constant;

import com.jfinal.kit.Prop;

public class Global {

	private static Prop p = loadConfig();

	public static Prop loadConfig() {
		return com.jfinal.kit.PropKit.use("config.properties");
	}

	public static final String HOST = p.get("host");

	public static final int pageSize = 20;

	public static final String LOADURL = HOST + "/shis";

	public static final int CODE_NOT_LOGIN = 100;

	public static final int CODE_REQUEST_ERROR = 101;

	public static final int CODE_INVALID_TOKEN = 102;

	public static final int CODE_REQUEST_ILLEGAL = 103;

	public static final int CODE_USERS_SEAL = 104;

	public static final int CODE_DEVICE_ERROR = 105;

	public static final int CODE_SUCCESS = 200;

	public static final int CODE_NOT_FOUND = 404;

	public static final int CODE_SERVICE_ERROR = 500;

	public static final String GEN_TABLEPREFIX = p.get("gen.tablePrefix");

	public static final String GEN_MODELPATH = p.get("gen.modelPath");

	public static final boolean DEVMODE = p.getBoolean("devMode", Boolean.valueOf(true)).booleanValue();

	public static final String REDIS_DEVICE = "RedisDevices";

	public static String getKeys(String key) {
		return p.get(key);
	}
}