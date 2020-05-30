package com.example.order.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
@TableName(value="restaurants")
public class Restaurants {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("type")
    private Integer type;

    @TableField("address")
    private String address;

}
