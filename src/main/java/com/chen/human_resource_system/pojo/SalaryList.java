package com.chen.human_resource_system.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-11 09:20
 * 薪酬单
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "handler" })
public class SalaryList implements Serializable {
    private String said;
    private int number;

    private Double amount;
    private Double realAmount;
    private String registrant;
    private Date registrationTime;
    private String target;
    //I级机构
    private Long lo1;
    //II级机构
    private Long lo2;
    //III级机构
    private Long lo3;
    private String lo1Name;
    private String lo2Name;
    private String lo3Name;
    private List<SalaryDetails> salaryDetails;
}
