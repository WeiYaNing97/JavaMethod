package com.example.user.controller;


import com.example.method.result.AjaxResult;
import com.example.user.entity.Departments;
import com.example.user.entity.Users;
import com.example.user.service.IDepartmentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public AjaxResult getList() {
        List<Departments> list = departmentsService.list();
        return AjaxResult.success(list);
    }


    @ApiOperation("添加部门信息")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Departments departments) {
        boolean save = departmentsService.add(departments);
        return save? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("删除部门信息")
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable("id") Integer id) {
        boolean delete = departmentsService.removeById(id);
        return delete? AjaxResult.success() : AjaxResult.error();
    }
}
