package com.womenstore.hcf.config;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handle(Exception e){
        log.error(e.getMessage(),e);
        return new Result(ResultCode.ERROR,e.getMessage()==null?"未知异常":e.getMessage());
    }

}
