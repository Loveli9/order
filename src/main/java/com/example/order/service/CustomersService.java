package com.example.order.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.order.common.ListResponse;
import com.example.order.common.PageResponse;
import com.example.order.common.PlainResponse;
import com.example.order.entity.Customers;
import com.example.order.entity.MenuDishes;
import com.example.order.entity.OrderDishes;
import com.example.order.entity.Orders;
import com.example.order.mapper.CustomersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomersService extends ServiceImpl<CustomersDao, Customers>
        implements IService<Customers> {

    @Autowired
    private RestaurantsService restaurantsService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrderDishesService orderDishesService;
    @Autowired
    private MenuDishesService menuDishesService;

    /**
     * 根据餐厅Id查询该餐厅所有的菜单
     * */
    public ListResponse<OrderDishes> getAllMenusByRestaurantId(Long restaurantId) {
        return PageResponse.ok(orderDishesService.findMenusByRestaurantId(restaurantId));
    }

    public List<Long> payOrder(Long customerId) {
        Wrapper<Orders> wrapper = new EntityWrapper<>();
        wrapper.eq("customer_id", customerId);
        wrapper.eq("status", 0);
        List<Orders> customerOrders = ordersService.selectList(wrapper);
        List<Long> restaruntIds = new ArrayList<>();
        customerOrders.forEach(
                customerOrder -> {
                    customerOrder.setStatus(3);
                    restaruntIds.add(customerOrder.getRestaruntId());
                }
        );
        ordersService.updateBatchById(customerOrders);
        return restaruntIds;
    }
    public PlainResponse addOrder(JSONObject jsonObject) {
        try {
            Long customerId = jsonObject.getLong("customerId");
            Long restaurantId = jsonObject.getLong("restaurantId");
            JSONArray jsonArray = jsonObject.getJSONArray("dishes");
            Orders orders = new Orders();
            orders.setCreatedOn(LocalDateTime.now());
            orders.setCustomerId(customerId);
            orders.setRestaruntId(restaurantId);
            orders.setStatus(0);
            //下订单
            Long orderId = ordersService.insertOrders(orders);
            for(int i=0;i<jsonArray.size();i++) {
                OrderDishes orderDishes = new OrderDishes();
                JSONObject jsonObj= jsonArray.getJSONObject(i);
                //点菜ID
                orderDishes.setMenuDishId(jsonObj.getLong("menuDishId"));
                orderDishes.setOrderId(orderId);
                //点菜数量
                orderDishes.setCount(jsonObj.getInteger("count"));
                //点菜
                orderDishesService.insert(orderDishes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PlainResponse.fail("订单提交失败");
        }
        return PlainResponse.fail("订单提交成功");
    }

    public PlainResponse updateOrder(Orders orders) {
        if(orders.getStatus() > 0) {
            return PlainResponse.fail("订单已经支付，不能修改");
        }
        ordersService.updateById(orders);
        return PlainResponse.ok("订单已修改");
    }

    /**
     * 查询用户所有的订单信息
     * */
    public JSONArray findAllOrderInfo(Long customerId) {
        JSONArray out = new JSONArray();
        Wrapper<Orders> wrapper = new EntityWrapper<>();
        wrapper.eq("customer_id", customerId);
        List<Orders> customerOrders = ordersService.selectList(wrapper);
        customerOrders.forEach(
                customerOrder -> {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id",customerOrder.getId());
                    jsonObject.put("createdOn",customerOrder.getCreatedOn());
                    jsonObject.put("restaruntId",customerOrder.getRestaruntId());
                    jsonObject.put("status",getStatusName(customerOrder.getStatus()));
                    jsonObject.put("totalPrice",calculatePrice(customerOrder.getId()));
                    jsonObject.put("dishNames",getMenuDishes(customerOrder.getId()));
                    out.add(jsonObject);
                }
        );
        return out;
    }

    /**
     * 查询点菜集和
     * */
    private List<String> getMenuDishes(Long orderId) {
        List<String> dishNames = new ArrayList<>();
        List<OrderDishes> orderDishes = getAllOrderDishes(orderId);
        orderDishes.forEach(
                orderDish -> {
                    MenuDishes menuDishes = menuDishesService.selectById(orderDish.getMenuDishId());
                    String name = menuDishes.getName();
                    dishNames.add(name);
                }
        );
        return dishNames;
    }

    /**
     * 计算订单总价
     * */
    private Integer calculatePrice(Long orderId) {
        List<OrderDishes> orderDishes = getAllOrderDishes(orderId);
        List<Integer> dishTotalPriceList = new ArrayList<>();
        orderDishes.forEach(
                orderDish -> {
                    Integer count = orderDish.getCount();
                    MenuDishes menuDishes = menuDishesService.selectById(orderDish.getMenuDishId());
                    Integer price = menuDishes.getPrice();
                    Integer dishPrice = price * count;
                    dishTotalPriceList.add(dishPrice);
                }
        );
        //总价
        return dishTotalPriceList.stream().reduce(Integer::sum).get();
    }

    private List<OrderDishes> getAllOrderDishes(Long orderId) {
        Wrapper<OrderDishes> wrapper = new EntityWrapper<>();
        wrapper.eq("order_id", orderId);
        return orderDishesService.selectList(wrapper);
    }

    private String getStatusName(int status) {
        String result = "";
        switch (status) {
            case 1:
                result = "提交";
                break;
            case 2:
                result = "已接单";
                break;
            case 3:
                result = "已送达";
                break;
            default :
                result = "已支付";
                break;
        }
        return result;
    }

}
