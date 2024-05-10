package com.thaprobit.resengine.controller;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thaprobit.resengine.controller.service.OrderService;
import com.thaprobit.resengine.dao.Orders;
import com.thaprobit.resengine.dto.MaxIdsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void testGetMaxOrderIds() {
        // Setup
        MaxIdsDto maxIdsDto = new MaxIdsDto();
        maxIdsDto.setOrderId(1L);
        maxIdsDto.setOrderChoiceSubId(2L);

        when(orderService.getMaxOrderIds()).thenReturn(maxIdsDto);

        // Execution
        MaxIdsDto result = orderController.getMaxOrderIds();

        // Verification
        assertEquals(1L, result.getOrderId());
        assertEquals(2L, result.getOrderChoiceSubId());
        verify(orderService).getMaxOrderIds();
    }

    @Test
    public void testSaveOrder() {
        Orders order = new Orders();
        order.setOrderChoices(new HashSet<>());

        doNothing().when(orderService).saveOrder(any(Orders.class));

        // Execution
        orderController.saveOrder(order);

        // Verification
        verify(orderService).saveOrder(any(Orders.class));
    }
}
