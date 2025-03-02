package com.example.userrole.controller;


import com.example.userrole.entity.Role;
import com.example.userrole.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@RestController
@RequestMapping("/userrole/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    public List<Role> getList() {
        return roleService.list();
    }

    @RequestMapping("/add")
    public String addRole() {
        System.out.println("添加角色");
        Role role = new Role();
        role.setRoleName("董事长");
        roleService.save(role);
        return "添加角色成功";
    }
}
