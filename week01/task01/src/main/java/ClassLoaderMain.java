package com.geek.week01.task01;

import java.lang.reflect.InvocationTargetException;

public class ClassLoaderMain {
    public static void main(String[] args) {
        String fileName = "Hello";
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> aClass = null;
        try {
            aClass = customClassLoader.loadClass(fileName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object instance = null;
        try {
            instance = aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            aClass.getMethod("hello", null).invoke(instance, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
