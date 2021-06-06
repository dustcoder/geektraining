package com.ren.task01.way02;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao02Impl implements UserDao02 {
    @Override
    public void save() {
        System.out.println("useDaoImpl save ...");
    }
}
