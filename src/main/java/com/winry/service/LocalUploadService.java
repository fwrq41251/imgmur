package com.winry.service;

import com.winry.dto.UploadResult;
import com.winry.util.DateTimeUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by cong on 2016/3/29.
 */
@Service("localUploadService")
public class LocalUploadService implements UploadService {

    @Value("${upload.folder}")
    private String uploadFolder;

    @Value("${domain}")
    private String domain;

    @Override
    public UploadResult upload(MultipartFile file) {
        String baseUrl = domain + uploadFolder;
        String name = generateFileName();
        String ext = getExtensionName(file);
        String fullName = name + "." + ext;
        String path = DateTimeUtil.getDatePath();
        File tempFile = newTempFile(path, fullName);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
        } catch (IOException e) {
            throw new RuntimeException("failed to copy inputStream to file", e);
        }
        return UploadResult.build(name, baseUrl + path + name);
    }

    private File newTempFile(String path, String fullName) {
        String fullPath = uploadFolder + path;
        return new File(fullPath + fullName);
    }

    private String generateFileName() {
        return Base64.getEncoder().encodeToString(DateTimeUtil.getNowTime().getBytes());
    }

    private String getExtensionName(MultipartFile file) {
        return StringUtils.substringAfterLast(file.getOriginalFilename(), ".").toLowerCase();
    }
}
