package com.xinghaol.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/2 7:13 下午
 * @description 演示AtomicIntegerFieldUpdater的用法
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {
    static Candidate tom;
    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score++;
            scoreUpdater.getAndIncrement(tom);
        }
    }

    public static class Candidate {
        volatile int score;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFieldUpdaterDemo atomicIntegerFieldUpdaterDemo = new AtomicIntegerFieldUpdaterDemo();
        tom = new Candidate();
        peter = new Candidate();

        Thread thread1 = new Thread(atomicIntegerFieldUpdaterDemo);
        Thread thread2 = new Thread(atomicIntegerFieldUpdaterDemo);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("原子类的结果：" + tom.score);
        System.out.println("普通类的结果：" + peter.score);
    }
}
