package com.ren.concurrent;

public class Method2 {

    static Integer result = 0;
    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        final MyThread myThread = new MyThread();
        myThread.start();
        synchronized (object) {
            object.wait();
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
            synchronized (object) {
                result = sum();
                object.notifyAll();
            }
        }
    }
}
