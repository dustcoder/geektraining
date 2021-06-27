package com.week08.task01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.week08.task01.model.Order;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository
@MapperScan("com.week08.task01.mapper")
public interface OrderMapper extends BaseMapper<Order> {

}
