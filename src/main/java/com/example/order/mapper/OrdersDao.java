package com.example.order.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.order.entity.Orders;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface OrdersDao extends BaseMapper<Orders> {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO orders(created_on,customer_id,restarunt_id,status) " +
            "VALUE(#{orders.created_on},#{orders.customer_id}," +
            "#{orders.restarunt_id},#{orders.status})")
    Long insertOrders(@Param("orders") Orders orders);

    @Select("select ord.* from orders ord " +
            "LEFT JOIN restaurants rest " +
            "on ord.restarunt_id = rest.address " +
            "where ord.customer_id = #{customerId}")
    List<Orders> getAllRestOrdersByCusId(@Param("customerId") Long customerId);

}
