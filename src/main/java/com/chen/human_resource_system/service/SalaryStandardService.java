package com.chen.human_resource_system.service;

import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.pojo.SalaryStandard;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SalaryStandardService {

    List<SalaryStandard> findAll();

    List<SalaryStandard> findAllByTarget(String target);

    List<SalaryStandard> select(String time,Long sid,String key);

    SalaryStandard findSalaryStandardById(Long id);

    void deleteSalaryStandard(Long id);

    void addSalaryStandard(SalaryStandard salaryStandard);
    void updateSalaryStandard(SalaryStandard salaryStandard,String target);
    void changeTarget(Long id,String target);

}
