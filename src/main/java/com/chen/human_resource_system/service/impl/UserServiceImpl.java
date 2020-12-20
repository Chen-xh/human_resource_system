package com.chen.human_resource_system.service.impl;

import com.chen.human_resource_system.dao.UserDao;
import com.chen.human_resource_system.pojo.Role;
import com.chen.human_resource_system.pojo.User;
import com.chen.human_resource_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-10 21:01
 **/
@Service
@Transactional
public class UserServiceImpl  implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public List<String> getUserRole(Long uid) {
        return userDao.getUserRoleName(uid);
    }
}
