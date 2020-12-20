package com.chen.human_resource_system.dao;

import com.chen.human_resource_system.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Mapper
public interface UserDao {

    @Select("select * from user")
    List<User> findAllUser();

    @Select("SELECT * FROM `user` WHERE id=#{id}")
    User findUserById(Long id);

    @Select("SELECT * FROM `user` WHERE username=#{username}")
    User findUserByUsername(String username);

    @Select("SELECT target from user_role u left outer join role r on(u.rid=r.rid) where u.uid=#{uid}")
    List<String> getUserRole(Long uid);
    @Select("SELECT roleName from user_role u left outer join role r on(u.rid=r.rid) where u.uid=#{uid}")
    List<String> getUserRoleName(Long uid);
}
