package com.week08.task01;

import com.week08.task01.mapper.OrderMapper;
import com.week08.task01.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingsphereJdbcDemoApplicationTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();

        order.setOrderNo(15);
        order.setUserId(10);

        orderMapper.insert(order);
    }

}
