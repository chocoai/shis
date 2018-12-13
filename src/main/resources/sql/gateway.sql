##\u83b7\u53d6\u7528\u6237\u4e0b\u7684\u6240\u6709\u7f51\u5173
#sql("getGatewayByUserId")
SELECT * FROM t_gateway WHERE user_id = ?
#end

##\u6839\u636e\u7528\u6237\u7f16\u53f7\u83b7\u53d6\u7f51\u5173\u5217\u8868
#sql("queryGatewayListByUserId")
SELECT * FROM t_gateway WHERE user_id = ?
#end

##\u6839\u636e\u7528\u6237\u7f16\u53f7\u548c\u8bbe\u5907\u540d\u79f0\u67e5\u8be2\u7f51\u5173
#sql("queryGatewayByUserIdDeviceName")
SELECT * FROM t_gateway WHERE user_id = ? and device_name = ? LIMIT 1
#end

##\u6839\u636e\u8bbe\u5907\u540d\u79f0\u67e5\u8be2\u7f51\u5173
#sql("queryGatewayByDeviceName")
SELECT * FROM t_gateway WHERE device_name = ? LIMIT 1
#end
