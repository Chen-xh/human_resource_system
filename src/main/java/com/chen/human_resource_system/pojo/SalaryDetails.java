package com.chen.human_resource_system.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author: CHEN
 * @date: 2020-12-11 09:20
 * 薪酬单详情
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "handler" })
public class SalaryDetails implements Serializable {
    private Long rid;
    private String username;
    private Double bonus;
    private Double deductionBonus;
    private Double amount;
    private String said;
    private Long salary_standard;

    private  SalaryStandard salaryStandard;
}
