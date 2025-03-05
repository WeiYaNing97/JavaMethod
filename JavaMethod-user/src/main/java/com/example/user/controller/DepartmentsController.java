package com.example.user.controller;


import com.example.method.result.AjaxResult;
import com.example.user.entity.Departments;
import com.example.user.entity.Users;
import com.example.user.service.IDepartmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 部门信息表 前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Api(tags = "部门信息表")
@RestController
@RequestMapping("/user/departments")
public class DepartmentsController {
    @Autowired
    private IDepartmentsService departmentsService;
    @ApiOperation("获取部门信息列表")
    @GetMapping("/getList")
    public List<Departments> getList() {
        List<Departments> list = departmentsService.list();
        return list;
    }


    @ApiOperation("添加部门信息")
    @GetMapping("/add")
    public AjaxResult add(@RequestBody Departments departments) {
        boolean save = departmentsService.add(departments);
        return save? AjaxResult.success() : AjaxResult.error();
    }
}
