package com.chen.human_resource_system.service.impl;

import com.chen.human_resource_system.dao.RecordDao;
import com.chen.human_resource_system.exception.CustomizeRuntimeException;
import com.chen.human_resource_system.exception.MyCustomizeErrorCode;
import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.pojo.User;
import com.chen.human_resource_system.service.RecordService;
import com.chen.human_resource_system.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: CHEN
 * @date: 2020-12-10 21:01
 **/
@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    @Resource
    RecordDao recordDao;
    @Resource
    FileUploadUtil fileUploadUtil;

    @Override
    public List<Record> findAllRecord() {
        return recordDao.findAllRecord();
    }

    @Override
    public List<Record> findAllByTarget(String target) {
        return recordDao.findAllRecordByTarget(target);
    }

    @Override
    public List<Record> select(Long lo1, Long lo2, Long lo3, String po1, String po2, String time,String target) {
        String sql = "SELECT * FROM record where 1=1 ";
        if(lo1!=null)sql=sql+" and lo1="+lo1;
        if(lo2!=null)sql=sql+" and lo2="+lo3;
        if(lo3!=null)sql=sql+" and lo3="+lo3;
        if(po1!=null&&!po1.equals("null"))sql=sql+" and position_classification='"+po1+"'";
        if(po2!=null&&!po2.equals("null"))sql=sql+" and position='"+po2+"'";
        if(target!=null&&!target.equals("null"))sql=sql+" and target='"+target+"'";
        if (time != null && time.contains(",")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = new Date(time.split(",")[0]);
            Date end = new Date(time.split(",")[1]);

            sql = sql + " and registration_time BETWEEN '" + sdf.format(start) + "' and '" + sdf.format(end)+"'";
        }
        return recordDao.select(sql);
    }

    @Override
    public Record findRecordById(Long id) {
        return recordDao.findById(id);
    }

    @Override
    public void deleteRecord(Long id) {
        Record oldOne = recordDao.findById(id);
        if (oldOne == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_RECORD);
        }
        if(oldOne.getTarget().equals("未复核")){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.CAN_NOT_DELETE);
        }
        recordDao.updateTarget(id,"已删除");
    }

    @Override
    public void addRecord(Record record, MultipartFile file) {
        //文件不能为空
        if (file == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.FILE_IS_NULL);
        }
        //上传文件
        String fileName = fileUploadUtil.getNewFileName(file);
        fileUploadUtil.uploadOneFile(file, "/head/" + fileName);
        fileName = "/static/head/" + fileName;
        record.setImg_url(fileName);
        int count=recordDao.getCount()+1;
        //系统生成id
        String lo1 = String.format("%02d", record.getLo1());
        String lo2 = String.format("%02d", record.getLo2());
        String lo3 = String.format("%02d", record.getLo3());
        String end = String.format("%02d", count);
        String id= Calendar.getInstance().get(Calendar.YEAR) +lo1+lo2+lo3+end;
        record.setRid(Long.parseLong(id));

        //标记档案状态
        record.setTarget("未复核");

        //保存
        recordDao.insertUser(record);
    }

    @Override
    public void updateRecord(Record record, MultipartFile file,String target) {
        Record oldOne = recordDao.findById(record.getRid());
        if (oldOne == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_RECORD);
        }
        //文件不为空
        if (file != null && Objects.requireNonNull(file.getOriginalFilename()).length() != 0) {
            //上传文件
            fileUploadUtil.deletePhoto(oldOne.getImg_url());
            String fileName = fileUploadUtil.getNewFileName(file);
            fileUploadUtil.uploadOneFile(file, "/head/" + fileName);
            fileName = "/static/head/" + fileName;
            record.setImg_url(fileName);
        }
        //标记档案状态
        record.setTarget(target);
        //保存
        recordDao.updateRecord(record);
    }

    @Override
    public void changeTarget(Long id, String target) {
        recordDao.updateTarget(id, target);
    }
}
