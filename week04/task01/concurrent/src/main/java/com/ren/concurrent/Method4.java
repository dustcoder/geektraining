package com.ren.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Method4 {

    static Integer result = 0;
    static final ReentrantLock lock = new ReentrantLock();
    static final Condition resultCompleted = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        new MyThread().start();
        Thread.sleep(1000);

        try {
            lock.lock();
        } finally {
            lock.unlock();
        }

        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间"  + (System.currentTimeMillis() - start) + "ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }

        return fibo(a -1) + fibo(a -2);
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            lock.lock();

            try {
                result = sum();
                resultCompleted.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
