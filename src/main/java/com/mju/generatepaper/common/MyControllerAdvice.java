package com.mju.generatepaper.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 **/
@ControllerAdvice
public class MyControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyControllerAdvice.class);
    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<Object> errorHandler(Exception ex) {
        ex.printStackTrace();
        return ResultFactory.failed(ex.getMessage(), null);
    }
}
