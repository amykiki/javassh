package org.mybatis.smvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Created by Amysue on 2016/6/1.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(ConstraintViolationException e) {
        String error = "请求参数不合法" + "\n";
        for (ConstraintViolation<?> s : e.getConstraintViolations()) {
            error += s.getMessage() + "\n";
        }
        return error;
    }
}
