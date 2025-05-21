package com.example.points.service.impl;

import com.example.points.entity.UserOrder;
import com.example.points.mapper.UserOrderMapper;
import com.example.points.service.IUserOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与订单中间表 服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements IUserOrderService {

}
