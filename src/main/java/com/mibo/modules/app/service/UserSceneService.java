package com.mibo.modules.app.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.iot.model.v20170420.PubResponse;
import com.google.gson.Gson;
import com.jfinal.kit.Base64Kit;
import com.jfinal.kit.LogKit;
import com.mibo.common.result.Response;
import com.mibo.common.util.StringUtil;
import com.mibo.modules.al.sma.util.ControlBean;
import com.mibo.modules.al.sma.util.DeviceSendSceneDataBean;
import com.mibo.modules.al.sma.util.SceneData;
import com.mibo.modules.al.sma.util.TopicMsgUtil;
import com.mibo.modules.data.model.Device;
import com.mibo.modules.data.model.Gateway;
import com.mibo.modules.data.model.UserScene;
import java.util.ArrayList;
import java.util.List;

public class UserSceneService extends com.mibo.common.base.BaseService {
	public static final UserSceneService userSceneService = new UserSceneService();
	private static final Gateway gatewayDao = Gateway.dao;
	private static final Device deviceDao = Device.dao;
	private static final UserScene userSceneDao = UserScene.dao;

	public Response add(String gatewayId, String type, String name, String data, String isHome) {
		if (StringUtil.isBlanks(new String[] { gatewayId, type, name, data, isHome })) {
			return renderErrorParameter();
		}
		Gateway gateway = (Gateway) gatewayDao.findById(gatewayId);
		if (gateway == null) {
			return renderError("网关未注册，请添加网关!");
		}
		userSceneDao.add(gatewayId, type, name, data, isHome);
		return renderMeg();
	}

	public Response update(String userId, String sceneId, String gatewayId, String type, String name, String data,
			String isHome) {
		if (StringUtil.isBlanks(new String[] { sceneId })) {
			return renderErrorParameter();
		}
		boolean state = false;
		UserScene scene = (UserScene) userSceneDao.findById(sceneId);
		if (scene == null) {
			return renderError("场景编号错误！");
		}
		if (StringUtil.isNotBlank(gatewayId)) {
			state = true;
			scene.setGatewayId(Integer.valueOf(Integer.parseInt(gatewayId)));
		}
		if (StringUtil.isNotBlank(type)) {
			state = true;
			scene.setSceneType(Integer.valueOf(Integer.parseInt(type)));
		}
		if (StringUtil.isNotBlank(name)) {
			state = true;
			scene.setSceneName(name);
		}
		if (StringUtil.isNotBlank(data)) {
			state = true;
			scene.setSceneData(data);
		}
		if (StringUtil.isNotBlank(isHome)) {
			state = true;
			if (isHome.equals("1")) {
				scene.setIsHome(Boolean.valueOf(true));
			} else {
				scene.setIsHome(Boolean.valueOf(false));
			}
		}
		if (state) {
			scene.update();
		}
		return renderResult(scene);
	}

	public Response delete(String userId, String sceneId) {
		if (StringUtil.isBlanks(new String[] { sceneId })) {
			return renderErrorParameter();
		}
		UserScene scene = (UserScene) userSceneDao.findById(Integer.valueOf(Integer.parseInt(sceneId)));
		if (scene == null) {
			return renderError("场景编号错误！");
		}
		scene.delete();
		return renderMeg();
	}

	public Response getList(String gatewayId) {
		if (StringUtil.isBlanks(new String[] { gatewayId })) {
			return renderErrorParameter();
		}
		Gateway gateway = gatewayDao.findById(gatewayId);
		if (gateway == null) {
			return renderError("网关未注册，请添加网关!");
		}
		return renderResult(userSceneDao.queryUserSceneListByGatewayId(Integer.valueOf(gatewayId)));
	}

	public Response home(String userId) {
		return renderResult(userSceneDao.queryUserSceneByUserIdHome(userId));
	}

