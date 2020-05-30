package com.example.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.order.entity.OrderDishes;
import com.example.order.mapper.OrderDishesDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderDishesService extends ServiceImpl<OrderDishesDao, OrderDishes>
        implements IService<OrderDishes> {

    public List<OrderDishes> findMenusByRestaurantId(Long restaurantId) {
        return this.baseMapper.findMenusByRestaurantId(restaurantId);
    }

}
