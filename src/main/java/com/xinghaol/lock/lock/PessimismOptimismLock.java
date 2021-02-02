package com.xinghaol.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/1/13 11:24 上午
 * @description description
 */
public class PessimismOptimismLock {

    /**
     * 悲观锁的一种实现
     */
    public synchronized void testMethod() {

    }

    /**
     * 乐观锁
     *
     * @param args
     */
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
}
