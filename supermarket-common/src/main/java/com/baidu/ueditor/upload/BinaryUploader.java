package com.baidu.ueditor.upload;

import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.util.WebUtils;

public class BinaryUploader {

	private static Logger logger = LoggerFactory.getLogger(BinaryUploader.class);

	public static final State save(HttpServletRequest request, Map<String, Object> conf) {
		MultipartFile file = null;
		boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload();

		if (isAjaxUpload) {
			upload.setHeaderEncoding("UTF-8");
		}

		// 先实例化一个文件解析器
		CommonsMultipartResolver coMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		file = multiRequest.getFile("upfile");

		if (file == null) {
			return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
		}

		String savePath = (String) conf.get("savePath");
		logger.info("路径"+savePath);
		String originFileName = file.getOriginalFilename();
		logger.info("文件名"+originFileName);
		String suffix = FileType.getSuffixByFilename(originFileName);

		originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
		savePath = savePath + suffix;
		logger.info("路径"+savePath);
		logger.info("路径"+originFileName);
		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
			return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
		}
		try {
		savePath = PathFormat.parse(savePath, originFileName);
		logger.info("路径"+savePath);
		// modified by Ternence
		String rootPath = ConfigManager.getRootPath(request, conf);
		String physicalPath = rootPath + savePath;
		logger.info("路径"+physicalPath);
		State storageState = StorageManager.saveFile(file, physicalPath);

		if (storageState.isSuccess()) {
			storageState.putInfo("url", "/"+PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", originFileName + suffix);
		}
		return storageState;
		
		}catch(Exception e) {
			return new BaseState(false, AppInfo.IO_ERROR);
		}
		
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
