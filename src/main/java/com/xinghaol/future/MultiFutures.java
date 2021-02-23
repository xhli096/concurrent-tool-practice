package com.xinghaol.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/22 11:22 上午
 * @description 演示批量操作时，用list来批量接收结果
 */
public class MultiFutures {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future<Integer> submit = executorService.submit(new CallableTask());
            futureList.add(submit);
        }
        Thread.sleep(5000);
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = futureList.get(i);
            try {
                Integer integer = future.get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);

            return new Random().nextInt();
        }
    }
}
