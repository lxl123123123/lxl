package com.itheima.controller.utils;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    //拦截所有的异常信息
    @ExceptionHandler
    public R doException(Exception e){

        e.printStackTrace();
        return new R("服务器异常，请稍后再试!");

    }

}
