package com.example.order.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.order.entity.Customers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomersDao extends BaseMapper<Customers> {

}