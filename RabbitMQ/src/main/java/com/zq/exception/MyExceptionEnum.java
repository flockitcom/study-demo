package com.zq.exception;

public enum MyExceptionEnum implements CommonException {
    //通用
    DEFAULT_OK("200", "成功", "OK"),
    DEFAULT_ERROR("900", "失败", "ERROR"),
    PARAMS_ERROR("9001","参数错误","Parameter error"),
    ;
    private String code;
    private String message;
    private String date;

    MyExceptionEnum(String code, String message, String date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public CommonException setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public CommonException setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public CommonException setDate(String date) {
        this.date = date;
        return this;
    }


}
