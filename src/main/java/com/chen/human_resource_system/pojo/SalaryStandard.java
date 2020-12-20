package com.chen.human_resource_system.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: CHEN
 * @date: 2020-12-11 09:20
 * 薪酬标准
 * 基本工资，交通补助，午餐补助，通信补助，
 * 养老保险，医疗保险，失业保险，住房公积金
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "handler" })
public class SalaryStandard implements Serializable {
    private Long sid;
    private String standardName;
    private String  maker;//制定人
    private String  registrant;
    private Date registrationTime;

    private Double sum; //薪酬总额
    private Double basicSalary; //基本工资
    private Double transportationAllowance; //交通补助
    private Double  lunchAllowance;//午餐补助
    private Double communicationSubsidy; //通信补助
    private Double endowmentInsurance; //养老保险
    private Double medicalInsurance; //医疗保险
    private Double unemploymentInsurance; //失业保险
    private Double housingProvidentFund; //住房公积金


    private String opinion;
    private String reviewer;
    private String target;
}
