package com.week07.task02;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
@Order(1000)
@Slf4j
public class RoutingDataSourceAOP {

    @Pointcut("@annotation(com.week07.task02.RoutingDataSource)")
    public void routingDataSourcePointcut() {};

    @Around("routingDataSourcePointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();
        final RoutingDataSource routingDataSource = method.getAnnotation(RoutingDataSource.class);
        DataSourceAddressEnum dataSourceAddressEnum = Objects.isNull(routingDataSource) ?
                DataSourceAddressEnum.MASTER : routingDataSource.value();

        DataSourceContextHolder.setCurrentDataSource(dataSourceAddressEnum);

        try {
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.removeDataSource();
        }

    }
}
