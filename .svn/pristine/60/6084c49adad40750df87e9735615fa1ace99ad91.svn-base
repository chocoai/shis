package com.mibo.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public final class FileUtil {
	public static String getExtension(String fileName) {
		int i = fileName.lastIndexOf(".");
		if (i < 0)
			return null;
		return fileName.substring(i + 1).toLowerCase();
	}

	public static String getExtension(File file) {
		if (file == null)
			return null;
		if (file.isDirectory())
			return null;
		String fileName = file.getName();
		return getExtension(fileName);
	}

	public static File readFile(String filePath) {
		File file = new File(filePath);
		if (file.isDirectory()) {
			return null;
		}
		if (!file.exists()) {
			return null;
		}
		return file;
	}

	public static boolean copyFile(String oldFilePath, String newFilePath) {
		try {
			int byteRead = 0;
			File oldFile = new File(oldFilePath);
			if (oldFile.exists()) {
				InputStream inStream = new FileInputStream(oldFilePath);
				FileOutputStream fs = new FileOutputStream(newFilePath);
				byte[] buffer = new byte['֤'];
				while ((byteRead = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteRead);
				}
				inStream.close();
				fs.close();
				return true;
			}
			oldFile.setReadOnly();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean delFile(String filePath) {
		return delFile(new File(filePath));
	}

	public static boolean delFile(File file) {
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	public static boolean isImage(File imgFile) {
		try {
			BufferedImage image = ImageIO.read(imgFile);
			return image != null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isVideo(File file) {
		String reg = "(mp4|flv|avi|rm|rmvb|wmv|ram|mov/asf|mpg)";
		Pattern p = Pattern.compile(reg);
		return p.matcher(file.getName()).find();
	}

	public static String getTimeFilePath() {
		return new SimpleDateFormat("/yyyy/MM/dd").format(new Date()) + "/";
	}
}