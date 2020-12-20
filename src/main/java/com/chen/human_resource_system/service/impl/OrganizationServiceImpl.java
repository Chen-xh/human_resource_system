package com.chen.human_resource_system.service.impl;

import com.chen.human_resource_system.dao.OrganizationDao;
import com.chen.human_resource_system.pojo.OrganizationLevel1;
import com.chen.human_resource_system.service.OrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    @Resource
    OrganizationDao organizationDao;

    @Override
    public List<OrganizationLevel1> findAll() {
        return organizationDao.findAll();
    }

}
