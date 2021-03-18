package com.zq.controller.base;

import com.zq.exception.CommonException;
import com.zq.exception.MyException;
import com.zq.exception.MyExceptionEnum;
import com.zq.response.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zqian
 * @date 2020/12/31
 */
@RestController
@Slf4j
public class BaseController {

    /**
     * 定义exceptionHandler解决未被controller层吸收的exception
     * @param request
     * @param e
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerException(HttpServletRequest request, Exception e) {
        if (e instanceof CommonException) {
            return new MyResponse((CommonException)e);
        } else if (e instanceof HttpMessageConversionException) {
            return new MyResponse(MyExceptionEnum.DEFAULT_ERROR,e.getMessage());
        } else {
            log.error("", e);
            return new MyResponse(MyExceptionEnum.DEFAULT_ERROR,e.getMessage());
        }
    }
}
