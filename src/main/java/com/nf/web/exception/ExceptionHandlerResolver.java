package com.nf.web.exception;

import com.nf.mvc.ViewResult;
import com.nf.mvc.exception.ExceptionHandler;
import com.nf.mvc.exception.vo.ResponseVO;

import static com.nf.mvc.handler.HandlerHelper.json;

public class ExceptionHandlerResolver {
    @ExceptionHandler(RuntimeException.class)
    public ViewResult result(RuntimeException e){

        return json(new ResponseVO(500,e.getMessage(),e.getMessage()));
    }
}
