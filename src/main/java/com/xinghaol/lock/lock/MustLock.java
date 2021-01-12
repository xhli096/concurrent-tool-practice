package com.xinghaol.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/1/11 7:53 下午
 * @description 必须释放锁，Lock不会像synchronized一样，异常的时候自动释放锁，所以最佳实践是，在finally中释放锁，以保证发生异常的时候锁一定被释放
 */
public class MustLock {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();

        try {
           // 获取本锁保护的资源
            System.out.println(Thread.currentThread().getName() + "开始执行任务");
        } finally {
            lock.unlock();
        }
    }
}
