package com.example.order.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.order.common.ListResponse;
import com.example.order.common.PlainResponse;
import com.example.order.entity.OrderDishes;
import com.example.order.entity.Orders;
import com.example.order.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Observable;

/**
 * 被观察者
 * */
@RestController
@RequestMapping("/customer")
public class CustomersController extends Observable {

    @Autowired
    private CustomersService customersService;

    private Long restaruntId;

    private static CustomersController instance = new CustomersController();

    private CustomersController (){}

    public static CustomersController getInstance() {
        return instance;
    }

    /**
     * 需求一：
     * 对于顾客，实现顾客（customer）在线基于餐厅（restaurant）的菜单（menu_dish），
     * 在线下单；对于未接单的订单，可以修改
     * */
    /**
     * 根据餐厅Id查询该餐厅所有的菜单
     * */
    @RequestMapping("/findAllMenus/{restaurantId}")
    public ListResponse<OrderDishes> getAllMenusByRestaurantId(@PathVariable Long restaurantId) {
        return customersService.getAllMenusByRestaurantId(restaurantId);
    }

    /**
     * 下订单
     * */
    @RequestMapping(value = "/addOrder",consumes = "application/json")
    public PlainResponse addOrder(@RequestBody JSONObject jsonObject) {
        //通知所有的观察者
        return customersService.addOrder(jsonObject);
    }

    /**
     * 支付订单
     * */
    @RequestMapping(value = "/payOrder/{customerId}")
    public PlainResponse payOrder(@PathVariable Long customerId) {
        List<Long> restIds = customersService.payOrder(customerId);
        //通知所有的店家（观察者）
        for(Long restId : restIds) {
            //目标对象的状态发生了改变
            instance.restaruntId = restId;
            //表示目标对象已经做了更改
            setChanged();
            //通知所有的观察者
            notifyObservers(restaruntId);
        }
        return PlainResponse.ok("订单支付成功，已通知店家接单");
    }

    /**
     * 对于未支付的订单，可以修改
     * */
    @RequestMapping(value = "/updateOrder",consumes = "application/json")
    public PlainResponse updateOrder(@RequestBody Orders orders) {
        return customersService.updateOrder(orders);
    }

    /**
     * 需求二：
     * 对于顾客，实现顾客（customer）查询自己下单，
     * 并能够查看订单详情（订单状态，总价 ，点菜集合）
     * */
    @RequestMapping("/findAllOrderInfo/{customerId}")
    public JSONArray findAllOrderInfo(@PathVariable Long customerId) {
        return customersService.findAllOrderInfo(customerId);
    }

    public Long getRestaruntId() {
        return restaruntId;
    }

    public void setRestaruntId(Long restaruntId) {
        this.restaruntId = restaruntId;
    }
}
