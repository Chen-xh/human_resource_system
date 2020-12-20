package com.chen.human_resource_system.controller.admin;

import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.pojo.SalaryStandard;
import com.chen.human_resource_system.service.RecordService;
import com.chen.human_resource_system.service.SalaryStandardService;
import com.chen.human_resource_system.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-10 21:22
 **/
@Api(tags = "薪酬管理接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/salary/")
public class SalaryController {
    @Resource
    SalaryStandardService salaryStandardService;

    @ApiOperation(value = "添加薪酬标准")
    @ApiImplicitParams({
    })
    @PostMapping("addStandard")
    public JsonResult addStandard(SalaryStandard salaryStandard) {
        salaryStandardService.addSalaryStandard(salaryStandard);
        return JsonResult.success();

    }
    @ApiOperation(value = "查询未复核的薪酬标准")
    @GetMapping("/findAllNoCheck")
    public JsonResult findAllNoCheck() {
        List<SalaryStandard> salaryStandards = salaryStandardService.findAllByTarget("未复核");
        return JsonResult.success().addObject("salaryStandards", salaryStandards).addObject("size",salaryStandards.size());
    }
    @ApiOperation(value = "查询已复核的薪酬标准")
    @GetMapping("/findAllChecked")
    public JsonResult findAllChecked() {
        List<SalaryStandard> salaryStandards = salaryStandardService.findAllByTarget("已复核");
        return JsonResult.success().addObject("salaryStandards", salaryStandards).addObject("size",salaryStandards.size());
    }
    @ApiOperation(value = "查询所有薪酬标准")
    @GetMapping("/findAll")
    public JsonResult findAll() {
        List<SalaryStandard> salaryStandards = salaryStandardService.findAll();
        return JsonResult.success().addObject("salaryStandards", salaryStandards).addObject("size",salaryStandards.size());
    }
    @ApiOperation(value = "根据条件查询薪酬标准")
    @PostMapping("/selectSalaryStandard")
    public JsonResult selectSalaryStandard(String time,Long sid,String key)  {
        List<SalaryStandard> salaryStandards = salaryStandardService.select(time,sid,key);
        return JsonResult.success().addObject("salaryStandards", salaryStandards);
    }
    @ApiOperation(value = "复核薪酬标准，状态设为已复核")
    @ApiImplicitParams({
    })
    @PostMapping("update")
    public JsonResult update(SalaryStandard salaryStandard) {
        salaryStandardService.updateSalaryStandard(salaryStandard,"已复核");
        return JsonResult.success();
    }
}
