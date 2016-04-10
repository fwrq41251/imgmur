package com.winry.controller;

import com.winry.dto.ResultDto;
import com.winry.dto.UploadResult;
import com.winry.service.UploadService;
import com.winry.util.ResultDtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Controller
public class UploadController {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${qiniu.enabled}")
    private boolean localUpload;

    private UploadService uploadService;

    @PostConstruct
    public void initUploadService() {
        String name = localUpload ? "qiniuUploadService" : "localUploadService";
        uploadService = applicationContext.getBean(name, UploadService.class);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    ResultDto handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        UploadResult data = uploadService.upload(file);
        return ResultDtoFactory.toSuccess(data);
    }

}
