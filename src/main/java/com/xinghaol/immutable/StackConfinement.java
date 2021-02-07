package com.xinghaol.immutable;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/7 8:01 下午
 * @description 演示栈封闭的两种情况，基本变量和对象
 * 先演示线程争抢带来错误结果，然后把变量放到方法中，情况就变了
 */
public class StackConfinement implements Runnable {
    private int index = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
        intThread();
    }

    private void intThread() {
        int newGoOut = 0;
        for (int i = 0; i < 10000; i++) {
            newGoOut++;
        }
        System.out.println("栈内受保护的数字时线程安全的：" + newGoOut);
    }

    public static void main(String[] args) throws InterruptedException {
        StackConfinement stackConfinement = new StackConfinement();

        Thread thread1 = new Thread(stackConfinement);
        Thread thread2 = new Thread(stackConfinement);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("stackConfinement.index : " + stackConfinement.index);
    }
}
