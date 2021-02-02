package com.xinghaol.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/1/18 9:34 下午
 * @description 非公平和公平的读写锁演示
 * 公平锁，无论是读锁还是写锁，都会去看队列中是否已经存在等待的线程
 * 非公平锁，获取写锁的线程总是可以插队，获取写锁的线程会看队列中的第一个是否是获取排它锁(写锁)的线程
 */
public class UnfairBargeDemo {

    /**
     * true -》 公平, false -> 非公平
     */
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> write(), "thread1").start();
        // 当thread1执行完的一瞬间，thread2和thread3会被取出，开始读
        new Thread(() -> read(), "thread2").start();
        new Thread(() -> read(), "thread3").start();
        new Thread(() -> write(), "thread4").start();
        new Thread(() -> read(), "thread5").start();

        // 创建插队线程
        new Thread(() -> {
            Thread[] threads = new Thread[1000];
            for (int i = 0; i < 1000; i++) {
                threads[i] = new Thread(() -> read(), "子线程创建的thread" + i);
            }
            for (int i = 0; i < 1000; i++) {
                threads[i].start();
            }
        }).start();
    }
}
