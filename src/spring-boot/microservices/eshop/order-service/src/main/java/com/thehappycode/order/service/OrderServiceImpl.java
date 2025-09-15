package com.thehappycode.order.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.thehappycode.order.dto.OrderRequest;
import com.thehappycode.order.model.Order;
import com.thehappycode.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = Order.builder()
            .orderNumber(UUID.randomUUID().toString())
            .skuCode(orderRequest.skuCode())
            .price(orderRequest.price())
            .quantity(orderRequest.quantity())
            .build();

        orderRepository.save(order);
    }

}


