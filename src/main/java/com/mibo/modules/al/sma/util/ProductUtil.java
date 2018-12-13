package com.mibo.modules.al.sma.util;

import java.util.ArrayList;
import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20180120.QueryProductListRequest;
import com.aliyuncs.iot.model.v20180120.QueryProductListResponse;
import com.aliyuncs.iot.model.v20180120.QueryProductListResponse.Data.ProductInfo;
import com.jfinal.kit.LogKit;
import com.mibo.modules.al.sma.conf.SmaConfig;

public class ProductUtil {

	private static final DefaultAcsClient CLIENT = SmaConfig.getClient();

	/**
	 * 根据页码查询产品列表
	 * 
	 * @param pageNo
	 * @return
	 */
	public static QueryProductListResponse queryProductList(int pageNo) {
		QueryProductListRequest request = new QueryProductListRequest();
		request.setActionName("QueryProductList");
		request.setCurrentPage(pageNo);
		request.setPageSize(200);
		QueryProductListResponse response = null;
		try {
			response = CLIENT.getAcsResponse(request);
		} catch (Exception e) {
			LogKit.error("根据页码查询产品列表失败！", e);
		}
		return response;
	}

	/**
	 * 根据产品名称查询产品key
	 * 
	 * @param productModel
	 * @return
	 */
	public static String queryProductKeyByProductModel(String productModel) {
		String productKey = "";
		List<ProductInfo> productInfoList = queryProductList(1).getData().getList();
		for (ProductInfo productInfo : productInfoList) {
			if (productInfo.getProductName().equals(productModel)) {
				productKey = productInfo.getProductKey();
			}
		}
		return productKey;
	}

	public static List<String> getProductKeyList() {
		List<String> strs = new ArrayList<String>();
		List<QueryProductListResponse.Data.ProductInfo> prodList = getAllProductInfoList();
		for (QueryProductListResponse.Data.ProductInfo pro : prodList) {
			strs.add(pro.getProductKey());
		}
		return strs;
	}

	private static List<QueryProductListResponse.Data.ProductInfo> getAllProductInfoList() {
		List<QueryProductListResponse.Data.ProductInfo> listProd = new ArrayList<QueryProductListResponse.Data.ProductInfo>();
		QueryProductListResponse response = queryProductList(1);
		if (!response.getSuccess().booleanValue()) {
			LogKit.error("定时任务获取产品列表失败!");
			return null;
		}
		listProd = response.getData().getList();
		int pageCount = response.getData().getPageCount().intValue();
		for (int i = 2; i <= pageCount; i++) {
			response = queryProductList(i);
			if (!response.getSuccess().booleanValue()) {
				LogKit.error("定时任务获取产品列表失败!");
			}
			for (QueryProductListResponse.Data.ProductInfo prod : response.getData().getList()) {
				listProd.add(prod);
			}
		}
		return listProd;
	}
}
