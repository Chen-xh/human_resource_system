package com.chen.human_resource_system.exception;

/**
 * @author CHEN
 * @date 2020/10/13  12:07
 */
public enum MyCustomizeErrorCode implements CustomizeErrorCode {
    /**
     * 2001:没有找到用户!
     */
    NOT_FOND_USER(2001,"没有找到用户!"),
    /**
     * 2011:上传文件不能为空!
     */
    FILE_IS_NULL(2011,"上传文件不能为空!"),
    /**
     * 2012:没有找到资源!
     */
    NOT_FOUND_RESOURCES(2012,"没有找到资源!"),
    /**
     * 2013:没有该角色!
     */
    NOT_FOND_ROLE(2013,"没有该角色!"),
    /**
     * 2014:没有该薪酬标准!
     */
    NOT_FOND_SALARYSTANDARD(2014,"没有该薪酬标准!"),
    /**
     * 2009:没有该档案!
     */
    NOT_FOND_RECORD(2009,"没有该档案!"),
    /**
     * 3001:尚未登录!
     */
    NOT_LOGIN(3001,"尚未登录!"),
    /**
     * 3002:没有权限!
     */
    NOT_ALLOWED(3002,"没有权限!"),
    /**
     * 3003:密码错误!
     */
    PASS_NOT_CORRECT(3003,"密码错误!"),
    /**
     * 3004:账号或密码错误!
     */
    USERNAME_PASS_NOT_CORRECT(3004,"账号或密码错误!"),
    /**
     * 3005:不能删除未复核档案!
     */
    CAN_NOT_DELETE(3005,"不能删除未复核档案!"),

    /**
     * 3007:文件大小超出限制！
     */
    FILE_MAX_SIZE_EXCEPTION(3007, "文件大小超出限制！"),
    /**
     * 500:服务端异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器冒烟了...要不等它降降温后再来访问?");

    private Integer code;
    private String message;

    MyCustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
