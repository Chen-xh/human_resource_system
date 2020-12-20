package com.chen.human_resource_system.jwt;


import com.chen.human_resource_system.exception.CustomizeRuntimeException;
import com.chen.human_resource_system.exception.MyCustomizeErrorCode;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


/**
 * @author CHEN
 * @date 2020/3/1 16:47
 * 通过此过滤器得到HTTP请求资源获取Authorization传递过来的token参数
 * 获取subject对象进行身份验证
 */
public class JWTFilter extends AccessControlFilter {
    private static final Logger PLOG = LoggerFactory.getLogger(JWTFilter.class);

    /**
     * 判断是否带TOKEN请求
     */
    private boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("token");
        return !StringUtils.isEmpty(authorization);
    }

    /**
     * 登录
     */
    private void executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("token");

        JWTToken token = new JWTToken(authorization);
        String tokens = (String) token.getCredentials();
        String studentNo = JWTUtil.getUsername(tokens);
        request.setAttribute("studentNo", studentNo);

        getSubject(request, response).login(token);

    }

    /**
     * 这里控制通过与否
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //这里先让它始终返回false来使用onAccessDenied()方法
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        System.out.println("onAccessDenied 方法被调用");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // OPTIONS 预请求 忽略
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        try {
            // 如果不带TOKEN请求，直接阻止
            if (!isLoginAttempt(request, response)) {
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_LOGIN);
//            return false;
            }

            executeLogin(request, response);
//            String authorization = httpServletRequest.getHeader("token");
//
//            JWTToken token = new JWTToken(authorization);
//            String tokens = (String) token.getCredentials();
//            String studentNo = JWTUtil.getUsername(tokens);
//            request.setAttribute("studentNo", studentNo);

        } catch (CustomizeRuntimeException e) {
            PLOG.error("JWT >> " + e);
            responseError(request, response, e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * 将非法请求跳转到 /error/*
     */
    private void responseError(ServletRequest req, ServletResponse resp, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");

            req.getRequestDispatcher("/error/" + message).forward(req, resp);
        } catch (IOException | ServletException e) {
            PLOG.error("JWT >> " + e);
        }
    }

}

