package com.winry.service;

import com.winry.dto.UploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by cong on 2016/3/29.
 */
public interface UploadService {

    UploadResult upload(MultipartFile file);
}
