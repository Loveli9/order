package com.example.order.common;

import java.io.Serializable;

/**
 * @author Ray
 * @see org.springframework.http.HttpStatus
 */
public enum CommonResultCode implements Serializable {

    // SUCCESS("200", "successful"),
    // SUCCESS_EXCEPTION("201", "success but no data found."),
    // EXCEPTION("-100", "server exception, msg is %s"),
    // ILLEGAL_PARAM("-101","illegal parameter, param is %s"),

    // //Authentication Exception
    // AUTHENTICATION_EXCEPTION("401","authentication exception"),

    // //Resource Exception
    // RESOURCE_ALREADY_EXIST("409","The resource already exists.Resource is %s"),
    // RESOURCE_NOT_EXIST("404","The resource not exists.Resource is %s");
    /**
     * http response code and message
     */
    SUCCESS("200", "successful"), SUCCESS_EXCEPTION("201", "success but no data found."),
    EXCEPTION("-100", "server exception, msg is %s"), ILLEGAL_PARAM("-101", "illegal parameter, param is %s"),
    AUTHENTICATION_EXCEPTION("401", "authentication exception"),
    RESOURCE_ALREADY_EXIST("409", "The resource already exists.Resource is %s"),
    RESOURCE_NOT_EXIST("404", "The resource not exists.Resource is %s"), NOT_IMPLEMENTED("501", "Not Implemented"),

    /**
     * ServiceUnavailable
     */
    UNAVAILABLE("ServiceUnavailable", "The request has failed due to a temporary failure of the server."),

    /**
     * InternalError
     */
    INTERNAL("InternalError", "The request processing has failed due to exception or failure: %s"),

    /**
     * InvalidParameter
     */
    INVALID_PARAMETER("InvalidParameter", "The specified parameter <%s> is not valid.%s"),

    ;

    public final String code;
    public final String message;

    CommonResultCode(String code, String msg){
        this.code = code;
        this.message = msg;
    }
}
