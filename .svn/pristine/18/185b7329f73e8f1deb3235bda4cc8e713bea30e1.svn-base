package com.mibo.modules.jiguang;

import com.jfinal.kit.LogKit;
import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送工具类
 * @author liqp
 *
 */
public class JpushUtil {

	private static final String APP_KEY = "ce27495388b2e693027fc913";
	private static final String MASTER_SECRET = "e02228bf9df52affc0849930";
	private static JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
	
	public static void main(String[] args) {
//		Platform platform = Platform.all();
//		Audience audience = Audience.all();
//		Notification notification = null;
//		notification = Notification.android("通知-内容","通知-标题", null);
//		Message message = null;
//		message = message("消息-类型", "消息-标题", "消息-内容");
//		PushPayload pushPayload = pushPayload(platform, audience, notification, message);
//		System.out.println(sendPush(pushPayload));
//		System.out.println(pushAndroidNotificationAlias("2d26590086524c55ba34f838425c8f6d",JpushUtil.notification("报警")));
		System.out.println(pushAndroidMessageAlias("2d26590086524c55ba34f838425c8f6d",JpushUtil.message("1", "报警", "报警")));
	}
	
	/**
	 * 发送推送
	 * @param pushPayload
	 * @return
	 */
	public static boolean sendPush(PushPayload pushPayload) {
		PushPayload payload = pushPayload;
		try {
			PushResult result = jpushClient.sendPush(payload);
			return result.isResultOK();
		} catch (Exception e) {
			e.printStackTrace();
			LogKit.error("极光推送失败！",e);
			return false;
		}
	}
	
	/**
	 * 根据别名推送安卓消息
	 * @param userToken	别名
	 * @param message	消息
	 * @return
	 */
	public static boolean pushAndroidMessageAlias(String userToken,Message message) {
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.alias(userToken))
				.setMessage(message)
				.build();
		try {
			PushResult result = jpushClient.sendPush(payload);
			return result.isResultOK();
		} catch (Exception e) {
			e.printStackTrace();
			LogKit.error("根据别名推送安卓消息失败！",e);
			return false;
		}
	}
	
	/**
	 * 根据别名推送安卓通知
	 * @param userId		别名
	 * @param notification	通知
	 * @return
	 */
	public static boolean pushAndroidNotificationAlias(String userToken,Notification notification) {
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.alias(userToken))
				.setNotification(notification)
				.build();
		try {
			PushResult result = jpushClient.sendPush(payload);
			return result.isResultOK();
		} catch (Exception e) {
			e.printStackTrace();
			LogKit.error("根据别名推送安卓通知失败！",e);
			return false;
		}
	}
	
	/**
	 * 自定义推送对象
	 * @param platform		推送平台
	 * @param audience		推送设备
	 * @param notification	推送通知
	 * @param message		推送消息
	 * @return
	 */
	public static PushPayload pushPayload(Platform platform,Audience audience,Notification notification,Message message) {
		return PushPayload.newBuilder()
				.setPlatform(platform)
				.setAudience(audience)
				.setNotification(notification)
				.setMessage(message)
				.build();
	}
	
	/**
	 * 自定义消息
	 * @param type
	 * @param title
	 * @param msgContent
	 * @return
	 */
	public static Message message(String type,String title,String msgContent) {
		return Message.newBuilder()
				.setContentType(type)
				.setTitle(title)
				.setMsgContent(msgContent)
				.build();
	}
	
	/**
	 * 自定义通知
	 * @return
	 */
	public static Notification notification(String title) {
		return Notification.newBuilder().setAlert(title).build();
	}
}
