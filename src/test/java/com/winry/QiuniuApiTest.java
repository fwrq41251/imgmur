package com.winry;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by cong on 2016/4/10.
 */
public class QiuniuApiTest {

    private final String accessKey = "rsvWN8dYYqOQRXctgCRVrSwECNKMTPlFV_-CBpQR";

    private final String secretKey = "5loYzD_08uLGjWZ-TpDKYKIYpGz7PerLL38ToVoY";

    private final String bucketName = "public";

    @Test
    public void upload() throws IOException{
        UploadManager uploadManager = new UploadManager();
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        Response response = uploadManager.put(new File("README.md"), null, token);
        String result = response.bodyString();
        System.out.println(result);
    }

}
