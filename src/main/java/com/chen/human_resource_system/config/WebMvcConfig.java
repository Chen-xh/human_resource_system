package com.chen.human_resource_system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHEN
 * 文件映射组件
 * 使前端可以通过url直接访问服务器的静态资源
 */
//@Component
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //图片存储路径,即所有图片存放根目录,可在配置文件修改
    @Value("${photo.dir}")
    String fileUrl;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");


        registry.addResourceHandler("/static/**").addResourceLocations("file:" + fileUrl);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //对那些请求路径进行跨域处理
//        registry.addMapping("/**")
//                // 允许的请求头，默认允许所有的请求头
//                .allowedHeaders("*")
//                // 允许的方法，默认允许GET、POST、HEAD
//                .allowedMethods("*")
//                // 探测请求有效时间，单位秒
//                .maxAge(1800)
//                // 支持的域
//                .allowedOrigins("*");
//    }
}

