package com.xinghaol.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/9 6:00 下午
 * @description 模拟100米跑步，5名选手都准备好了，只等裁判一声令下，所有人开始跑步
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) {
        // 一次信号
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i;
            executorService.submit(() -> {
                System.out.println("No." + no + "准备完毕，等待发令枪");
                try {
                    countDownLatch.await();
                    System.out.println("No." + no + "开始跑步了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 裁判员检查发令枪
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发令枪响了，开始跑步");
        countDownLatch.countDown();
    }
}
