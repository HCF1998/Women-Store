package com.womenstore.hcf.util;

import java.util.UUID;

/**
 * 生成uuid工具类
 */
public class UuidCode {

    /**
     * 生成用户登录uuid
     * @return
     */
    public static String generateLoginUuid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

    /**
     * 生成订单号：用户下单时间戳+uuid
     * @return
     */
    public static String generateOrderUuid(){
        String orderUuid =UUID.randomUUID().toString().replaceAll("-","")+System.currentTimeMillis();
        return orderUuid;
    }
    public static void main(String[] args) {
        String s = UuidCode.generateOrderUuid();
        System.out.println(s);
    }
}
