package com.example.order.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author Ray
 */
@SuppressWarnings("unchecked")
public class BaseResponse implements Serializable {

    /**
     * default serial version UID
     */
    private static final long serialVersionUID = 1L;

    private String            code;

    private String            message;

    private boolean           succeed;

    public BaseResponse(){
        this.code = CommonResultCode.SUCCESS.code;
        this.succeed = true;
        this.message = CommonResultCode.SUCCESS.message;
    }

    /**
     * 设置错误信息
     *
     * @param code 错误编码
     * @param message 错误信息
     */
    public <R extends BaseResponse> R setErrorMessage(String code, String message) {
        this.code = code;
        this.succeed = false;
        this.message = message;
        return (R) this;
    }

    /**
     * 设置错误信息
     * 
     * @param rc 错误编码
     * @param args 附加错误信息
     * @see CommonResultCode
     */
    public <R extends BaseResponse> R setError(CommonResultCode rc, Object... args) {
        this.code = rc.code;
        this.succeed = false;
        if (args == null || args.length == 0) {
            this.message = rc.message;
        } else {
            this.message = String.format(rc.message, args);
        }
        return (R) this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSucceed() {
        succeed = CommonResultCode.SUCCESS.code.equals(code);
        return succeed;
    }

    protected void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
