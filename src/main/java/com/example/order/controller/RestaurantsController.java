package com.example.order.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.example.order.common.ListResponse;
import com.example.order.common.PlainResponse;
import com.example.order.entity.Restaurants;
import com.example.order.service.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

/**
 * 观察者
 * */
@RestController
@RequestMapping("/customer")
public class RestaurantsController {

    private Restaurants restaurants;

    @Autowired
    private RestaurantsService restaurantsService;

    /**
     * 新增店家
     * */
    @RequestMapping(value = "/addRestaurant",consumes = "application/json")
    public PlainResponse addRestaurant(@RequestBody Restaurants restaurants) {
        return restaurantsService.addRestaurant(restaurants);
    }

    /**
     * 查询所有店家
     * */
    @RequestMapping("/findAllRestaurants")
    public ListResponse<Restaurants> findAllRestaurants() {
        return ListResponse.ok(restaurantsService.selectList(Condition.create()
                .orderAsc(Arrays.asList(new String[]{"type"}))));

    }

}
