package com.mibo.modules.data.model;

import com.mibo.modules.data.base.BaseTagProduct;
import java.util.List;

@SuppressWarnings("serial")
public class TagProduct extends BaseTagProduct<TagProduct> {
	/* 12 */ public static final TagProduct dao = (TagProduct) new TagProduct().dao();

	public List<TagProduct> queryTagProductLikeName(String keyword) {
		/* 21 */ String sql = "SELECT * FROM t_tag_product WHERE product_name LIKE '%" + keyword + "%'";
		/* 22 */ return find(sql);
	}
}