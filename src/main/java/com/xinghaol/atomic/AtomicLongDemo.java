package com.xinghaol.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/6 11:38 上午
 * @description 演示高并发场景下，LongAdder的性能比AtomicLong好
 */
public class AtomicLongDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong counter = new AtomicLong(0);
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

        System.out.println(counter.get());
        System.out.println("AtomicLong耗时：" + (end - start));
    }

    private static class Task implements Runnable {
        private AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }
}
