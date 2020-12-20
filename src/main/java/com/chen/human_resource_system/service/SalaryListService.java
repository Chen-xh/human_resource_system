package com.chen.human_resource_system.service;

import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.pojo.SalaryList;
import com.chen.human_resource_system.pojo.SalaryStandard;

import java.util.List;

public interface SalaryListService {

    List<SalaryList> makeAll();

    List<SalaryList> findAllByTarget(String target);

    void addSalaryList(SalaryList salaryList,String details);

    void updateCheck(SalaryList salaryList,String details,String target);

    List<SalaryList> select(String time,String sid,String key);
}
