package com.thiago.ecommerce;

import java.util.HashMap;
import java.util.Map;

public class RealOrderRepository implements OrderRepository {
    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Order findById(int id) {
        return orders.get(id);
    }
}
