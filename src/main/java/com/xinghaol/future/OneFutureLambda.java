package com.xinghaol.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/21 10:36 下午
 * @description 演示一个Future的用法,lambda形式
 */
public class OneFutureLambda {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);

            return new Random().nextInt();
        };

        Future<Integer> future = executorService.submit(callable);
        try {
            System.out.println("future.get() = " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
