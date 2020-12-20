package com.chen.human_resource_system.service;

import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecordService {

    List<Record> findAllRecord();

    List<Record> findAllByTarget(String target);

    List<Record> select(Long lo1, Long lo2, Long lo3 , String po1, String po2 , String time,String target);

    Record findRecordById(Long id);

    void deleteRecord(Long id);

    void addRecord(Record record, MultipartFile file);

    void updateRecord(Record record, MultipartFile file,String target);

    void changeTarget(Long id,String target);

}
