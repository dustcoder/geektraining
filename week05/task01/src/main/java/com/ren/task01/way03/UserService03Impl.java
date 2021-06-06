package com.ren.task01.way03;

import javax.annotation.Resource;

public class UserService03Impl implements UserService03 {

    @Resource(name = "userDao3")
    private UserDao03 userDao03;

    @Override
    public void save() {
        userDao03.save();

        System.out.println("userServiceImpl save");
    }
}
