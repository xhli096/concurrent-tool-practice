package com.xinghaol.cas;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/6 2:28 下午
 * @description 模拟CAS操作，等价代码
 */
public class TwoThreadCompetition implements Runnable {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadCompetition twoThreadCompetition = new TwoThreadCompetition();
        twoThreadCompetition.value = 0;

        Thread thread1 = new Thread(twoThreadCompetition, "Thread1");
        Thread thread2 = new Thread(twoThreadCompetition, "Thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(twoThreadCompetition.value);
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }
}
