package com.mibo.modules.app.service;

import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;
import com.mibo.common.base.BaseService;
import com.mibo.common.constant.Global;
import com.mibo.common.result.Response;
import com.mibo.common.util.FileUtil;
import com.mibo.common.util.ImageUtil;
import com.mibo.common.util.SavaFile;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class FileService extends BaseService {
	/* 20 */ public static FileService fs = new FileService();

	public Response upload(List<UploadFile> files) {
		/* 28 */ if (files.isEmpty()) {
			/* 29 */ return renderError("上传文件不能为空");
		}
		/* 31 */ List<SavaFile> savaedFiles = new ArrayList<SavaFile>();
		/* 32 */ List<SavaFile> failedFiles = new ArrayList<SavaFile>();
		/* 33 */ String uploadPath = PropKit.get("uploadPath");
		/* 34 */ for (UploadFile uploadFile : files) {
			/* 35 */ String saveFilePath = uploadPath;
			/* 36 */ String urlPath = "";
			/* 37 */ File file = uploadFile.getFile();
			/* 38 */ long fileSize = file.length() / 1024L;
			/* 39 */ if (FileUtil.isImage(file)) {
				/* 40 */ if (fileSize > 12288L) {
					/* 41 */ return renderError("上传不能超过12M!");
				}
				/* 43 */ urlPath = "/images";
				/* 44 */ } else if (FileUtil.isVideo(file)) {
				/* 45 */ urlPath = "/video";
			} else {
				/* 47 */ urlPath = "/otherPath";
			}
			/* 49 */ urlPath = urlPath + FileUtil.getTimeFilePath();
			/* 50 */ saveFilePath = saveFilePath + urlPath;
			/* 51 */ File saveFileDir = new File(saveFilePath);
			/* 52 */ if (!saveFileDir.exists()) {
				/* 53 */ saveFileDir.mkdirs();
			}
			/* 55 */ String fileName = System.currentTimeMillis() + ".";
			/* 56 */ if (FileUtil.isImage(file)) {
				/* 57 */ fileName = fileName + "jpg";
			} else {
				/* 59 */ fileName = fileName + FileUtil.getExtension(file.getName());
			}

			/* 62 */ saveFilePath = saveFilePath + fileName;

			/* 64 */ if (FileUtil.copyFile(file.getAbsolutePath(), saveFilePath)) {
				/* 65 */ if (FileUtil.isImage(file)) {
					/* 66 */ ImageUtil.compress(saveFilePath, saveFilePath);
				}

				/* 69 */ file.delete();
				/* 70 */ urlPath = urlPath + fileName;
			} else {
				/* 72 */ urlPath = null;
			}
			/* 74 */ if (StringUtils.isEmpty(urlPath)) {
				/* 76 */ failedFiles.add(new SavaFile(uploadFile.getParameterName()));
			} else {
				/* 78 */ savaedFiles.add(new SavaFile(uploadFile.getParameterName(), Global.LOADURL + urlPath));
			}
		}
		/* 81 */ Map<String, Object> urls = new HashMap<String, Object>();
		/* 82 */ urls.put("successFiles", savaedFiles);
		/* 83 */ urls.put("failedFiles", failedFiles);
		/* 84 */ return renderResult(urls);
	}

	public Response uploadImg(List<UploadFile> files) {
		/* 93 */ if (files.isEmpty()) {
			/* 94 */ return renderError("上传图片不能为空");
		}
		/* 96 */ List<SavaFile> savaedFiles = new ArrayList<SavaFile>();
		/* 97 */ List<SavaFile> failedFiles = new ArrayList<SavaFile>();
		/* 98 */ String uploadPath = PropKit.get("uploadPath");
		/* 99 */ for (UploadFile uploadFile : files) {
			/* 100 */ String saveFilePath = uploadPath;
			/* 101 */ String urlPath = "";
			/* 102 */ File file = uploadFile.getFile();
			/* 103 */ long fileSize = file.length() / 1024L;
			/* 104 */ if (FileUtil.isImage(file)) {
				/* 105 */ if (fileSize > 12288L) {
					/* 106 */ return renderError("上传不能超过12M!");
				}
				/* 108 */ urlPath = "/images";
				/* 109 */ urlPath = urlPath + FileUtil.getTimeFilePath();
				/* 110 */ saveFilePath = saveFilePath + urlPath;
				/* 111 */ File saveFileDir = new File(saveFilePath);
				/* 112 */ if (!saveFileDir.exists()) {
					/* 113 */ saveFileDir.mkdirs();
				}
				/* 115 */ String fileName = Long.toString(System.currentTimeMillis());
				/* 116 */ saveFilePath = saveFilePath + fileName;

				/* 118 */ if (FileUtil.copyFile(file.getAbsolutePath(), saveFilePath + ".jpg")) {
					/* 119 */ ImageUtil.compress(saveFilePath + ".jpg", saveFilePath + ".jpg");
					/* 120 */ ImageUtil.enlarge(0.5F, saveFilePath + ".jpg", saveFilePath + "_slt.jpg");

					/* 122 */ file.delete();
					/* 123 */ urlPath = urlPath + fileName;
				} else {
					/* 125 */ urlPath = null;
				}
				/* 127 */ if (StringUtils.isEmpty(urlPath)) {
					/* 129 */ failedFiles.add(new SavaFile(uploadFile.getParameterName()));
				} else {
					/* 131 */ savaedFiles.add(new SavaFile(uploadFile.getParameterName(),
							Global.LOADURL + urlPath + ".jpg", Global.LOADURL + urlPath + "_slt.jpg"));
				}
			}
		}
		/* 135 */ Map<String, Object> urls = new HashMap<String, Object>();
		/* 136 */ urls.put("successFiles", savaedFiles);
		/* 137 */ urls.put("failedFiles", failedFiles);
		/* 138 */ return renderResult(urls);
	}
}