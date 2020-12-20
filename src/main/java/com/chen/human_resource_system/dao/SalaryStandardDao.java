package com.chen.human_resource_system.dao;

import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.pojo.SalaryStandard;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Mapper
public interface SalaryStandardDao {

    @Select("select * from salarystandard")
    List<SalaryStandard> findAllSalary();

    @Select("select * from salarystandard where target=#{target}")
    List<SalaryStandard> findAllSalaryByTarget(String target);

    //使用MyProvider类的select方法来生成sql
    @SelectProvider(type = MyProvider.class, method = "select")
    List<SalaryStandard> select(String sql);
    class MyProvider {
        public String select(String sql) {
         System.out.println(sql);
            return sql;
        }
    }
    @Select("SELECT * FROM `salarystandard` WHERE sid=#{id}")
    SalaryStandard findById(Long id);


    @Insert("insert into `salarystandard` " +
            "values( #{sid} ,#{standardName} , #{maker} , #{registrant} ,#{registrationTime} , #{sum} , " +
            "#{basicSalary} , #{transportationAllowance} , #{lunchAllowance} , #{communicationSubsidy} , #{endowmentInsurance} , #{medicalInsurance} , " +
            "#{unemploymentInsurance} , #{housingProvidentFund} , #{opinion}, #{reviewer}, #{target})" )
    void insertSalaryStandard(SalaryStandard salaryStandard);


    @Update("UPDATE `salarystandard` SET " +
            "standardName=#{standardName} ,maker= #{maker} , registrant=#{registrant} ,registrationTime=#{registrationTime} , sum=#{sum} , " +
            "basicSalary=#{basicSalary} , transportationAllowance=#{transportationAllowance} ,lunchAllowance= #{lunchAllowance} ,communicationSubsidy= #{communicationSubsidy}  ," +
            "endowmentInsurance=#{endowmentInsurance} , medicalInsurance=#{medicalInsurance} ,unemploymentInsurance= #{unemploymentInsurance} ,housingProvidentFund= #{housingProvidentFund}  ," +
            "opinion=#{opinion} ,reviewer= #{reviewer} , target=#{target}  " +
            "WHERE sid=#{sid}")
    void updateSalaryStandard(SalaryStandard salaryStandard);


    @Update("UPDATE `salarystandard` SET target=#{target} WHERE sid=#{sid}")
    void updateTarget(Long sid,String target);

    @Select("Select count(*) FROM `salarystandard`")
    int getCount();
}
