package com.xinghaol.flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/18 4:16 下午
 * @description ConditionDemo基本用法
 */
public class ConditionDemo1 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void method1() {
        lock.lock();

        try {
            System.out.println("条件不满足，开始await");
            condition.await();
            System.out.println("条件已满足，开始执行后续的任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        lock.lock();

        try {
            System.out.println("准备工作完成，唤醒其他线程");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                conditionDemo1.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        conditionDemo1.method1();
    }
}
