package com.chen.human_resource_system.service.impl;

import com.chen.human_resource_system.dao.RecordDao;
import com.chen.human_resource_system.dao.SalaryStandardDao;
import com.chen.human_resource_system.exception.CustomizeRuntimeException;
import com.chen.human_resource_system.exception.MyCustomizeErrorCode;
import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.pojo.SalaryStandard;
import com.chen.human_resource_system.service.RecordService;
import com.chen.human_resource_system.service.SalaryStandardService;
import com.chen.human_resource_system.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: CHEN
 * @date: 2020-12-10 21:01
 **/
@Service
@Transactional
public class SalaryStandardImpl implements SalaryStandardService {
    @Resource
    SalaryStandardDao salaryStandardDao;


    @Override
    public List<SalaryStandard> findAll() {
        return salaryStandardDao.findAllSalary();
    }

    @Override
    public List<SalaryStandard> findAllByTarget(String target) {
        return salaryStandardDao.findAllSalaryByTarget(target);
    }

    @Override
    public List<SalaryStandard> select(String time,Long sid,String key) {
        String sql = "SELECT * FROM salarystandard where 1=1 ";
        if(sid!=null)sql=sql+" and sid like '%"+sid+"%'";
        System.out.println(key);
        if(key!=null&&key.length()>0)sql=sql+" and (standardName like '%"+key+"%' OR  maker like '%"+key+"%' OR registrant like '%"+key+"%' OR  reviewer like '%"+key+"%')";
        if (time != null && time.contains(",")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = new Date(time.split(",")[0]);
            Date end = new Date(time.split(",")[1]);
            sql = sql + " and registrationTime BETWEEN '" + sdf.format(start) + "' and '" + sdf.format(end)+"'";
        }
        return salaryStandardDao.select(sql);
    }

    @Override
    public SalaryStandard findSalaryStandardById(Long id) {
        return salaryStandardDao.findById(id);
    }

    @Override
    public void deleteSalaryStandard(Long id) {

    }

    @Override
    public void addSalaryStandard(SalaryStandard salaryStandard) {
        int count=salaryStandardDao.getCount()+1;
        String id="1"+String.format("%011d", count);
        salaryStandard.setSid(Long.parseLong(id));
        salaryStandard.setTarget("未复核");
        salaryStandardDao.insertSalaryStandard(salaryStandard);
    }

    @Override
    public void updateSalaryStandard(SalaryStandard salaryStandard, String target) {
        SalaryStandard oldOne=salaryStandardDao.findById(salaryStandard.getSid());
        if(oldOne==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_SALARYSTANDARD);
        }
        salaryStandard.setTarget(target);
        salaryStandardDao.updateSalaryStandard(salaryStandard);
    }

    @Override
    public void changeTarget(Long id, String target) {
        salaryStandardDao.updateTarget(id,target);
    }
}
