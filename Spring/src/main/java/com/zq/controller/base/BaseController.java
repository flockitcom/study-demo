package com.zq.controller.base;

import com.zq.common.BaseObject;
import com.zq.exception.CommonException;
import com.zq.exception.MyExceptionEnum;
import com.zq.response.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * @author zqian
 * @date 2020/12/31
 */
@RestController
@Slf4j
public class BaseController extends BaseObject {

    /**
     * 定义exceptionHandler解决未被controller层吸收的exception
     *
     * @param
     * @param e
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public MyResponse handlerException(Exception e) {
        if (e instanceof CommonException) {
            return new MyResponse((CommonException) e);
        } else if (e instanceof HttpMessageConversionException) {
            return new MyResponse(MyExceptionEnum.DEFAULT_ERROR, e.getMessage());
        } else {
            log.error("", e);
            return new MyResponse(MyExceptionEnum.DEFAULT_ERROR, e.getMessage());
        }
    }

    /**
     * 处理Validated校验异常
     * <p>
     * 注: 常见的ConstraintViolationException异常， 也属于ValidationException异常
     *
     * @param e 捕获到的异常
     * @return 返回给前端的data
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public MyResponse handleParameterVerificationException(Exception e) {
        /// BindException
        if (e instanceof BindException) {
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = ((BindException) e).getFieldError();
            if (fieldError != null) {
                return new MyResponse(MyExceptionEnum.DEFAULT_ERROR, fieldError.getDefaultMessage());
            }
            /// MethodArgumentNotValidException
        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return new MyResponse(MyExceptionEnum.DEFAULT_ERROR, fieldError.getDefaultMessage());
            }
            /// ValidationException 的子类异常ConstraintViolationException
        } else if (e instanceof ConstraintViolationException) {
            /*
             * ConstraintViolationException的e.getMessage()形如
             *     {方法名}.{参数名}: {message}
             *  这里只需要取后面的message即可
             */
            String msg = e.getMessage();
            if (msg != null) {
                int lastIndex = msg.lastIndexOf(':');
                if (lastIndex >= 0) {
                    return new MyResponse(MyExceptionEnum.DEFAULT_ERROR, msg.substring(lastIndex + 1).trim());
                }
            }
            /// ValidationException 的其它子类异常
        } else {
            return new MyResponse(MyExceptionEnum.DEFAULT_ERROR, "处理参数时异常");
        }
        return new MyResponse(MyExceptionEnum.DEFAULT_ERROR);
    }
}
