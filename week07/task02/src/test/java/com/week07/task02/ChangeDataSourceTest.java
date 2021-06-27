package com.week07.task02;

import com.week07.task02.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeDataSourceTest {

    @Autowired
    OrderService orderService;

    @Test
    public void test() {
        // todo ch
        Assert.assertNotNull(orderService.getOrder(1250169039));
        Assert.assertNull(orderService.getOrderFromSlave(1250169039));
    }

}
