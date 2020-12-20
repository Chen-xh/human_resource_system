package com.chen.human_resource_system.exception;


import org.apache.shiro.authc.AuthenticationException;

/**
 * @author CHEN
 * @date 2020/3/5 16:47
 */
public class CustomizeAuthenticationException extends AuthenticationException {

    private Integer code;
    private String message;

    public CustomizeAuthenticationException(CustomizeErrorCode customizeErrorCode) {

        this.code = customizeErrorCode.getCode();
        this.message = customizeErrorCode.getMessage();

    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
