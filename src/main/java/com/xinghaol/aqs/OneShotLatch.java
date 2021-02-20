package com.xinghaol.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/20 2:36 下午
 * @description 自己用AQS实现一个简单的线程协作器
 */
public class OneShotLatch {

    private final Sync sync = new Sync();

    /**
     * 一次性的门闩
     */
    public void await() {
        sync.acquireShared(0);
    }

    public void signal() {
        sync.releaseShared(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {

        /**
         * state == 1，门闩被打开了，就返回1，放行就OK了
         *
         * @param arg
         *
         * @return
         */
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败则等待");
                oneShotLatch.await();
                System.out.println("开闸放行" + Thread.currentThread().getName() + "继续运行");
            }).start();
        }
        Thread.sleep(5000);
        oneShotLatch.signal();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败则等待");
            oneShotLatch.await();
            System.out.println("开闸放行" + Thread.currentThread().getName() + "继续运行");
        }).start();
    }
}
