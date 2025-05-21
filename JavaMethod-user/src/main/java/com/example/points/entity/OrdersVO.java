package com.example.points.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrdersVO {
    private String username;
    private List<ProductsVO> productsVOList;
}
