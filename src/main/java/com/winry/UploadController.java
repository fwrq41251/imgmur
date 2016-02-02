// package com.winry;
//
// import java.io.File;
// import java.util.UUID;
//
// import org.apache.commons.io.FileUtils;
// import org.apache.commons.lang.StringUtils;
// import org.apache.commons.lang.SystemUtils;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.multipart.MultipartFile;
//
// @Controller
// public class UploadController {
//
// @RequestMapping(value = "/upload", method = RequestMethod.GET)
// public @ResponseBody String provideUploadInfo() {
// return "You can upload a file by posting to this same URL.";
// }
//
// @RequestMapping(value = "/upload", method = RequestMethod.POST)
// public @ResponseBody String handleFileUpload(@RequestParam("file")
// MultipartFile file) {
// if (!file.isEmpty()) {
// try {
// File tempFile = new File(generateFileName(file));
// FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
// return "You successfully uploaded file!";
// } catch (Exception e) {
// return "You failed to upload file => " + e.getMessage();
// }
// } else {
// return "You failed to upload file because the file was empty.";
// }
// }
//
// private String generateFileName(MultipartFile file) {
// String path = SystemUtils.IS_OS_WINDOWS ? FileUtils.getTempDirectoryPath() :
// "usr/imgmur";
// String ext = StringUtils.substringAfterLast(file.getOriginalFilename(),
// ".").toLowerCase();
// String name = UUID.randomUUID().toString();
// return new
// StringBuilder().append(path).append(name).append(".").append(ext).toString();
// }
// }
