package com.winry.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winry.dto.ResultDto;
import com.winry.util.DateTimeUtil;
import com.winry.util.ResultDtoFactory;
import com.winry.util.WebUtil;

@Controller
public class UploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody ResultDto handleFileUpload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws IOException {
		String baseUrl = WebUtil.getBaseUrl(request);
		String name = generateFileName();
		String ext = getExtensionName(file);
		String fullName = name + "." + ext;
		String path = DateTimeUtil.getDatePath();
		File tempFile = newTempFile(path, fullName);
		FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
		UploadResult data = new UploadResult(fullName, baseUrl, path).init();
		return ResultDtoFactory.toSuccess(data);
	}

	private File newTempFile(String path, String fullName) {
		String fullPath = SystemUtils.IS_OS_WINDOWS ? FileUtils.getTempDirectoryPath() : "/var/www/html/" + path;
		return new File(fullPath + fullName);
	}

	private String generateFileName() throws UnsupportedEncodingException {
		return Base64.getEncoder().encodeToString(DateTimeUtil.getNowTime().getBytes("utf-8"));
	}

	private String getExtensionName(MultipartFile file) {
		return StringUtils.substringAfterLast(file.getOriginalFilename(), ".").toLowerCase();
	}

	public static class UploadResult {

		final String fullName;

		final String name;

		private String url;

		private String htmlCode;

		private String BBCode;

		private String markdown;

		public UploadResult(String name, String baseUrl, String path) {
			super();
			this.name = name;
			this.fullName = baseUrl + path + name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHtmlCode() {
			return htmlCode;
		}

		public void setHtmlCode(String htmlCode) {
			this.htmlCode = htmlCode;
		}

		public String getBBCode() {
			return BBCode;
		}

		public void setBBCode(String bBCode) {
			BBCode = bBCode;
		}

		public String getMarkdown() {
			return markdown;
		}

		public void setMarkdown(String markdown) {
			this.markdown = markdown;
		}

		UploadResult init() {
			this.setUrl(fullName);
			this.setHtmlCode(MessageFormat.format("<img src=\"{0}\" alt=\"{1}\" title=\"{1}\" />", fullName, name));
			this.setBBCode(MessageFormat.format("[img]{0}[/img]", fullName));
			this.setMarkdown(MessageFormat.format("![{0}]({1})", name, fullName));
			return this;
		}
	}

}
