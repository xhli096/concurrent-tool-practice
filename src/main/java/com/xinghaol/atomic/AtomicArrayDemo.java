package com.xinghaol.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/2 1:01 下午
 * @description 演示原子数组的使用方法
 */
public class AtomicArrayDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);

        Thread[] threadsIncrement = new Thread[100];
        Thread[] threadsDecrement = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threadsDecrement[i] = new Thread(decrementer);
            threadsIncrement[i] = new Thread(incrementer);

            threadsIncrement[i].start();
            threadsDecrement[i].start();
        }

        // 对他们进行等待
        for (int i = 0; i < 100; i++) {
            threadsDecrement[i].join();
            threadsIncrement[i].join();
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("发现了错误，非0值， i=" + i);
            }
        }

        System.out.println("运行结束");

        B b = new B();
        b.a = 10;
        b.func(b);
        System.out.println(b.a);
    }
}

class Decrementer implements Runnable {

    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable {

    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}

class B {
    int a;

    public void func(B b) {
        b = new B();
        b.a = 20;
    }
}

