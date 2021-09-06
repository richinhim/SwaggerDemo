package com.sample.repository;

import com.sample.entity.Order;

import java.util.List;

public interface OrderRepository {
    public int save(Order order);
    public Order findById(String id);
    public int delete(String id);
    public int modify(Order order);
    public List<Order> findAll();
}
