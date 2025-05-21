package com.example.points.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.points.entity.Products;
import com.example.points.mapper.ProductsMapper;
import com.example.points.service.IProductsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.util.MybatisPlusUtilt;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements IProductsService {

    /**
     * 根据传入的Products对象，返回满足条件的Products列表
     *
     * @param product 要查询的Products对象
     * @return 满足条件的Products列表
     * @throws IllegalAccessException 如果字段不可访问，则抛出此异常
     */
    @Override
    public List<Products> getListByPojo(Products product) {
        MybatisPlusUtilt<Products> mybatisPlusUtilt = new MybatisPlusUtilt<>();
        QueryWrapper<Products> byPojo = mybatisPlusUtilt.getByPojo(product);
        // 执行查询并返回结果
        return list(byPojo);
    }
}
