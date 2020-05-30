package com.example.order.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @RequestMapping(value = "/getPersonInfo/{age}/{name}")
    public String findPersonInfo(@PathVariable Integer age,@PathVariable String name) {
        return "个人信息" + age + "," + "姓名：" + name;
    }

}
