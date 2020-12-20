package com.chen.human_resource_system.dao;

import com.chen.human_resource_system.pojo.OrganizationLevel3;
import com.chen.human_resource_system.pojo.SalaryDetails;
import com.chen.human_resource_system.pojo.SalaryList;
import com.chen.human_resource_system.pojo.SalaryStandard;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Mapper
public interface SalaryListDao {



    @Select("select * from salarylist where target=#{target}")
    @Results(id = "salaryListMap2", value = {
            @Result(id = true,column = "said",property = "said"),
            @Result(column = "lo1",property = "lo1"),
            @Result(column = "lo2",property = "lo2"),
            @Result(column = "lo3",property = "lo3"),
            @Result(column = "lo1", property = "lo1Name",
                    one = @One(select = "com.chen.human_resource_system.dao.OrganizationDao.findNameById1", fetchType = FetchType.LAZY)),
            @Result(column = "lo2", property = "lo2Name",
                    one = @One(select = "com.chen.human_resource_system.dao.OrganizationDao.findNameById2", fetchType = FetchType.LAZY)),
            @Result(column = "lo3", property = "lo3Name",
                    one = @One(select = "com.chen.human_resource_system.dao.OrganizationDao.findNameById3", fetchType = FetchType.LAZY)),
            @Result(column = "said", property = "salaryDetails",
                    many = @Many(select = "com.chen.human_resource_system.dao.SalaryListDao.findDetailsId", fetchType = FetchType.LAZY))
    })
    List<SalaryList> findAllByTarget(String target);

    @Select("select * from salarydetails where said=#{said}")
    @Results(id = "salaryDetailsMap", value = {
            @Result(column = "salary_standard",property = "salary_standard"),
            @Result(column = "salary_standard", property = "salaryStandard",
                    one = @One(select = "com.chen.human_resource_system.dao.SalaryStandardDao.findById", fetchType = FetchType.LAZY))
    })
    SalaryDetails findDetailsId(String said);

    @Update("UPDATE `salarylist` SET target=#{target} WHERE said=#{said}")
    void updateTarget(Long said,String target);

    @Select("SELECT lo1 ,lo2,lo3 FROM record GROUP BY lo1 ,lo2,lo3")
    @Results(id = "salaryListMap", value = {
            @Result(column = "lo1",property = "lo1"),
            @Result(column = "lo2",property = "lo2"),
            @Result(column = "lo3",property = "lo3"),
            @Result(column = "lo1", property = "lo1Name",
                    one = @One(select = "com.chen.human_resource_system.dao.OrganizationDao.findNameById1", fetchType = FetchType.LAZY)),
            @Result(column = "lo2", property = "lo2Name",
                    one = @One(select = "com.chen.human_resource_system.dao.OrganizationDao.findNameById2", fetchType = FetchType.LAZY)),
            @Result(column = "lo3", property = "lo3Name",
                    one = @One(select = "com.chen.human_resource_system.dao.OrganizationDao.findNameById3", fetchType = FetchType.LAZY))
    })
    List<SalaryList> findNull();

    @Select("Select count(*) FROM `salarylist`")
    int getCount();
    @Select("Select count(*) FROM `salarydetails`")
    int getCount2();


    @Insert("insert into `salarylist` " +
            "values( #{said} ,#{number} , #{amount} , #{RealAmount} ,#{registrant} , #{registrationTime} , " +
            "#{target} , #{lo1} , #{lo2} , #{lo3} )")
    void insertSalaryList(SalaryList salaryList);
    @Insert("insert into `salarydetails` " +
            "values( #{rid} ,#{username} , #{bonus} , #{deductionBonus} ,#{amount} , #{said} , " +
            "#{salary_standard} )")

    void insertSalaryDetails(SalaryDetails salaryDetails);

    @Update("UPDATE `salarydetails` SET bonus=#{bonus},deductionBonus=#{deductionBonus},amount=#{amount} WHERE rid=#{rid}")
    void updateBonus(Long rid,Double bonus,Double deductionBonus,Double amount);

    @Update("UPDATE `salarylist` SET  target=#{target},realAmount=#{realAmount} WHERE said=#{said}")
    void updateCheck(String said,String target,Double realAmount);

    //使用MyProvider类的select方法来生成sql
    @SelectProvider(type = SalaryListDao.MyProvider.class, method = "select")
    @ResultMap(value = {"salaryListMap2"})
    List<SalaryList> select(String sql);

    class MyProvider {
        public String select(String sql) {
            System.out.println(sql);
            return sql;
        }
    }
}
