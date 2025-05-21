package com.example.points.mapper;

import com.example.points.entity.UserOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户与订单中间表 Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
@Mapper
public interface UserOrderMapper extends BaseMapper<UserOrder> {

}
