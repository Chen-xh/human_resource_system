package com.chen.human_resource_system.dao;

import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.pojo.SalaryDetails;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Mapper
public interface RecordDao {

    @Select("select * from record")
    @Results(id = "recordMap", value = {
            @Result(id = true,column = "rid",property = "rid"),
            @Result(column = "lo1",property = "lo1"),
            @Result(column = "lo2",property = "lo2"),
            @Result(column = "lo3",property = "lo3"),
            @Result(column = "salary_standard",property = "salary_standard"),
            @Result(column = "lo1", property = "lo1Name",
                    one = @One(select = "com.chen.human_resource_system.dao.OrganizationDao.findNameById1", fetchType = FetchType.LAZY)),
            @Result(column = "lo2", property = "lo2Name",
                    one = @One(select = "com.chen.human_resource_system.dao.OrganizationDao.findNameById2", fetchType = FetchType.LAZY)),
            @Result(column = "lo3", property = "lo3Name",
                    one = @One(select = "com.chen.human_resource_system.dao.OrganizationDao.findNameById3", fetchType = FetchType.LAZY)),
            @Result(column = "salary_standard", property = "salaryStandard",
                    many = @Many(select = "com.chen.human_resource_system.dao.SalaryStandardDao.findById", fetchType = FetchType.LAZY))
    })
    List<Record> findAllRecord();

    @Select("select * from record where target=#{target}")
    @ResultMap(value = {"recordMap"})
    List<Record> findAllRecordByTarget(String target);

    //使用MyProvider类的select方法来生成sql
    @SelectProvider(type = MyProvider.class, method = "select")
    @ResultMap(value = {"recordMap"})
    List<Record> select(String sql);

    class MyProvider {
        public String select(String sql) {
         System.out.println(sql);
            return sql;
        }
    }
    @Select("SELECT * FROM `record` WHERE rid=#{id}")
    @ResultMap(value = {"recordMap"})
    Record findById(Long id);

    @Insert("insert into `record` " +
            "values( #{rid} ,#{username} , #{gender} , #{email} ,#{phone} , #{qq} , " +
            "#{telephone} , #{address} , #{postcode} , #{nationality} , #{birth_place} , #{birth_date} , " +
            "#{ethnic} , #{political_affiliation} , #{political_status}, #{id_number}, #{social_security_number}," +
            "#{age}, #{education}, #{professional}, #{salary_standard}, #{bank_deposit}, " +
            "#{account}, #{specialty}, #{hobby}, #{personal_resume}, #{family_relationship_information}, " +
            "#{remarks}, #{registrant}, #{registration_time}, #{position_classification}, #{position}, " +
            "#{img_url}, #{lo1}, #{lo2}, #{lo3},#{target},#{reviewer},#{review_time})")
    void insertUser(Record record);

    @Delete("DELETE FROM `record` WHERE rid=#{id}")
    void deleteRecord(Long id);


    @Update("UPDATE `record` SET " +
            "username=#{username} ,gender= #{gender} , email=#{email} ,phone=#{phone} , qq=#{qq} , " +
            "telephone=#{telephone} , address=#{address} ,postcode= #{postcode} ,nationality= #{nationality} , birth_place=#{birth_place} , birth_date=#{birth_date} , " +
            "ethnic=#{ethnic} ,political_affiliation= #{political_affiliation} , political_status=#{political_status}, id_number=#{id_number},social_security_number= #{social_security_number}," +
            "age=#{age},education= #{education},professional= #{professional},salary_standard= #{salary_standard}, bank_deposit=#{bank_deposit}, " +
            "account=#{account}, specialty=#{specialty},hobby= #{hobby}, personal_resume=#{personal_resume},family_relationship_information= #{family_relationship_information}, " +
            "remarks=#{remarks}, " +
            "img_url=#{img_url},reviewer=#{reviewer},review_time=#{review_time},target=#{target} " +
            "WHERE rid=#{rid}")

    void updateRecord(Record record);

    @Update("UPDATE `record` SET target=#{target} WHERE rid=#{rid}")
    void updateTarget(Long rid,String target);

    @Select("Select count(*) FROM `record`")
    int getCount();

    @Select("select rid,username,salary_standard from record where lo1=#{lo1} and lo2=#{lo2} and lo3=#{lo3}")
    @Results(id = "recordMap2", value = {
            @Result(id = true,column = "rid",property = "rid"),
            @Result(column = "salary_standard", property = "salaryStandard",
                    many = @Many(select = "com.chen.human_resource_system.dao.SalaryStandardDao.findById", fetchType = FetchType.LAZY))
    })
    List<SalaryDetails> findNameAndRidByOl(Long lo1, Long lo2, Long lo3);

}
