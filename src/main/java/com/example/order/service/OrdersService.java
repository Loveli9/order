package com.example.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.order.entity.Orders;
import com.example.order.mapper.OrdersDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrdersService extends ServiceImpl<OrdersDao, Orders>
        implements IService<Orders> {

    public Long insertOrders(Orders orders){
        return this.baseMapper.insertOrders(orders);
    }

    public List<Orders> getAllRestOrdersByCusId(Long customerId){
        return this.baseMapper.getAllRestOrdersByCusId(customerId);
    }

}
