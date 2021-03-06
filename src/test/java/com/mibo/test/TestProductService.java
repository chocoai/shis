package com.mibo.test;

import org.junit.Test;
import com.mibo.base.BaseTest;
import com.mibo.modules.app.service.ProductService;
import com.mibo.modules.app.service.UserSceneService;

public class TestProductService extends BaseTest{

	private static final ProductService productService = ProductService.productService;
	private static final UserSceneService userSceneService = UserSceneService.userSceneService;
	
	@Test
	public void test() {
//		testMessageList();
//		testSearchMessage();
//		testSetupDevice();
//		
//		testDeviceDetails();
		
//		testDelteALL();
		
//		testSend();
		
//		testRegisterDevice();
		
	}
	
	public void testRegisterDevice() {
		for (int i = 0; i < 500; i++) {
			productService.registerDevice("1", "13", "HOGSZB0100010000", "04B8C70D004B1200");
		}
	}
	
	public void testDelteALL() {
		print(productService.delteALL("1","15"));
	}
	
	public void testMessageList() {
		print(productService.messageList("1", 1));
	}

	/**
	 * 测试发送消息
	 */
	@Test
	public void testSearchMessage() {
//		print(productService.searchMessage("5", null,"门磁", 1));
//		print(productService.searchMessage("5", "safa",null, 1));
		print(productService.searchMessage("8", "1",1));
	}
	
	public void testDeviceDetails() {
		print(productService.getDeviceDetails("3"));
	}
	
	public void testSetupDevice() {
		print(productService.setupDevice("3", "true", "false"));
	}
	
	public void testSend() {
		print(userSceneService.send("2", "21099417004B1200", "2"));
	}
}
