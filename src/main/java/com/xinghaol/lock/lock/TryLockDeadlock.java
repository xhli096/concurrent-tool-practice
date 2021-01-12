package com.xinghaol.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/1/11 8:11 下午
 * @description 用tryLock来避免死锁
 */
public class TryLockDeadlock implements Runnable {
    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("线程1获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println("线程1获取到了锁2");
                                    System.out.println("线程1成功获取到了两把锁");
                                } finally {
                                    lock2.unlock();
                                }
                            } else {
                                System.out.println("线程1获取锁2失败，已重试");
                            }
                        } finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println("线程1获取锁失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (flag == 2) {
            try {
                if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("线程2获取到了锁2");
                        Thread.sleep(new Random().nextInt(2000));
                        if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("线程2获取到了锁2");
                                System.out.println("线程2成功获取到了两把锁");
                            } finally {
                                lock2.unlock();
                            }
                        } else {
                            System.out.println("线程2获取锁2失败，已重试");
                        }
                    } finally {
                        lock2.unlock();
                        Thread.sleep(new Random().nextInt(2000));
                    }
                } else {
                    System.out.println("线程2获取锁失败，已重试");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TryLockDeadlock lockDeadlock1 = new TryLockDeadlock();
        TryLockDeadlock lockDeadlock2 = new TryLockDeadlock();
        lockDeadlock1.flag = 1;
        lockDeadlock2.flag = 2;
        new Thread(lockDeadlock1).start();
        new Thread(lockDeadlock2).start();
    }
}
