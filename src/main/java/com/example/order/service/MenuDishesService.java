package com.example.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.order.entity.MenuDishes;
import com.example.order.mapper.MenuDishesDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuDishesService extends ServiceImpl<MenuDishesDao, MenuDishes>
        implements IService<MenuDishes> {



}
