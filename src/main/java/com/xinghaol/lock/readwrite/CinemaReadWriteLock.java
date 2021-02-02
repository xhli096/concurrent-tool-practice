package com.xinghaol.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/1/18 9:22 下午
 * @description description
 */
public class CinemaReadWriteLock {

    /**
     * 非公平锁，写锁可以插队，读锁不可以插队
     */
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read() {
        readLock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void wirte() {
        writeLock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> wirte(), "thread1").start();
        new Thread(() -> read(), "thread2").start();
        new Thread(() -> read(), "thread3").start();
        new Thread(() -> wirte(), "thread4").start();
        new Thread(() -> read(), "thread5").start();
    }
}
