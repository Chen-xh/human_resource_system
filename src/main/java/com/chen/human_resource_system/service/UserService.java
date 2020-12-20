package com.chen.human_resource_system.service;

import com.chen.human_resource_system.pojo.Role;
import com.chen.human_resource_system.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser() ;

    User findUserByUserName(String username) ;

    List<String> getUserRole(Long uid);
}
