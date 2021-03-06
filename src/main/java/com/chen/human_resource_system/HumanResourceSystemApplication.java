package com.chen.human_resource_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@SpringBootApplication
public class HumanResourceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanResourceSystemApplication.class, args);
    }

}
