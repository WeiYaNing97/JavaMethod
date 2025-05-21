package com.example.points.entity;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrder {
    private Integer userId;
    private List<Integer> productIds;
    private Integer quantity;
}
