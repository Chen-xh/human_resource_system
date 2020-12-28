package com.chen.human_resource_system.dao;

import com.chen.human_resource_system.pojo.OrganizationLevel1;
import com.chen.human_resource_system.pojo.OrganizationLevel2;
import com.chen.human_resource_system.pojo.OrganizationLevel3;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Mapper
public interface OrganizationDao {

    @Select("select * from organizationlevel3 where ol2Id=#{ol2Id}")
    OrganizationLevel3 findOL3ByOL2Id(Long ol2Id);

    @Select("select * from organizationlevel2 where ol1Id=#{ol1Id}")
    @Results(id = "ol2Map", value = {
            @Result(id = true,column = "ol2Id",property = "ol2Id"),
            @Result(column = "ol2Id", property = "sub",
                    many = @Many(select = "com.chen.human_resource_system.dao.OrganizationDao.findOL3ByOL2Id", fetchType = FetchType.LAZY))
    })
    OrganizationLevel2 findOL2ByOL1Id(Long ol1Id);

    @Select("select * from organizationlevel1 ")
    @Results(id = "ol1Map", value = {
            @Result(id = true,column = "ol1Id",property = "ol1Id"),
            @Result(column = "ol1Id", property = "sub",
                    many = @Many(select = "com.chen.human_resource_system.dao.OrganizationDao.findOL2ByOL1Id", fetchType = FetchType.LAZY))
    })
    List<OrganizationLevel1> findAll();

    @Select("select organizationName from organizationlevel1 where ol1Id=#{id}")
    String findNameById1(Long id);
    @Select("select organizationName from organizationlevel2 where ol2Id=#{id}")
    String findNameById2(Long id);
    @Select("select organizationName from organizationlevel3 where ol3Id=#{id}")
    String findNameById3(Long id);


    @Select("select organizationName from organizationlevel3 ")
    List<String> findAll3();
    @Select("select organizationName from organizationlevel2 ")
    List<String> findAll2();
}
