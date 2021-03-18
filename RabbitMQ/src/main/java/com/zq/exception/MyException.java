package com.zq.exception;

public class MyException extends Exception implements CommonException {
    private final CommonException commonException;

    /**
     * 直接接收EmBusinessError的传参用于构造业务异常
     * @param commonException
     */
    public MyException(CommonException commonException) {
        //一定要调用super(),因为exception自身会有初始化机制
        super();
        this.commonException = commonException;
    }

    /**
     * 接受自定义message的方式构造业务异常
     * @param commonException
     * @param message
     */
    public MyException(CommonException commonException, String message) {
        super();
        this.commonException = commonException;
        this.commonException.setMessage(message);
        this.commonException.setDate(message);
    }

    /**
     * 接受自定义message,date的方式构造业务异常
     * @param commonException
     * @param message
     * @param date
     */
    public MyException(CommonException commonException, String message, String date) {
        super();
        this.commonException = commonException;
        this.commonException.setMessage(message);
        this.commonException.setDate(date);
    }

    /**
     * 接受自定义message,date的方式构造业务异常
     * @param code
     * @param message
     * @param date
     */
    public MyException(String code, String message, String date) {
        super();
        CommonException commonException = MyExceptionEnum.DEFAULT_ERROR;
        commonException.setCode(code);
        commonException.setMessage(message);
        commonException.setDate(date);
        this.commonException = commonException;
    }

    @Override
    public String getCode() {
        return this.commonException.getCode();
    }

    @Override
    public CommonException setCode(String code) {
        return this.commonException.setCode(code);
    }

    @Override
    public String getMessage() {
        return this.commonException.getMessage();
    }

    @Override
    public CommonException setMessage(String message) {
        return this.commonException.setMessage(message);
    }

    @Override
    public String getDate() {
        return this.commonException.getDate();
    }

    @Override
    public CommonException setDate(String date) {
        return this.commonException.setDate(date);
    }
}
