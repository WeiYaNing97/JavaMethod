package com.example.points.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.points.entity.*;
import com.example.points.mapper.OrdersMapper;
import com.example.points.mapper.ProductsMapper;
import com.example.points.mapper.UserOrderMapper;
import com.example.points.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.util.MybatisPlusUtilt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    ProductsMapper productsMapper;
    @Autowired
    UserOrderMapper userOrderMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> getByPojo(Orders orders) {
        MybatisPlusUtilt<Orders> mybatisPlusUtilt = new MybatisPlusUtilt<>();
        QueryWrapper<Orders> byPojo = mybatisPlusUtilt.getByPojo(orders);
        List<Orders> list = list(byPojo);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrderLogic(CreateOrder createOrder) {
        for (Integer productId:createOrder.getProductIds()){
            /*根据商品ID获取商品信息*/
            Products products = productsMapper.selectById(productId);
            if (products == null) {
                return false;
            }
            /*编辑订单信息，插入订单数据*/
            Orders orders = new Orders();
            orders.setProductId(products.getProductId());
            orders.setQuantity(createOrder.getQuantity());
            orders.setPointsEarned(products.getPointsRequired());
            boolean save = save(orders);
            if (!save) {
                return false;
            }
            /*编辑用户订单中间表，插入数据*/
            UserOrder userOrder = new UserOrder();
            userOrder.setUserId(Long.valueOf(createOrder.getUserId()));
            userOrder.setOrderId(orders.getOrderId());
            int insert = userOrderMapper.insert(userOrder);
            if (insert <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer getUserPointsByUserId(Integer userId) {
        Integer points =  ordersMapper.selectUserPointsByUserId(userId);
        return points;
    }

    @Override
    public List<OrdersVO> getOrdersVO() {
        return ordersMapper.selectOrdersVO();
    }
}
