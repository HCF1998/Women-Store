package com.womenstore.hcf.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Result<T> {
    /**
     * 业务错误码
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

    private Result(ResultStatus resultStatus,T data){
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static Result<Void> success(){
        return new Result<Void>(ResultStatus.SUCCESS,null);
    }

    /**
     * 业务成功返回业务代码和描述信息、返回参数
     */
    public static <T> Result<T> success(T data){
        return new Result<>(ResultStatus.SUCCESS,data);
    }
}
