package com.example.order.common;

/**
 * @author author
 */
@SuppressWarnings("unchecked")
public class PlainResponse<T> extends BaseResponse {

    /**
     * default serial version UID
     */
    private static final long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static PlainResponse ok(Object obj) {
        PlainResponse plainResponse = new PlainResponse<>();
        plainResponse.setData(obj);
        return plainResponse;
    }

    /**
     * 操作错误，错误码501，succeed = false
     *
     * @param errorMsg
     * @return
     */
    public static PlainResponse fail(String errorMsg) {
        PlainResponse plainResponse = new PlainResponse<>();
        plainResponse.setErrorMessage(CommonResultCode.INVALID_PARAMETER.code, errorMsg);
        return plainResponse;
    }

    public PlainResponse message(String message) {
        this.setMessage(message);
        return this;
    }
}
