package com.ren.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Method3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        final FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });

        final Thread thread = new Thread(integerFutureTask);
        thread.start();

        System.out.println("异步计算结果为：" + integerFutureTask.get());

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
}
