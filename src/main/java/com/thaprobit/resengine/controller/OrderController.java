package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.controller.service.OrderService;
import com.thaprobit.resengine.dao.Orders;
import com.thaprobit.resengine.dto.MaxIdsDto;
import com.thaprobit.util.URLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLProvider.SERVICE_RESERVATION)
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders/maxIds")
    public MaxIdsDto getMaxOrderIds() {
        return orderService.getMaxOrderIds();
    }

    @PostMapping("/orders")
    public void saveOrder(@RequestBody Orders order) {
        orderService.saveOrder(order);
    }
}
