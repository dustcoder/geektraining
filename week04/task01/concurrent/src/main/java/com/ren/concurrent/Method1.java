package com.ren.concurrent;

public class Method1 {

    private static Integer result = 0;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        final MyThread thread = new MyThread();
        thread.start();
        thread.join();

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
            synchronized (result) {
                result = sum();
            }
        }
    }

}
