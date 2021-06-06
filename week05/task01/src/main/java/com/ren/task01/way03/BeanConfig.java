package com.ren.task01.way03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserDao03 userDao3() {
        System.out.println("创建userdao3...");
        return new UserDao03Impl();
    }

    @Bean
    public UserService03 userService03() {
        System.out.println("创建userservice03...");
        return new UserService03Impl();
    }
}
