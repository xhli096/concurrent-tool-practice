package com.xinghaol.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/1/17 10:13 下午
 * @description description
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        new ReentrantLockDemo().init();
    }

    private void init() {
        final Outer outer = new Outer();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outer.outer("悟空");
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("大师兄");
            }
        }).start();
    }

    static class Outer {
        private Lock lock = new ReentrantLock();

        public void outer(String name) {
            int lenght = name.length();
            lock.lock();

            try {
                for (int i = 0; i < lenght; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println("");
            } finally {
                lock.unlock();
            }
        }
    }
}
