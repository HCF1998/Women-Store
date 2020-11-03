package com.womenstore.hcf.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息描述
     */
    private String message;
    /**
     * 返回参数
     */
    private T data;

    public Result(T data) {
        this(ResultCode.SUCCESS,data);
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public Result(int code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }


}
