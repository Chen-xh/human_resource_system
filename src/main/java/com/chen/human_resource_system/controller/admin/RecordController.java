package com.chen.human_resource_system.controller.admin;

import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.service.RecordService;
import com.chen.human_resource_system.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-10 21:22
 **/
@Api(tags = "档案管理接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/record/")
public class RecordController {
    @Resource
    RecordService recordService;

    @ApiOperation(value = "添加档案")
    @ApiImplicitParams({
    })
    @PostMapping("add")
    public JsonResult add(Record record, @RequestParam(value = "file") MultipartFile file) {
        recordService.addRecord(record,file);

        return JsonResult.success();

    }

    @ApiOperation(value = "复核档案，状态设为已复核")
    @ApiImplicitParams({
    })
    @PostMapping("update")
    public JsonResult update(Record record, @RequestParam(value = "file") MultipartFile file) {
        recordService.updateRecord(record,file,"已复核");
        return JsonResult.success();
    }
    @ApiOperation(value = "更新档案，状态设为未复核")
    @ApiImplicitParams({
    })
    @PostMapping("update2")
    public JsonResult update2(Record record, @RequestParam(value = "file") MultipartFile file) {
        recordService.updateRecord(record,file,"未复核");
        return JsonResult.success();

    }
    @ApiOperation(value = "根据id查询")
    @GetMapping("/{rid}")
    public JsonResult find(@PathVariable Long rid) {
        Record record = recordService.findRecordById(rid);
        return JsonResult.success().addObject("record", record);
    }

    @ApiOperation(value = "查询未复核的档案")
    @GetMapping("/findAllNoCheck")
    public JsonResult findAllNoCheck() {
        List<Record> record = recordService.findAllByTarget("未复核");
        return JsonResult.success().addObject("record", record).addObject("size",record.size());
    }
    @ApiOperation(value = "查询已复核的档案")
    @GetMapping("/findAllChecked")
    public JsonResult findAllChecked() {
        List<Record> record = recordService.findAllByTarget("已复核");
        return JsonResult.success().addObject("record", record).addObject("size",record.size());
    }
    @ApiOperation(value = "查询所有档案")
    @GetMapping("/findAll")
    public JsonResult findAll() {
        List<Record> record = recordService.findAllRecord();
        return JsonResult.success().addObject("record", record);
    }
    @ApiOperation(value = "根据条件查询档案")
    @PostMapping("/selectRecord")
    public JsonResult selectRecord(Long lo1, Long lo2, Long lo3 , String po1, String po2 , String time,String target)  {
        List<Record> record = recordService.select(lo1,lo2,lo3,po1,po2,time,target);
        return JsonResult.success().addObject("record", record);
    }
    @ApiOperation(value = "删除档案，状态设为已删除")
    @GetMapping("/delete")
    public JsonResult delete( Long rid) {
        recordService.deleteRecord(rid);
        return JsonResult.success();
    }
}
