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
 * @date: 2020-12-09 09:13
 * 档案信息
 * <p>
 * 档案信息包括：
 * I级机构、II级机构、III级机构、职位分类、职位名称、职称；
 * 姓名、性别、Email、电话、QQ、手机、住址、邮编、国籍、出生地、出生日期、
 * 民族、宗教信仰、政治面貌、身份证号码、社会保障号码、年龄、学历、学历专业、
 * 薪酬标准、开户行、账号、特长、爱好、个人履历、家庭关系信息、备注、登记人、登记时间。
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "handler" })
public class Record implements Serializable {
    //档案编号
    private Long rid;
    private String username;
    private String gender;
    private String email;
    private String phone;
    private String qq;
    private String telephone;
    private String address;
    private String postcode;
    private String nationality;
    private String birth_place;
    private Date birth_date;
    private String ethnic;
    private String political_affiliation;
    private String political_status;
    private String id_number;
    private String social_security_number;
    private Integer age;
    private String education;
    private String professional;
    private Long salary_standard;
    private String bank_deposit;
    private String account;
    private String specialty;
    private String hobby;
    private String personal_resume;
    private String family_relationship_information;
    private String remarks;
    private String registrant;
    private Date registration_time;
    private String position_classification;
    private String position;
    private String img_url;
    //I级机构
    private Long lo1;
    //II级机构
    private Long lo2;
    //III级机构
    private Long lo3;
    private String target;
    private String reviewer;
    private Date review_time;

    private String lo1Name;
    private String lo2Name;
    private String lo3Name;
    private SalaryStandard salaryStandard;
}
