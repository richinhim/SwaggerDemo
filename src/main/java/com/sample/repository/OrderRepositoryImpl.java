package com.sample.repository;

import com.sample.entity.Order;
import com.sample.mock.OrderDB;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private OrderDB persistence = OrderDB.getInstance();

    @Override
    public int save(Order order) {
        if (findById(order.getId()) != null) {
            return 0; // 이미 등록된 주문 번호면 에러 처리
        }
        return persistence.save(order);
    }

    @Override
    public Order findById(String id) {
        return persistence.findById(id);
    }

    @Override
    public int delete(String id) {
        if (findById(id) == null) return 0; // 삭제할게 없다면 에러처리

        return persistence.delete(id);
    }

    @Override
    public int modify(Order order) {
        if (findById(order.getId()) == null) return 0;

        return persistence.save(order);
    }

    @Override
    public List<Order> findAll() {
        return persistence.findAll();
    }

}
