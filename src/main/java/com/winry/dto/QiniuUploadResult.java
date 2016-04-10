package com.winry.dto;

/**
 * Created by cong on 2016/4/10.
 */
public class QiniuUploadResult {

    private String hash;

    private String key;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
