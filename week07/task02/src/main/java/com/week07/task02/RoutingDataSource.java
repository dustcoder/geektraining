package com.week07.task02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RoutingDataSource {

    DataSourceAddressEnum  value() default DataSourceAddressEnum.MASTER;

}
