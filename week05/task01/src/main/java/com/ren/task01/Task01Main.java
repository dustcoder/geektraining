package com.ren.task01;

import com.ren.task01.way02.UserService02;
import com.ren.task01.way03.BeanConfig;
import com.ren.task01.way03.UserService03;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task01Main {
    public static void main(String[] args) {
        //xml装配
        final ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        // 通过构造器注入 + xml
        System.out.println(classPathXmlApplicationContext.getBean("user1"));
        // 通过setter方法注入
        System.out.println(classPathXmlApplicationContext.getBean("user2"));

        // anotation装配
        final ClassPathXmlApplicationContext classPathXmlApplicationContext1 = new ClassPathXmlApplicationContext("ApplicationContext2.xml");
        final UserService02 userService02 = (UserService02) classPathXmlApplicationContext1.getBean("userService02");
        userService02.save();

        // 扫描装配
        final ClassPathXmlApplicationContext classPathXmlApplicationContext2 = new ClassPathXmlApplicationContext("ApplicationContext3.xml");
        final UserService02 userService021 = (UserService02) classPathXmlApplicationContext2.getBean("userService");
        userService021.save();

        // java config
        final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        final UserService03 userService2 = (UserService03) annotationConfigApplicationContext.getBean("userService03");
        userService2.save();
    }
}
