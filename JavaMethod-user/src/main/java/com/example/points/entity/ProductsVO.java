package com.example.points.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-04-10
 */
@Data
public class ProductsVO implements Serializable {
    private String productName;
    private String productDescription;
    private Integer quantity;
    private Integer pointsEarned;
    private Boolean isDeleted;
}
