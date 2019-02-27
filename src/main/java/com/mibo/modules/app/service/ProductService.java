package com.mibo.modules.app.service;

import com.aliyuncs.iot.model.v20170420.PubResponse;
import com.aliyuncs.iot.model.v20180120.GetDeviceStatusResponse;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailResponse;
import com.aliyuncs.iot.model.v20180120.RegisterDeviceResponse;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mibo.common.constant.ConstUtils;
import com.mibo.common.result.Response;
import com.mibo.common.util.StringUtil;
import com.mibo.modules.al.sma.util.DeviceTypeTAG;
import com.mibo.modules.al.sma.util.DeviceUtil;
import com.mibo.modules.al.sma.util.ProductUtil;
import com.mibo.modules.data.model.Device;
import com.mibo.modules.data.model.DeviceLaying;
import com.mibo.modules.data.model.DeviceMsg;
import com.mibo.modules.data.model.DeviceSwitch;
import com.mibo.modules.data.model.Gateway;
import com.mibo.modules.data.model.TagProduct;
import com.mibo.modules.data.model.User;
import com.mibo.modules.data.model.UserGateway;
import com.mibo.modules.data.model.UserScene;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProductService extends com.mibo.common.base.BaseService {
	/* 35 */ public static final ProductService productService = new ProductService();
	/* 36 */ private static final User userDao = User.dao;
	/* 37 */ private static final Gateway gatewayDao = Gateway.dao;
	/* 38 */ private static final UserGateway userGatewayDao = UserGateway.dao;
	/* 39 */ private static final Device deviceDao = Device.dao;
	/* 40 */ private static final TagProduct tagProduct = TagProduct.dao;
	/* 41 */ private static final UserScene userSceneDao = UserScene.dao;
	/* 42 */ private static final DeviceSwitch deviceSwitchDao = DeviceSwitch.dao;
	/* 43 */ private static final DeviceMsg deviceMessageDao = DeviceMsg.dao;
	/* 44 */ private static final DeviceLaying deviceLayingDao = DeviceLaying.dao;

	@Before({ Tx.class })
	public Response registerGateway(String userId, String productModel, String deviceName) {
		/* 56 */ if (StringUtil.isBlanks(new String[] { productModel, deviceName })) {
			/* 57 */ return renderErrorParameter();
		}
		/* 59 */ this.map = getMap();

		/* 61 */ Gateway gateway = gatewayDao.queryGatewayByDeviceName(deviceName);
		/* 62 */ if (gateway != null) {
			/* 63 */ this.map.put("ProductKey", gateway.getProductKey());
			/* 64 */ this.map.put("DeviceSecret", gateway.getDeviceSecret());

			/* 66 */ UserGateway userGateway = userGatewayDao.queryUserGatewayByUserIdGatewayId(Integer.valueOf(userId),
					/* 67 */ gateway.getId());
			/* 68 */ if (userGateway == null) {
				/* 70 */ userGatewayDao.addUserGateway(Integer.valueOf(userId), gateway.getId(), new Date());
			}
			/* 72 */ return renderResult(this.map);
		}
		/* 74 */ Date date = new Date();

		/* 76 */ QueryDeviceDetailResponse deviceInfo = DeviceUtil.queryDeviceByProductKeyDeviceName(deviceName);
		/* 77 */ if (deviceInfo != null) {
			/* 78 */ String productKey = deviceInfo.getData().getProductKey();
			/* 79 */ String deviceSecret = deviceInfo.getData().getDeviceSecret();
			/* 80 */ this.map.put("ProductKey", productKey);
			/* 81 */ this.map.put("DeviceSecret", deviceSecret);

			/* 83 */ gateway = gatewayDao.addGateway(productModel, productKey, deviceName, deviceSecret, date);

			/* 85 */ userGatewayDao.addUserGateway(Integer.valueOf(userId), gateway.getId(), date);
			/* 86 */ return renderResult(this.map);
		}

		/* 89 */ String productKey = ProductUtil.queryProductKeyByProductModel(productModel);

		/* 91 */ RegisterDeviceResponse response = DeviceUtil.registerDevice(productKey, deviceName);
		/* 92 */ if (!response.getSuccess().booleanValue()) {
			/* 93 */ return renderError(response.getErrorMessage());
		}
		/* 95 */ String deviceSecret = response.getData().getDeviceSecret();
		/* 96 */ this.map.put("ProductKey", productKey);
		/* 97 */ this.map.put("DeviceSecret", deviceSecret);

		/* 99 */ gateway = gatewayDao.addGateway(productModel, productKey, deviceName, deviceSecret, date);

		/* 101 */ userGatewayDao.addUserGateway(Integer.valueOf(userId), gateway.getId(), date);
		/* 102 */ return renderResult(this.map);
	}

	@Before({ Tx.class })
	public Response registerDevice(String userId, String gatewayId, String productModel, String deviceName) {
		Gateway gateway=null;
		if (gatewayId != null) {
			if (StringUtil.isBlanks(new String[] { gatewayId, productModel, deviceName })) {
				return renderErrorParameter();
			}
			gateway = (Gateway) gatewayDao.findById(gatewayId);
			if (gateway == null) {
				return renderError("网关编号错误!");
			}
			UserGateway userGateway = userGatewayDao.queryUserGatewayByUserIdGatewayId(Integer.valueOf(userId),Integer.valueOf(gatewayId));
			if (userGateway == null) {
				userGatewayDao.addUserGateway(Integer.valueOf(userId), Integer.valueOf(gatewayId), new Date());
			}
		}
		this.map = getMap();

		Device device = getRedisDevice(deviceName);
		if (device == null) {
			device = deviceDao.queryDeviceByDeviceName(deviceName);
		}
		if (device != null) {
			if (gatewayId != null && Integer.parseInt(gatewayId) != device.getGatewayId().intValue()) {
				User user = userDao.queryUserByGgatewayId(device.getGatewayId());
				return renderError(105, "该设备已在" + user.getPhone() + "帐号下绑定！");
			}else if (gatewayId == null && device != null) {//网关id为空，设备独自运行
				return renderError(105, "该设备已被绑定！");
			}
			this.map.put("ProductKey", device.getProductKey());
			this.map.put("DeviceSecret", device.getDeviceSecret());
			return renderResult(this.map);
		}
		Date date = new Date();
		QueryDeviceDetailResponse deviceInfo = DeviceUtil.queryDeviceByProductKeyDeviceName(deviceName);
		if (deviceInfo != null) {
			String productKey = deviceInfo.getData().getProductKey();
			String deviceSecret = deviceInfo.getData().getDeviceSecret();
			this.map.put("ProductKey", productKey);
			this.map.put("DeviceSecret", deviceSecret);

			if (gatewayId != null) {
				device = deviceDao.addDevice(gateway.getId(), productModel, productKey, deviceName, deviceSecret,date);
			}else { //添加wifi设备
				System.out.println(userId+"-----"+productModel+"----"+productKey+"-----"+deviceName+"-----"+deviceSecret);
				device = deviceDao.addWifiDevice(Integer.parseInt(userId), productModel, productKey, deviceName, deviceSecret,date);
			}
			if (device != null) {
				setRedisDevice(device);

				if (DeviceTypeTAG.isSensor(productModel)) {
					device.setIsPush(Boolean.valueOf(true));
					device.setIsLaying(Boolean.valueOf(true));
					device.update();
				}
				if (DeviceTypeTAG.isControl(productModel)) {
					device.setIsSwitch(Boolean.valueOf(true));
					device.update();
					int count = DeviceTypeTAG.controlCount(productModel);
					deviceSwitchDao.addDeviceSwitch(device.getId(), count);
				}
			}
			return renderResult(this.map);
		}

		String productKey = ProductUtil.queryProductKeyByProductModel(productModel);

		RegisterDeviceResponse response = DeviceUtil.registerDevice(productKey, deviceName);
		if (!response.getSuccess().booleanValue()) {
			return renderError(response.getErrorMessage());
		}
		String deviceSecret = response.getData().getDeviceSecret();
		this.map.put("ProductKey", productKey);
		this.map.put("DeviceSecret", deviceSecret);

		device = deviceDao.addDevice(gateway.getId(), productModel, productKey, deviceName, deviceSecret,date);
		if (device != null) {
			setRedisDevice(device);

			if (DeviceTypeTAG.isSensor(productModel)) {
				device.setIsPush(Boolean.valueOf(true));
				device.setIsLaying(Boolean.valueOf(true));
				device.update();
			}
			if (DeviceTypeTAG.isControl(productModel)) {
				device.setIsSwitch(Boolean.valueOf(true));
				device.update();
				int count = DeviceTypeTAG.controlCount(productModel);
				deviceSwitchDao.addDeviceSwitch(device.getId(), count);
			}
		}
		return renderResult(this.map);
	}

	private List<Gateway> synchroUserGatewayList(String userId) {
		/* 214 */ List<Gateway> gatewayList = gatewayDao.queryGatewayListByUserId(Integer.valueOf(userId));
		/* 215 */ for (Gateway gat : gatewayList) {
			/* 216 */ boolean deviceState = false;
			/* 217 */ GetDeviceStatusResponse response = DeviceUtil.getDeviceStatus(gat.getProductKey(),
					gat.getDeviceName());
			/* 218 */ if (response.getSuccess().booleanValue()) {
				/* 219 */ if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.OFFLINE.getValue())) {
					/* 220 */ deviceState = false;
				}
				/* 222 */ if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.ONLINE.getValue())) {
					/* 223 */ deviceState = true;
				}
				/* 225 */ gat.setDeviceState(Boolean.valueOf(deviceState));
				/* 226 */ gat.update();
			}
		}
		/* 229 */ return gatewayList;
	}

	public Response getGatewayList(String userId) {
		/* 240 */ return renderResult(synchroUserGatewayList(userId));
	}

	//wifi设备列表
	public Response getWifiDeviceList(String userId) {
		/* 240 */ return renderResult(synchroUserWifiDeviceList(userId));
	}

	private List<Device> synchroUserWifiDeviceList(String userId) {
		List<Device> deviceList = deviceDao.queryWifiDeviceListByUserId(Integer.valueOf(userId));
		for (Device device : deviceList) {
			boolean deviceState = false;
			GetDeviceStatusResponse response = DeviceUtil.getDeviceStatus(device.getProductKey(),device.getDeviceName());
			if (response.getSuccess().booleanValue()) {
				if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.OFFLINE.getValue())) {
					deviceState = false;
				}
				if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.ONLINE.getValue())) {
					deviceState = true;
				}
				device.setDeviceState(Boolean.valueOf(deviceState));
				device.update();
			}
		}
		/* 229 */ return deviceList;
	}

	public Response gatewayDetails(String gatewayId) {
		/* 249 */ if (StringUtil.isBlanks(new String[] { gatewayId })) {
			/* 250 */ return renderErrorParameter();
		}
		/* 252 */ Gateway gateway = (Gateway) gatewayDao.findById(gatewayId);
		/* 253 */ if (gateway == null) {
			/* 254 */ return renderError("网关未注册，请添加网关!");
		}
		/* 256 */ GetDeviceStatusResponse response = DeviceUtil.getDeviceStatus(gateway.getProductKey(),
				gateway.getDeviceName());
		/* 257 */ if (response.getSuccess().booleanValue()) {
			/* 258 */ if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.OFFLINE.getValue())) {
				/* 259 */ gateway.setDeviceState(Boolean.valueOf(false));
			}
			/* 261 */ if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.ONLINE.getValue())) {
				/* 262 */ gateway.setDeviceState(Boolean.valueOf(true));
			}
			/* 264 */ gateway.update();
		}
		/* 266 */ return renderResult(gateway);
	}

	public Response getDevicList(String gatewayId) {
		/* 276 */ if (StringUtil.isBlanks(new String[] { gatewayId })) {
			/* 277 */ return renderErrorParameter();
		}

		/* 280 */ List<Device> list = deviceDao.queryDevicListByGatewayId(Integer.valueOf(gatewayId));
		/* 281 */ if ((list == null) || (list.size() == 0)) {
			/* 282 */ return renderMeg();
		}

		/* 285 */ synchroGatewayDeviceList(list);
		/* 286 */ return renderResult(list);
	}

	private void synchroGatewayDeviceList(List<Device> list) {
		/* 295 */ for (Device device : list) {
			/* 296 */ GetDeviceStatusResponse response = DeviceUtil.getDeviceStatus(device.getProductKey(),
					/* 297 */ device.getDeviceName());
			/* 298 */ if (response.getSuccess().booleanValue()) {
				/* 299 */ if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.OFFLINE.getValue())) {
					/* 300 */ device.setDeviceState(Boolean.valueOf(false));
				}
				/* 302 */ if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.ONLINE.getValue())) {
					/* 303 */ device.setDeviceState(Boolean.valueOf(true));
				}
				/* 305 */ updateDeviceStatusByTime(device);
				/* 306 */ device.update();
			}
		}
	}

	private void updateDeviceStatusByTime(Device device) {
		/* 317 */ long deviceTime = device.getAddTime().getTime();

		/* 319 */ long sysTime = new Date().getTime();

		/* 321 */ if (sysTime - deviceTime <= 10000L) {
			/* 322 */ device.setDeviceState(Boolean.valueOf(true));
		}
	}

	public Response sendMessage(String deviceName, String topicFullName, String messageContent) {
		/* 334 */ if (StringUtil.isBlanks(new String[] { deviceName, topicFullName, messageContent })) {
			/* 335 */ return renderErrorParameter();
		}
		/* 337 */ Device device = deviceDao.queryDeviceByDeviceName(deviceName);
		/* 338 */ if (device == null) {
			/* 339 */ return renderError("不存在的设备名称！");
		}
		/* 341 */ topicFullName = device.getProductKey() + topicFullName;
		/* 342 */ PubResponse response = com.mibo.modules.al.sma.util.TopicMsgUtil.sendMessage(device.getProductKey(),
				topicFullName, messageContent);
		/* 343 */ if (!response.getSuccess().booleanValue()) {
			/* 344 */ return renderError(response.getErrorMessage());
		}
		/* 346 */ return renderMeg();
	}

	public Response getDeviceDetails(String deviceId) {
		/* 355 */ if (StringUtil.isBlanks(new String[] { deviceId })) {
			/* 356 */ return renderErrorParameter();
		}
		/* 358 */ Map<String, Object> map = getMap();
		/* 359 */ Device device = deviceDao.queryDeviceById(Integer.valueOf(deviceId));
		/* 360 */ if (device == null) {
			/* 361 */ return renderError("设备编号不存在！");
		}
		/* 363 */ GetDeviceStatusResponse response = DeviceUtil.getDeviceStatus(device.getProductKey(),
				device.getDeviceName());
		/* 364 */ if (response.getSuccess().booleanValue()) {
			/* 365 */ if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.OFFLINE.getValue())) {
				/* 366 */ device.setDeviceState(Boolean.valueOf(false));
			}
			/* 368 */ if (response.getData().getStatus().equals(ConstUtils.DEVICE_STATUS.ONLINE.getValue())) {
				/* 369 */ device.setDeviceState(Boolean.valueOf(true));
			}
			/* 371 */ updateDeviceStatusByTime(device);
			/* 372 */ device.update();
		}

		/* 375 */ if (device.getIsLayingTime().booleanValue()) {
			/* 376 */ map.put("deviceLayingList", deviceLayingDao.queryDeviceLayingByDeviceId(device.getId()));
		}

		/* 379 */ if (device.getIsSwitch().booleanValue()) {
			/* 380 */ map.put("deviceSwitchList", deviceSwitchDao.queryDeviceSwitchByDeviceId(device.getId()));
		}
		/* 382 */ map.put("device", device);
		/* 383 */ return renderResult(map);
	}

	public Response updateDeviceAlias(String id, String alias, String type) {
		/* 394 */ if (StringUtil.isBlanks(new String[] { id, alias, type })) {
			/* 395 */ return renderErrorParameter();
		}
		/* 397 */ if ((!type.equals(ConstUtils.PRODUCT_TYPE.DEVICE.getValue())) &&
				/* 398 */ (!type.equals(ConstUtils.PRODUCT_TYPE.GATEWAY.getValue()))) {
			/* 399 */ return renderError("类型错误！");
		}
		/* 401 */ if (type.equals(ConstUtils.PRODUCT_TYPE.GATEWAY.getValue())) {
			/* 402 */ Gateway gateway = (Gateway) gatewayDao.findById(Integer.valueOf(Integer.parseInt(id)));
			/* 403 */ if (gateway == null) {
				/* 404 */ return renderError("网关未注册，请添加网关!");
			}
			/* 406 */ ((Gateway) gateway.setDeviceAlias(alias)).update();
		}
		/* 408 */ if (type.equals(ConstUtils.PRODUCT_TYPE.DEVICE.getValue())) {
			/* 409 */ Device device = deviceDao.queryDeviceById(Integer.valueOf(Integer.parseInt(id)));
			/* 410 */ if (device == null) {
				/* 411 */ return renderError("设备编号不存在！");
			}
			/* 413 */ ((Device) device.setDeviceAlias(alias)).update();
		}
		/* 415 */ return renderMeg();
	}

	public Response messageList(String userId, int pageNo) {
		/* 425 */ return renderResult(deviceMessageDao.queryPageDeviceMessage(userId, pageNo));
	}

	@Before({ Tx.class })
	public Response delete(String userId, String id, String type) {
		/* 437 */ if (StringUtil.isBlanks(new String[] { id, type })) {
			/* 438 */ return renderErrorParameter();
		}
		/* 440 */ if ((!type.equals(ConstUtils.PRODUCT_TYPE.DEVICE.getValue())) &&
				/* 441 */ (!type.equals(ConstUtils.PRODUCT_TYPE.GATEWAY.getValue()))) {
			/* 442 */ return renderError("类型错误!");
		}
		/* 444 */ if (type.equals(ConstUtils.PRODUCT_TYPE.DEVICE.getValue())) {
			/* 445 */ Device device = deviceDao.queryDeviceById(Integer.valueOf(Integer.parseInt(id)));
			/* 446 */ if (device == null) {
				/* 447 */ return renderError("编号不存在!");
			}

			/* 450 */ deviceMessageDao.deleteDeviceMessageByDeviceId(device.getId());

			/* 452 */ deviceLayingDao.deleteDeviceLayingByDeviceId(device.getId());

			/* 454 */ deviceSwitchDao.deleteDeviceSwitchByDeviceId(device.getId());

			/* 456 */ device.delete();

			/* 458 */ removeRedisDevice(device.getDeviceName());
		}
		/* 460 */ if (type.equals(ConstUtils.PRODUCT_TYPE.GATEWAY.getValue())) {
			/* 461 */ UserGateway userGateway = userGatewayDao
					.queryUserGatewayByUserIdGatewayId(Integer.valueOf(userId), Integer.valueOf(id));
			/* 462 */ if (userGateway == null) {
				/* 463 */ return renderError("编号不存在!");
			}
			/* 465 */ userGateway.delete();
		}
		/* 467 */ return renderMeg();
	}

	public Response gatewayVersion(String id, String name, String no) {
		/* 478 */ if (StringUtil.isBlanks(new String[] { name, no })) {
			/* 479 */ return renderErrorParameter();
		}
		/* 481 */ Gateway gateway = (Gateway) gatewayDao.findById(Integer.valueOf(id));
		/* 482 */ if (gateway == null) {
			/* 483 */ return renderError("编号不存在!");
		}
		/* 485 */ gateway.setVersionName(name);
		/* 486 */ gateway.setVersionNo(no);
		/* 487 */ gateway.update();
		/* 488 */ return renderMeg();
	}

	@Deprecated
	public Response searchMessage(String userId, String name, String alias, int pageNo) {
		/* 501 */ Page<DeviceMsg> page = null;
		/* 502 */ if (StringUtil.isNotBlank(name)) {
			/* 503 */ page = deviceMessageDao.searchDeviceMsgByUserIdProductName(pageNo, Integer.valueOf(userId), name);
		}
		/* 505 */ if (StringUtil.isNotBlank(alias)) {
			/* 506 */ page = deviceMessageDao.searchDeviceMsgByUserIdalias(pageNo, Integer.valueOf(userId), alias);
		}
		/* 508 */ return renderResult(page);
	}

	public Response searchMessage(String userId, String keyword, int pageNo) {
		/* 519 */ if (StringUtil.isBlanks(new String[] { keyword })) {
			/* 520 */ return renderErrorParameter();
		}
		/* 522 */ List<TagProduct> tagProductList = tagProduct.queryTagProductLikeName(keyword);
		/* 523 */ return renderResult(deviceMessageDao.searchDeviceMsgByTagProductKeyword(Integer.valueOf(userId),
				pageNo, tagProductList, keyword));
	}

	public Response delteMessage(String ids) {
		/* 532 */ if (StringUtil.isBlanks(new String[] { ids })) {
			/* 533 */ return renderErrorParameter();
		}
		/* 535 */ String[] id = ids.split(",");
		/* 536 */ if ((id == null) || (id.length == 0)) {
			/* 537 */ return renderErrorParameter();
		}
		/* 539 */ deviceMessageDao.batchDelteById(id);
		/* 540 */ return renderMeg();
	}

	@Before({ Tx.class })
	public Response delteALL(String userId, String id) {
		/* 551 */ if (StringUtil.isBlanks(new String[] { id })) {
			/* 552 */ return renderErrorParameter();
		}
		/* 554 */ List<UserGateway> userGatewayList = userGatewayDao.queryUserGatewayByGatewayId(Integer.valueOf(id));
		/* 555 */ if ((userGatewayList == null) || (userGatewayList.size() == 0)) {
			/* 556 */ return renderError("网关编号错误!");
		}

		/* 559 */ List<Device> deviceList = deviceDao.queryDevicListByGatewayId(Integer.valueOf(id));
		/* 560 */ for (Device device : deviceList) {
			/* 562 */ deviceMessageDao.deleteDeviceMessageByDeviceId(device.getId());

			/* 564 */ deviceLayingDao.deleteDeviceLayingByDeviceId(device.getId());

			/* 566 */ deviceSwitchDao.deleteDeviceSwitchByDeviceId(device.getId());

			/* 568 */ device.delete();

			/* 570 */ removeRedisDevice(device.getDeviceName());
		}

		/* 573 */ userSceneDao.deleteUserSceneAllByGatewayId(Integer.valueOf(id));
		/* 574 */ for (UserGateway userGateway : userGatewayList) {
			/* 575 */ userGateway.delete();
		}
		/* 577 */ gatewayDao.deleteById(Integer.valueOf(id));
		/* 578 */ return renderMeg();
	}

	public Response setupDevice(String id, String laying, String push) {
		/* 589 */ if (StringUtil.isBlanks(new String[] { id })) {
			/* 590 */ return renderErrorParameter();
		}
		/* 592 */ if ((StringUtil.isBlank(laying)) && (StringUtil.isBlank(push))) {
			/* 593 */ return renderErrorParameter();
		}
		/* 595 */ Device device = deviceDao.queryDeviceById(Integer.valueOf(id));
		/* 596 */ if (device == null) {
			/* 597 */ return renderError("设备编号不存在！");
		}
		/* 599 */ device.setIsLaying(Boolean.valueOf(Boolean.parseBoolean(laying)));
		/* 600 */ device.setIsPush(Boolean.valueOf(Boolean.parseBoolean(push)));
		/* 601 */ device.update();
		/* 602 */ return renderMeg();
	}

	public Response deviceLayingTime(String id, String staTime, String endTime) {
		/* 613 */ if (StringUtil.isBlanks(new String[] { id, staTime, endTime })) {
			/* 614 */ return renderErrorParameter();
		}
		/* 616 */ Device device = deviceDao.queryDeviceById(Integer.valueOf(id));
		/* 617 */ if (device == null) {
			/* 618 */ return renderError("设备编号不存在！");
		}
		/* 620 */ deviceLayingDao.addDeviceLaying(Integer.valueOf(id), staTime, endTime);
		/* 621 */ ((Device) device.setIsLayingTime(Boolean.valueOf(true))).update();
		/* 622 */ return renderMeg();
	}

	public Response deleteDeviceLaying(String layingId) {
		/* 631 */ if (StringUtil.isBlanks(new String[] { layingId })) {
			/* 632 */ return renderErrorParameter();
		}
		/* 634 */ DeviceLaying deviceLaying = (DeviceLaying) deviceLayingDao.findById(Integer.valueOf(layingId));
		/* 635 */ if (deviceLaying == null) {
			/* 636 */ return renderError("布放编号不存在！");
		}
		/* 638 */ deviceLaying.delete();
		/* 639 */ List<DeviceLaying> deviceLayingList = deviceLayingDao
				.queryDeviceLayingByDeviceId(deviceLaying.getDeviceId());
		/* 640 */ if ((deviceLayingList == null) || (deviceLayingList.size() == 0)) {
			/* 641 */ ((Device) deviceDao.queryDeviceById(deviceLaying.getDeviceId())
					.setIsLayingTime(Boolean.valueOf(false))).update();
		}
		/* 643 */ return renderMeg();
	}

	public Response clears() {
		/* 648 */ com.mibo.common.util.JedisUtil.del("RedisDevices");
		/* 649 */ return renderMeg();
	}

	public Response deleteAllMessage(String userId) {
		/* 658 */ deviceMessageDao.deleteALLMessage(Integer.valueOf(userId));
		/* 659 */ return renderMeg();
	}

	public Response getAllDevice(String userId) {
		/* 668 */ List<Device> allDeviceList = deviceDao.queryAllDeviceByUserId(Integer.valueOf(userId));
		/* 669 */ if ((allDeviceList == null) || (allDeviceList.size() == 0)) {
			/* 670 */ return renderMeg();
		}

		/* 673 */ synchroGatewayDeviceList(allDeviceList);
		/* 674 */ return renderResult(allDeviceList);
	}

	public Response batteryPush(String userId) {
		/* 683 */ deviceDao.updateBatteryPush(Integer.valueOf(userId));
		/* 684 */ return renderMeg();
	}
}

/*
 * Location:
 * C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\modules\app\
 * service\ProductService.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */