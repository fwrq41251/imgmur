package com.winry.service;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.winry.dto.QiniuUploadResult;
import com.winry.dto.UploadResult;
import com.winry.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by cong on 2016/4/10.
 */
@Service("qiniuUploadService")
public class QiniuUploadService implements UploadService {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucketName}")
    private String bucketName;

    @Value("${qiniu.urlPrefix}")
    private String urlPrefix;

    @Override
    public UploadResult upload(MultipartFile file) {
        UploadManager uploadManager = new UploadManager();
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        try {
            Response response = uploadManager.put(file.getBytes(), null, token);
            QiniuUploadResult result = JsonUtil.toObject(QiniuUploadResult.class, response.bodyString());
            String name = result.getKey();
            return UploadResult.build(name, urlPrefix + name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
