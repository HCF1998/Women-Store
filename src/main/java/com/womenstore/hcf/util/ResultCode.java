package com.womenstore.hcf.util;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter

/**
 * 统一返回体枚举类
 */
public enum ResultCode {
    SUCCESS(200,"操作成功"),
    FAILED(404, "响应失败"),
    BAD_REQUEST(405,"错误请求"),
    ERROR(500, "内部错误"),;

    /**
     * 业务状态码
     */
    public Integer code;

    /**
     * 业务信息描述
     */
    public String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
