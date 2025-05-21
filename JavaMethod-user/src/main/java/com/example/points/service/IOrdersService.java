package com.example.points.service;

import com.example.points.entity.CreateOrder;
import com.example.points.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.points.entity.OrdersVO;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
public interface IOrdersService extends IService<Orders> {

    List<Orders> getByPojo(Orders orders);

    boolean createOrderLogic(CreateOrder createOrder);

    Integer getUserPointsByUserId(Integer userId);

    List<OrdersVO> getOrdersVO();
}
