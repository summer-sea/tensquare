package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-05-21 12:52
 * 通用异常处理类，将异常转换成前端需要的异常形式
 * 自定义异常处理类
 **/

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());

    }
}
