package com.example.order.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.order.entity.OrderDishes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface OrderDishesDao extends BaseMapper<OrderDishes> {

    @Select("select od.* from orders ord " +
            "left join order_dishes od " +
            "on ord.id = od.order_id " +
            "where ord.restarunt_id = #{restaurantId}")
    List<OrderDishes> findMenusByRestaurantId(@Param("restaurantId") Long restaurantId);

}
