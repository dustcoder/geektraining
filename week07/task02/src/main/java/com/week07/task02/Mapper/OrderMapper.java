package com.week07.task02.Mapper;

import com.week07.task02.model.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper {
    @Select("select * from t_mall_order where order_no = #{orderNo}")
    Order getOrder(@Param("orderNo") Integer orderNo);
}
