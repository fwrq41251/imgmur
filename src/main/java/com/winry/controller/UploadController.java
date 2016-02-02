package com.winry.controller;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winry.dto.ResultDto;
import com.winry.util.DateTimeUtil;
import com.winry.util.ResultDtoFactory;

@Controller
public class UploadController {

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody ResultDto handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		String name = Base64.getEncoder().encodeToString(DateTimeUtil.getNowTime().getBytes("utf-8"));
		String ext = StringUtils.substringAfterLast(file.getOriginalFilename(), ".").toLowerCase();
		String fullName = name + "." + ext;
		File tempFile = new File(generateFileName(fullName));
		FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
		UploadResult data = getUploadResult(fullName);
		return ResultDtoFactory.toSuccess(data);
	}

	private UploadResult getUploadResult(String name) {
		UploadResult result = new UploadResult(name);
		result.setUrl(result.fullName);
		result.setHtmlCode(
				MessageFormat.format("<img src=\"{0}\" alt=\"{1}\" title=\"{1}\" />", result.fullName, result.name));
		result.setBBCode(MessageFormat.format("[img]{0}[/img]", result.fullName));
		result.setMarkdown(MessageFormat.format("![{0}]({1})", result.name, result.fullName));
		return result;
	}

	private String generateFileName(String name) {
		String path = SystemUtils.IS_OS_WINDOWS ? FileUtils.getTempDirectoryPath() : "usr/imgmur";
		return new StringBuilder().append(path).append(name).toString();
	}

	public class UploadResult {

		final String fullName;
		final String name;

		private String url;

		private String htmlCode;

		private String BBCode;

		private String markdown;

		public UploadResult(String name) {
			super();
			this.name = name;
			this.fullName = servletContext.getContextPath() + name;
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

	}
}
