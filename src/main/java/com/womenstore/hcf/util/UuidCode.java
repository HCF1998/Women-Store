package com.womenstore.hcf.util;

import java.util.UUID;

/**
 * 生成uuid工具类
 */
public class UuidCode {

    public static String generateUuid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }
}
