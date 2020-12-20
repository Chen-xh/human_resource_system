package com.chen.human_resource_system.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-10 20:54
 * I级机构
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "handler" })
public class OrganizationLevel1 {
    //机构编号
    private Long ol1Id;
    //机构名称
    private String organizationName;
    //机构描述
    private String organizationDescribe;
    private List<OrganizationLevel2> sub;
}
