package com.example.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.order.common.PlainResponse;
import com.example.order.controller.CustomersController;
import com.example.order.entity.Orders;
import com.example.order.entity.Restaurants;
import com.example.order.mapper.RestaurantsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Service
@Transactional
public class RestaurantsService extends ServiceImpl<RestaurantsDao, Restaurants>
        implements IService<Restaurants>, Observer {

    @Autowired
    private OrdersService ordersService;

    /**
     * 消息订阅，自动接单
     * */
    public PlainResponse findOrdersByRestaruntId(Long customerId) {
        List<Orders> restaruntOrders = ordersService.getAllRestOrdersByCusId(customerId);
        restaruntOrders.forEach(
                restaruntOrder -> {
                    if(3 == restaruntOrder.getStatus()) {
                        restaruntOrder.setStatus(1);
                        ordersService.updateById(restaruntOrder);
                    }
                }
        );
        return PlainResponse.ok("店家接单成功");
    }

    public PlainResponse addRestaurant(Restaurants restaurants) {
        PlainResponse out = PlainResponse.ok(this.baseMapper.insert(restaurants));
        CustomersController.getInstance().addObserver(new RestaurantsService());
        return out;
    }

    /**
     * 需求三：
     * 对于店家，实现店家查询订单，并接单。
     * */
    @Override
    public void update(Observable o, Object arg) {
        Long restaruntId = ((CustomersController)o).getRestaruntId();
        this.findOrdersByRestaruntId(restaruntId);
    }
}
