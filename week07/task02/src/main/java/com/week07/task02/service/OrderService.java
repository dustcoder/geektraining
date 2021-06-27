package com.week07.task02.service;

import com.week07.task02.model.Order;

public interface OrderService {
    Order getOrder(Integer orderNo);

    Order getOrderFromSlave(Integer orderNo);
}
