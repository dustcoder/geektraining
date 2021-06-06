package com.ren.task01.way02;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserService02Impl implements UserService02 {

    @Resource(name = "userDao")
    private UserDao02 userDao02;

    @Override
    public void save() {
        userDao02.save();

        System.out.println("userServiceImpl save");
    }
}
