package com.chen.human_resource_system.exception;

/**
 * @author CHEN
 * @date 2020/10/13  12:05
 */
public interface CustomizeErrorCode {
    /**
     * 获取错误码
     * @return Integer
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return String
     */
    String getMessage();
}
