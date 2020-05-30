package com.example.order.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
@TableName(value="order_dishes")
public class OrderDishes {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("menu_dish_id")
    private Long menuDishId;

    @TableField("order_id")
    private Long orderId;

    @TableField("count")
    private Integer count;

}
