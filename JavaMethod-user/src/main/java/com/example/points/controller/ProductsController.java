package com.example.points.controller;


import com.example.method.result.AjaxResult;
import com.example.points.entity.Products;
import com.example.points.service.IProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
@Api(tags = "商品表")
@RestController
@RequestMapping("/points/products")
public class ProductsController {
    @Autowired
    private IProductsService productsService;

    @ApiOperation("添加商品信息")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Products product) {
        boolean save = productsService.save(product);
        if (save) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @ApiOperation("根据ID获取商品信息")
    @GetMapping("/get")
    public AjaxResult get(@RequestBody Products product) {
        Products byId = productsService.getById(product);
        if (byId != null) {
            return AjaxResult.success(byId);
        }
        return AjaxResult.error();
    }
    @ApiOperation("根据实体类获取商品列表")
    @GetMapping("/getListByPojo")
    public AjaxResult getListByPojo(@RequestBody Products product) {
        List<Products> getList = productsService.getListByPojo(product);
        if (getList.size() > 0) {
            return AjaxResult.success(getList);
        }
        return AjaxResult.error();
    }
}
