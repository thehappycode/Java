package com.thehappycode.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thehappycode.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}


