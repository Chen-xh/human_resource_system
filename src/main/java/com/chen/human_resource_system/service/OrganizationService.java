package com.chen.human_resource_system.service;

import com.chen.human_resource_system.pojo.OrganizationLevel1;
import com.chen.human_resource_system.pojo.OrganizationLevel2;
import com.chen.human_resource_system.pojo.OrganizationLevel3;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Service
public interface OrganizationService {

    List<OrganizationLevel1> findAll();
    List<String> findAll3();
    List<String> findAll2();
}
