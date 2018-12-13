package com.mibo.common.util;

import java.io.IOException;

public class ImageUtil {
	public static void main(String[] args) {
		enlarge(0.5F, "D:\\Bases\\test\\img\\7.jpg", "D:\\\\Bases\\\\test\\\\img\\\\7-1.jpg");
	}

	public static boolean compress(String fromPic, String toPic) {
		try {
			net.coobird.thumbnailator.Thumbnails.of(new String[] { fromPic }).scale(1.0D).outputQuality(0.56F)
					.toFile(toPic);
			return true;
		} catch (IOException e) {
			com.jfinal.kit.LogKit.error("=========》图片压缩异常！" + e);
			e.printStackTrace();
		}
		return false;
	}

	public static boolean tailor(int high, int wide, String fromPic, String toPic) {
		try {
			net.coobird.thumbnailator.Thumbnails.of(new String[] { fromPic }).size(high, wide).toFile(toPic);
			return true;
		} catch (IOException e) {
			com.jfinal.kit.LogKit.error("=========》图片缩放异常！" + e);
			e.printStackTrace();
		}
		return false;
	}

	public static boolean enlarge(float ratio, String fromPic, String toPic) {
		try {
			net.coobird.thumbnailator.Thumbnails.of(new String[] { fromPic }).scale(ratio).toFile(toPic);
			return true;
		} catch (IOException e) {
			com.jfinal.kit.LogKit.error("=========》图片放大异常！" + e);
			e.printStackTrace();
		}
		return false;
	}
}