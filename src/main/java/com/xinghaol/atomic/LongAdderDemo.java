package com.xinghaol.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/6 11:38 上午
 * @description 演示高并发场景下，LongAdder的性能比AtomicLong好
 */
public class LongAdderDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAdder counter = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Task(counter));
        }
        /* Thread.sleep(10000); */
        executorService.shutdown();
        // 在线程池中的任务没有全部执行完时，就在这里循环
        while (!executorService.isTerminated()) {}
        long end = System.currentTimeMillis();

        System.out.println(counter.sum());
        System.out.println("LongAdder耗时：" + (end - start));
    }

    private static class Task implements Runnable {
        private LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }
}
