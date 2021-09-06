package com.sample.service;

import java.util.List;

import com.sample.entity.Order;

public interface OrderService {
    public int save(Order order);
    public Order findById(String id);
    public int delete(String id);
    public int modify(Order order);
    public List<Order> findAll();
}
