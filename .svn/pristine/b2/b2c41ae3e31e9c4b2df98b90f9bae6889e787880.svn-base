##\u6839\u636e\u7528\u6237\u7f16\u53f7\u67e5\u8be2\u8bbe\u5907\u5217\u8868
#sql("queryDeviceListByUserId")
SELECT * FROM t_device WHERE user_id = ? 
#end

##\u6839\u636e\u7528\u6237\u7f16\u53f7\u67e5\u8be2\u4ea7\u54c1key\u96c6\u5408
#sql("qeruyProductKeyListByUserId")
SELECT * FROM t_device WHERE user_id = ? GROUP BY product_key;
#end

##\u6839\u636e\u4ea7\u54c1key\u548c\u7528\u6237\u7f16\u53f7\u5206\u9875\u67e5\u8be2\u8bbe\u5907
#sql("qeruyDeviceNameListByProductKeyUserId")
SELECT * FROM t_device WHERE user_id = #para(userId) AND product_key = #para(productKey)
#end

##\u6839\u636e\u8bbe\u5907\u540d\u67e5\u8be2\u8bbe\u5907\u4fe1\u606f
#sql("queryDeviceByDeviceName")
SELECT * FROM t_device WHERE device_name = ?
#end

##\u6839\u636e\u7528\u6237\u548c\u7f51\u5173\u7f16\u53f7\u67e5\u8be2\u8bbe\u5907\u5217\u8868
#sql("queryDevicListByGatewayId")
SELECT * FROM t_device WHERE gateway_id = ?
#end

##\u6839\u636e\u4ea7\u54c1\u540d\u79f0\u8bbe\u5907\u96c6\u5408
#sql("queryDeviceByUserIdProductNameList")
SELECT * FROM t_device WHERE user_id = ? AND product_name LIKE ?
#end

##\u6839\u636e\u8bbe\u5907\u522b\u540d\u67e5\u8be2\u8bbe\u5907\u96c6\u5408
#sql("queryDeviceByUserIdAliasList")
SELECT * FROM t_device WHERE user_id = ? AND device_alias = ?
#end