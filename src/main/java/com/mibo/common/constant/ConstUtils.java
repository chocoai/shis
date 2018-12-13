package com.mibo.common.constant;

public interface ConstUtils {

	/**
	 * 是否
	 * 
	 * @author liqp
	 * 
	 */
	static enum YES_NO {
	/** 是 **/
	YES("1"),
	/** 否 **/
	NO("0");
		private final String yesNo;

		private YES_NO(final String yesNo) {
			this.yesNo = yesNo;
		}

		public String getValue() {
			return this.yesNo;
		}
	}

	/**
	 * 账户状态
	 * 
	 * @author liqp
	 *
	 */
	static enum ACCOUNT_STATUS {
		/**
		 * 正常
		 */
		NORMAL("nor"),
		/**
		 * 禁止
		 */
		FORBID("for");
		private final String accountStatus;

		private ACCOUNT_STATUS(final String accountStatus) {
			this.accountStatus = accountStatus;
		}

		public String getValue() {
			return this.accountStatus;
		}
	}

	/**
	 * 用户缓存key
	 * 
	 * @author liqp
	 *
	 */
	static enum USER_KEY {
		IDS("UserIds"), USERS("UserInfos");
		private final String userKey;

		private USER_KEY(final String userKey) {
			this.userKey = userKey;
		}

		public String getValue() {
			return this.userKey;
		}
	}

	/**
	 * 产品类型
	 * 
	 * @author liqp
	 *
	 */
	static enum PRODUCT_TYPE {
		/**
		 * 设备
		 */
		DEVICE("device"),
		/**
		 * 网关
		 */
		GATEWAY("gateway");
		private final String productType;

		private PRODUCT_TYPE(final String productType) {
			this.productType = productType;
		}

		public String getValue() {
			return this.productType;
		}
	}

	/**
	 * 验证码方式
	 * 
	 * @author liqp
	 *
	 */
	static enum CODE_PURPOSE {
		/**
		 * 旧手机号验证码
		 */
		OLDREPLACE("CodeOldReplace"),
		/**
		 * 新手机号验证码
		 */
		NEWREPLACE("CodeNewReplace"),
		/**
		 * 注册验证码
		 */
		REGISTER("CodeRegister"),
		/**
		 * 忘记密码验证码
		 */
		FORGET("CodeForget");
		private final String codePurpose;

		private CODE_PURPOSE(final String codePurpose) {
			this.codePurpose = codePurpose;
		}

		public String getValue() {
			return this.codePurpose;
		}
	}

	/**
	 * 缓存产品key
	 * 
	 * @author liqp
	 *
	 */
	static enum REDIS_PRODUCT {
		PRODUCTKEY("AllProductKeyList"), PRODUCTINFO("AllProductInfoList");
		private final String redisProduct;

		private REDIS_PRODUCT(final String redisProduct) {
			this.redisProduct = redisProduct;
		}

		public String getValue() {
			return this.redisProduct;
		}
	}

	/**
	 * 设备状态
	 * 
	 * @author liqp
	 *
	 */
	static enum DEVICE_STATUS {
		OFFLINE("OFFLINE"), ONLINE("ONLINE");
		private final String deviceStatus;

		private DEVICE_STATUS(final String deviceStatus) {
			this.deviceStatus = deviceStatus;
		}

		public String getValue() {
			return this.deviceStatus;
		}
	}

	static enum APP_TYPE {
		CLIENT("1");
		private final String appType;

		private APP_TYPE(final String appType) {
			this.appType = appType;
		}

		public String getValue() {
			return this.appType;
		}
	}
}