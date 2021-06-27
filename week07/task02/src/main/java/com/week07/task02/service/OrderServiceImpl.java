package com.week07.task02.service;

import com.week07.task02.DataSourceAddressEnum;
import com.week07.task02.Mapper.OrderMapper;
import com.week07.task02.RoutingDataSource;
import com.week07.task02.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @RoutingDataSource(DataSourceAddressEnum.MASTER)
    public Order getOrder(Integer orderNo) {
        return orderMapper.getOrder(orderNo);
    }

    @Override
    @RoutingDataSource(DataSourceAddressEnum.SLAVE)
    public Order getOrderFromSlave(Integer orderNo) {
        return orderMapper.getOrder(orderNo);
    }
}
