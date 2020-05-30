package com.example.order;

import com.example.order.controller.Hello;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderApplicationTests {

    @Autowired
    private Hello hello;

    @Test
    void contextLoads() {
    }

    @Test
    public void testHello() {
        String result = hello.findPersonInfo(77,"zhangsan");
        System.out.println(result);
//        Assert.doesNotContain("个人信息77,姓名：zhangsan",result,"123");
    }

}
