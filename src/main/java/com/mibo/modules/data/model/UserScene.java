package com.mibo.modules.data.model;

import com.jfinal.plugin.activerecord.Db;
import com.mibo.modules.data.base.BaseUserScene;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class UserScene extends BaseUserScene<UserScene> {
	/* 14 */ public static final UserScene dao = (UserScene) new UserScene().dao();

	public UserScene add(String gatewayId, String type, String name, String data, String isHome) {
		/* 17 */ UserScene scene = new UserScene();
		/* 18 */ scene.setGatewayId(Integer.valueOf(gatewayId));
		/* 19 */ scene.setSceneType(Integer.valueOf(type));
		/* 20 */ scene.setSceneName(name);
		/* 21 */ scene.setSceneData(data);
		/* 22 */ if (isHome.equals("1")) {
			/* 23 */ scene.setIsHome(Boolean.valueOf(true));
		} else {
			/* 25 */ scene.setIsHome(Boolean.valueOf(false));
		}
		/* 27 */ scene.setAddTime(new Date());
		/* 28 */ scene.save();
		/* 29 */ return scene;
	}

	public List<UserScene> queryUserSceneListByGatewayId(Integer gatewayId) {
		/* 38 */ return find(getSql("userScene.queryUserSceneListByGatewayId"), new Object[] { gatewayId });
	}

	public List<UserScene> queryUserSceneByUserIdHome(String userId) {
		/* 47 */ return find(getSql("userScene.queryUserSceneByUserIdHome"),
				new Object[] { Integer.valueOf(Integer.parseInt(userId)) });
	}

	public List<UserScene> queryUserSceneByUserId(Integer userId) {
		/* 56 */ String sql = "SELECT * FROM t_user_scene WHERE gateway_id IN (SELECT gateway_id FROM t_user_gateway WHERE user_id = ?)";
		/* 57 */ return find(sql, new Object[] { userId });
	}

	public void deleteUserSceneAllByGatewayId(Integer gatewayId) {
		/* 65 */ String sql = "DELETE FROM t_user_scene WHERE gateway_id = ?";
		/* 66 */ Db.delete(sql, new Object[] { gatewayId });
	}
}