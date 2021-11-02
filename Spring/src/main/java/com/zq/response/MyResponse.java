package com.zq.response;

import com.zq.exception.CommonException;
import com.zq.exception.MyException;
import com.zq.exception.MyExceptionEnum;
import lombok.Data;

/**
 * 返回类
 * @author zqian
 * @date 2020/12/31
 */
@Data
public class MyResponse {
    private String code;
    private String message;
    private Object data;

    public MyResponse() {
    }

    public MyResponse(CommonException commonException) {
        this.code = commonException.getCode();
        this.message = commonException.getMessage();
        this.data = commonException.getDate();
    }

    public MyResponse(CommonException commonException, String message) {
        this.code = commonException.getCode();
        this.message = message;
        this.data = message;
    }

    public MyResponse(CommonException commonException, Object object) {
        this.code = commonException.getCode();
        this.message = commonException.getMessage();
        this.data = object;
    }
    /**
     * 通用成功
     */
    public static MyResponse returnSuccess(){
        return new MyResponse(MyExceptionEnum.DEFAULT_OK);
    }

    /**
     * 通用成功
     */
    public static MyResponse returnSuccess(Object object){
        return new MyResponse(MyExceptionEnum.DEFAULT_OK,object);
    }

    /**
     * 通用失败
     */
    public static void returnFail() throws MyException {
        throw new MyException(MyExceptionEnum.DEFAULT_ERROR);
    }

    /**
     * 使用MyResponseEnum失败
     */
    public static void returnFail(CommonException commonException) throws MyException {
        throw new MyException(commonException);
    }

    /**
     * 使用MyResponseEnum失败,并自定义失败message(message和data一致)
     */
    public static void returnFail(CommonException commonException,String message) throws MyException {
        throw new MyException(commonException,message);
    }
}
