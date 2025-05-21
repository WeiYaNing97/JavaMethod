package com.example.points.service;

import com.example.points.entity.Products;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
public interface IProductsService extends IService<Products> {

    List<Products> getListByPojo(Products product);
}
