package com.example.points.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.method.result.AjaxResult;
import com.example.points.entity.CreateOrder;
import com.example.points.entity.Orders;
import com.example.points.entity.OrdersVO;
import com.example.points.mapper.OrdersMapper;
import com.example.points.service.IOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
@Api(tags = "订单表")
@RestController
@RequestMapping("/points/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;
    @Autowired
    private OrdersMapper ordersMapper;

    @ApiOperation("查询订单列表")
    @GetMapping("/getPojoList")
    public List<CreateOrder> getPojoList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("order_id","product_id");
        List list = ordersMapper.selectList(queryWrapper);
        if (list.isEmpty()){
            System.out.println("查询结果为空");
        }
        return list;
    }

    @ApiOperation("新增订单")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CreateOrder createOrder) {
        boolean save = ordersService.createOrderLogic(createOrder);
        if (save) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @ApiOperation("根据ID查询订单")
    @GetMapping("/getByID")
    public AjaxResult getByID(Long orderId) {
        Orders byId = ordersService.getById(orderId);
        if (byId != null){
            return AjaxResult.success(byId);
        }
        return AjaxResult.error();
    }

    @ApiOperation("根据条件查询订单")
    @GetMapping("/getByPojo")
    public AjaxResult getByPojo(@RequestBody Orders orders) {
        List<Orders> byId = ordersService.getByPojo(orders);
        if (byId != null){
            return AjaxResult.success(byId);
        }
        return AjaxResult.error();
    }

    @ApiOperation("根据用户ID查询积分")
    @GetMapping("/getUserPoints")
    public AjaxResult getUserPoints(Long userId) {
        LambdaQueryWrapper<Orders> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper
                .select(Orders::getProductId,Orders::getPointsEarned)
                .eq(Orders::getOrderId,userId);
        Orders orders = ordersMapper.selectList(lambdaQueryWrapper).stream().findFirst().orElse(null);
        if (orders != null){
            return AjaxResult.success(orders);
        }
        return AjaxResult.error();
    }

    @ApiOperation("查询订单详情")
    @GetMapping("/getOrdersVO")
    public AjaxResult getOrdersVO() {
        List<OrdersVO> ordersVOS = ordersService.getOrdersVO();
        if (ordersVOS != null){
            return AjaxResult.success(ordersVOS);
        }
        return AjaxResult.error();
    }
}
