package com.thiago.ecommerce;

public interface OrderRepository {

    void save(Order order);

    Order findById(int id);
}
