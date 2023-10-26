package com.woniuxy.cxy.service.impl;

import com.woniuxy.cxy.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    IOrderService orderService;

    @Test
    void countItemByOrderId() {
        Long count = orderService.countItemByOrderId(8741754875894457696L);
        System.out.println("count = " + count);
    }
}