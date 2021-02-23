package com.xinghaol.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/22 11:33 上午
 * @description 演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机，并不是一产生异常就抛出，直到我们
 * get方法执行时才抛出
 */
public class GetException {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Future<Integer> future = executorService.submit(new CallableTask());
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException异常");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException异常");
        }
        System.out.println(future.isDone());
        executorService.shutdown();
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("参数异常");
        }
    }
}
