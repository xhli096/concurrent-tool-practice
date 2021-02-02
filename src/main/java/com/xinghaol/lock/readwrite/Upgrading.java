package com.xinghaol.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/1/18 9:22 下午
 * @description description
 */
public class Upgrading {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void readUpgrading() {
        readLock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
            Thread.sleep(1000);
            System.out.println("升级会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "得到了写锁，升级成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void wirteDowngrading() {
        writeLock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在写入");
            Thread.sleep(1000);
            System.out.println("降级中");
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "在不释放写锁的情况下，直接获取读锁，成功降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("先演示降级时可以的");
        Thread thread = new Thread(() -> wirteDowngrading(), "wirteDowngradingThread");
        thread.start();
        thread.join();

        System.out.println("----------------------------------------------------------");
        System.out.println("演示升级是不行的");
        new Thread(() -> readUpgrading(), "readUpgradingThread").start();
    }
}
