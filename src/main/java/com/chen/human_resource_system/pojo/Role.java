package com.chen.human_resource_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author CHEN
 * @date 2020/10/12  19:07
 * 管理用户角色
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role implements Serializable {

    private Long rid;

    private String roleName;

    private String target;
}
