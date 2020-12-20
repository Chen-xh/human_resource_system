package com.chen.human_resource_system.exception;

import com.chen.human_resource_system.util.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  18:28
 */
//@RestControllerAdvice
public class MyCustomizeExceptionHandler {

    @ExceptionHandler(CustomizeAuthenticationException.class)
    public JsonResult CustomizeAuthenticationException(CustomizeAuthenticationException ex) {
        return JsonResult.errorOf(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public JsonResult validationBodyException(BindException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        JsonResult jsonResult = JsonResult.fail();
        int index = 0;
        for (ObjectError allError : allErrors) {
            jsonResult.addObject(allError.getCode() + index, allError.getDefaultMessage());
            index++;
        }
        return jsonResult;
    }

    @ExceptionHandler(MultipartException.class)
    public JsonResult handleMultipartException() {

        return JsonResult.errorOf(MyCustomizeErrorCode.FILE_MAX_SIZE_EXCEPTION);
    }

    @ExceptionHandler(RuntimeException.class)
    public JsonResult handleCustomizeException(HttpServletRequest request, Throwable ex) {
        //获取错误状态码
        HttpStatus status = getStatus(request);
        if (ex instanceof CustomizeRuntimeException) {
            CustomizeRuntimeException customizeException = (CustomizeRuntimeException) ex;
            return JsonResult.errorOf(customizeException.getCode(), customizeException.getMessage());
        }
        if (status.is5xxServerError() || ex instanceof ParseException) {
            return JsonResult.errorOf(MyCustomizeErrorCode.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
