package com.thaprobit.resengine.controller.service;

import com.thaprobit.resengine.dao.Orders;
import com.thaprobit.resengine.dao.OrderChoices;
import com.thaprobit.resengine.repo.OrderRepository;
import com.thaprobit.resengine.repo.OrderChoiceRepository;
import com.thaprobit.service.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thaprobit.resengine.dto.MaxIdsDto;

import java.util.List;

@Service
@Slf4j
public class OrderService extends AbstractService<Orders> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderChoiceRepository orderChoiceRepository;
    public MaxIdsDto getMaxOrderIds() {
        List<Long[]> maxIdsList = orderRepository.findMaxOrderIds();
        MaxIdsDto maxIdsDto = new MaxIdsDto();

        if (maxIdsList != null && !maxIdsList.isEmpty() && maxIdsList.get(0).length >= 2) {
            Long[] maxIds = maxIdsList.get(0);
            maxIdsDto.setOrderId(maxIds[0]);
            maxIdsDto.setOrderChoiceSubId(maxIds[1]);
        } else {
            log.warn("Unexpected result from findMaxOrderIds(): {}", maxIdsList);
        }
        return maxIdsDto;
    }
    public void saveOrder(Orders order) {

        orderRepository.save(order);

        for (OrderChoices choice : order.getOrderChoices()) {
            choice.setOrder(order);
            orderChoiceRepository.save(choice);
        }
    }
}
