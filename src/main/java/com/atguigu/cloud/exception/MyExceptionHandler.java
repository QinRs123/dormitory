package com.atguigu.cloud.exception;

import com.atguigu.cloud.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result SystemException( RuntimeException e){
        e.printStackTrace();
        return Result.error("系统异常..");
    }
}
