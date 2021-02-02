package com.xinghaol.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/1/17 10:07 下午
 * @description description
 */
public class BookSeat {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(BookSeat::bookSeat).start();
        new Thread(BookSeat::bookSeat).start();
        new Thread(BookSeat::bookSeat).start();
        new Thread(BookSeat::bookSeat).start();
    }

    private static void bookSeat() {
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "预定电影票座位");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "完成电影票座位预定");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
