package com.example.points.mapper;

import com.example.points.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.points.entity.OrdersVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    Integer selectUserPointsByUserId(@Param("userId") Integer userId);
    List<OrdersVO> selectOrdersVO();
}
