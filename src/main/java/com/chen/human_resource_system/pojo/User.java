package com.chen.human_resource_system.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: CHEN
 * @date: 2020-12-09 09:30
 * 管理用户账号
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private Long userId;

    private String username;
    @JsonIgnore
    private String password;

}
