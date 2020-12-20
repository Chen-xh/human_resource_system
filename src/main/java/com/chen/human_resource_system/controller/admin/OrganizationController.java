package com.chen.human_resource_system.controller.admin;

import com.chen.human_resource_system.pojo.OrganizationLevel1;
import com.chen.human_resource_system.pojo.OrganizationLevel2;
import com.chen.human_resource_system.pojo.Record;
import com.chen.human_resource_system.service.OrganizationService;
import com.chen.human_resource_system.service.RecordService;
import com.chen.human_resource_system.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-10 21:22
 **/
@Api(tags = "机构查询接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/organization/")
public class OrganizationController {
    @Resource
    OrganizationService organizationService;


    @ApiOperation(value = "获取全部机构数据")
    @GetMapping("/getAll")
    public JsonResult getAll() {
        List<OrganizationLevel1> organization = organizationService.findAll();
        return JsonResult.success().addObject("organization", organization);
    }


}
