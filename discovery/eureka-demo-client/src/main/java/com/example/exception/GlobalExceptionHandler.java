package com.example.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -15:07
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ClientException.class)
    public Response exceptionHandler(HttpServletRequest req, ClientException e) throws Exception {
        return Response.getFailInstance(Response.ERROR, e.getMessage(), req.getRequestURL());
    }
}
