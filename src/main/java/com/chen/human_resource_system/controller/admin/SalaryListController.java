package com.chen.human_resource_system.controller.admin;

import com.chen.human_resource_system.pojo.SalaryDetails;
import com.chen.human_resource_system.pojo.SalaryList;
import com.chen.human_resource_system.pojo.SalaryStandard;
import com.chen.human_resource_system.service.SalaryListService;
import com.chen.human_resource_system.service.SalaryStandardService;
import com.chen.human_resource_system.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-10 21:22
 **/
@Api(tags = "薪酬发放管理接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/salaryList/")
public class SalaryListController {
    @Resource
    SalaryListService salaryListService;

    @ApiOperation(value = "登记薪酬发放单")
    @ApiImplicitParams({
    })
    @PostMapping("/add")
    public JsonResult addStandard( SalaryList salaryList, String details) {
        salaryListService.addSalaryList(salaryList,details);
        return JsonResult.success();
    }
    @ApiOperation(value = "复核薪酬发放单")
    @ApiImplicitParams({
    })
    @PostMapping("/check")
    public JsonResult check( SalaryList salaryList, String details) {
        salaryListService.updateCheck(salaryList,details,"已复核");
        return JsonResult.success();
    }

    @ApiOperation(value = "查询所有薪酬发放单")
    @GetMapping("/findAll")
    public JsonResult findAll() {
        List<SalaryList> salaryLists = salaryListService.makeAll();
        return JsonResult.success().addObject("salaryLists", salaryLists);
    }
    @ApiOperation(value = "查询未复核薪酬发放单")
    @GetMapping("/findAllNoCheck")
    public JsonResult findAllNoCheck() {
        List<SalaryList> salaryLists = salaryListService.findAllByTarget("未复核");
        return JsonResult.success().addObject("salaryLists", salaryLists);
    }
    @ApiOperation(value = "查询已复核薪酬发放单")
    @GetMapping("/findAllChecked")
    public JsonResult findAllChecked() {
        List<SalaryList> salaryLists = salaryListService.findAllByTarget("已复核");
        return JsonResult.success().addObject("salaryLists", salaryLists);
    }
    @ApiOperation(value = "根据条件查询薪酬发放单")
    @PostMapping("/selectSalaryList")
    public JsonResult selectSalaryStandard(String time,String sid,String key)  {
        List<SalaryList> salaryLists = salaryListService.select(time,sid,key);
        return JsonResult.success().addObject("salaryLists", salaryLists);
    }
}
