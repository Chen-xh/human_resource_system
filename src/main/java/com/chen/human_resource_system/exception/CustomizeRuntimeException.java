package com.chen.human_resource_system.exception;

/**
 * @author CHEN
 * @date 2020/10/13  18:28
 */
public class CustomizeRuntimeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeRuntimeException(CustomizeErrorCode customizeErrorCode) {

        this.code = customizeErrorCode.getCode();
        this.message = customizeErrorCode.getMessage();

    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
