package com.sample.mock;

import com.sample.entity.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RDBMS를 대체하기 위해 만든 임시 메모리 데이터 저장소
 */
public class OrderDB {
    private static OrderDB orderDB = null;
    private Map<String, Order> persistence = new ConcurrentHashMap<String, Order>();

    static public OrderDB getInstance() {
        if (orderDB == null) {
            orderDB = new OrderDB();
        }
        return orderDB;
    }

    public Order findById(String id) {
        return persistence.get(id);
    }

    public int save(Order order) {
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        order.setDate(format.format(time));

        persistence.put(order.getId(), order);

        return 1;
    }

    public int delete(String id) {
        persistence.remove(id);

        return 1;
    }

    public List<Order> findAll(){
        List<Order> list = new ArrayList(persistence.values());
        return list;
    }

}
