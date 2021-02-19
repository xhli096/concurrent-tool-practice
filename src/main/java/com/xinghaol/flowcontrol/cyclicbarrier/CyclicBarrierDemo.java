package com.xinghaol.flowcontrol.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/19 10:54 上午
 * @description description
 */
public class CyclicBarrierDemo {
     static class Task implements Runnable {
         private int id;
         private CyclicBarrier cyclicBarrier;

         public Task(int id, CyclicBarrier cyclicBarrier) {
             this.id = id;
             this.cyclicBarrier = cyclicBarrier;
         }

         @Override
         public void run() {
             System.out.println("线程" + id + "现在前往集合地点");
             try {
                 Thread.sleep((long) (Math.random() * 10000));
                 System.out.println("线程" + id + "到了集合地点，开始等待其他人到达");

                 cyclicBarrier.await();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             } catch (BrokenBarrierException e) {
                 e.printStackTrace();
             }
         }
     }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("所有人都到场了，大家一起出发");
        });

        for (int i = 0; i < 5; i++) {
            new Thread(new Task(i, cyclicBarrier)).start();
        }
    }
}
