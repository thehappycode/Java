package com.thehappycode.order.service;

import com.thehappycode.order.dto.OrderRequest;

public interface OrderService {
    /** 
     * Nơi order
     * @param orderRequest
    */
    void placeOrder(OrderRequest orderRequest);
}


