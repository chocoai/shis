##\u6839\u636e\u7528\u6237\u548c\u7f51\u5173\u7f16\u53f7\u67e5\u8be2\u573a\u666f\u96c6\u5408
#sql("queryUserSceneListByGatewayId")
SELECT * FROM t_user_scene WHERE gateway_id = ?
#end

##\u6839\u636e\u7528\u6237\u7f16\u53f7\u548c\u9996\u9875\u663e\u793a\u67e5\u8be2\u573a\u666f\u96c6\u5408
#sql("queryUserSceneByUserIdHome")
SELECT * FROM t_user_scene WHERE is_home = 1 AND gateway_id IN (SELECT gateway_id FROM t_user_gateway WHERE user_id = ?) 
#end