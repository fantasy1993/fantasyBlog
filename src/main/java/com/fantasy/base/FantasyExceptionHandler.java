package com.fantasy.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class FantasyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public FantasyResult handleExcetion(Exception e){
        e.printStackTrace();
        return new FantasyResult(FantasyResultCode.WARN);
    }
}
