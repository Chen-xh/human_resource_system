package com.chen.human_resource_system.util;

import com.chen.human_resource_system.exception.CustomizeErrorCode;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CHEN
 * @date 2020/10/13  12:13
 * 同一返回类型
 */
@Data
public class JsonResult implements Serializable {
    private Integer code;
    private String message;
    private Map<String, Object> extended = new HashMap<>();

    public static JsonResult success(){
        return new JsonResult(200, "处理成功");
    }

    public static JsonResult fail(){return new JsonResult(100, "处理失败!");}

    public static JsonResult errorOf(CustomizeErrorCode errorCode){
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static JsonResult errorOf(Integer code, String message) {

        return new JsonResult(code, message);
    }

    public  JsonResult addObject(String key, Object value){
        this.extended.put(key, value);
        return this;
    }

    public JsonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
