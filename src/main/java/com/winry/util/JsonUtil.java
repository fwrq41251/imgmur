package com.winry.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by cong on 2016/4/10.
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T toObject(Class<T> clazz, String json) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