	public Response send(String userId, String deviceName, String sceneID) {
		if (StringUtil.isBlanks(new String[] { deviceName, sceneID })) {
			return renderErrorParameter();
		}

		//获取用户场景集合
		List<UserScene> userSceneList = userSceneDao.queryUserSceneByUserId(Integer.valueOf(userId));
		for (UserScene userScene : userSceneList) {
			SceneData sd = (SceneData) JSON.parseObject(Base64Kit.decodeToStr(userScene.getSceneData()),
					SceneData.class);
			List<SceneData.DataBean> dbs = sd.getData();
			for (SceneData.DataBean db : dbs) {
				if (deviceName.equals(db.getDevice_name())) {
					if (db.isSmart()) {

						if (Integer.parseInt(sceneID) == db.getControlId()) {
							String topicFullName = new String();
							String productKey = new String();

							for (SceneData.DataBean tem : dbs) {
								Device device = deviceDao.queryDeviceByDeviceName(tem.getDevice_name());
								if (device == null) {
									return renderError("不存在的设备名称！");
								}
								device.setIsLaying(Boolean.valueOf(tem.isShield()));
								if (tem.isShield()) {
									device.setIsPush(Boolean.valueOf(tem.isShield()));
								}
								device.update();
								if (!StringUtil.isBlanks(new String[] { tem.getControlData1() })) {

									topicFullName = device.getProductKey() + tem.getTopic1();
									productKey = device.getProductKey();
								}
							}

							if (!StringUtil.isBlanks(new String[] { topicFullName, productKey })) {

								//执行场景
								PubResponse response = com.mibo.modules.al.sma.util.TopicMsgUtil.sendMessage(productKey,
										topicFullName, setSendSceneData(sd));
								if (!response.getSuccess().booleanValue()) {
									return renderError(response.getErrorMessage());
								}
								return renderMeg();
							}

						}
					}
				}
			}
		}
		return renderMeg();
	}

	/**
	 * 执行场景中的相关设备操作
	 * @param sd
	 * @return
	 */
	public static String setSendSceneData(SceneData sd) {
		String strJson = "";
		List<DeviceSendSceneDataBean.MultiparamsBean> multiparamsBeanList = new ArrayList<DeviceSendSceneDataBean.MultiparamsBean>();
		for (SceneData.DataBean data : sd.getData()) {

//			Device device = deviceDao.queryDeviceByDeviceName(data.getDevice_name());
//
//			//确认设备是否在线
//			if(null!=device&TopicMsgUtil.GetDeviceStatus(device.getProductKey(),device.getDeviceName())) {

				//场景面板和感应器不执行操作
				if (data.getControlData1() != null && !data.getProduct_name().contains("HOSCZB")
						&& !data.getProduct_name().contains("HODSZB") && !data.getProduct_name().contains("HOGSZB")
						&& !data.getProduct_name().contains("HOMSZB") && !data.getProduct_name().contains("HOSAZB")) {

					DeviceSendSceneDataBean.MultiparamsBean multiparamsBean = new DeviceSendSceneDataBean.MultiparamsBean();
					String str = new Gson().toJson(data.getControlData1()).replace("\\", "");
					str = str.substring(1, str.length() - 1);

					ControlBean controlBean = (ControlBean) new Gson().fromJson(str, ControlBean.class);
					multiparamsBean.setIndex(controlBean.getParams().getIndex());
					multiparamsBean.setOnOff(controlBean.getParams().isOnOff());
					multiparamsBean.setName(data.getDevice_name());
					multiparamsBeanList.add(multiparamsBean);

					if (data.getProduct_name().contains("HODRZB")) {
						if (data.getIsControl1()) {
							strJson = strJson + "{\"Name\": \"" + data.getDevice_name() + "\",\"Index\": "
									+ controlBean.getParams().getIndex() + ",\"State\": " + 1 + "},";
						} else {
							strJson = strJson + "{\"Name\": \"" + data.getDevice_name() + "\",\"Index\": "
									+ controlBean.getParams().getIndex() + ",\"State\": " + 0 + "},";
						}
					} else if (data.getProduct_name().contains("HOSWZB11")) {
						strJson = strJson + "{\"Name\": \"" + data.getDevice_name() + "\",\"Index\": "
								+ controlBean.getParams().getIndex() + ",\"CurrentLevel\": " + controlBean.getParams().getCurrentLevel()
								+ ",\"OnLevel\": " + controlBean.getParams().getOnLevel()
								+ "},";
					} else {
						strJson = strJson + "{\"Name\": \"" + data.getDevice_name() + "\",\"Index\": "
								+ controlBean.getParams().getIndex() + ",\"OnOff\": " + controlBean.getParams().isOnOff()
								+ "},";
					}
				}
//			}
		}

		String overStrJson = "{\"id\": \"123\",\"version\": \"1.0\",\"multiparams\": ["
				+ strJson.substring(0, strJson.length() - 1) + "],\"method\": \"set\"}";
		LogKit.warn("场景执行*********************************************************："+overStrJson);
		DeviceSendSceneDataBean deviceSendSceneDataBean = new DeviceSendSceneDataBean();
		deviceSendSceneDataBean.setId(StringUtil.getCount());
		deviceSendSceneDataBean.setMethod("set");
		deviceSendSceneDataBean.setVersion("1.0");
		deviceSendSceneDataBean.setMultiparams(multiparamsBeanList);

		String msg = Base64Kit.encode(overStrJson);
		return msg;
	}
}
