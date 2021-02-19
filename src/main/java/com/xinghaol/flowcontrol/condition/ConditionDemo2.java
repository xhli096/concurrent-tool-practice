package com.xinghaol.flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/18 11:12 下午
 * @description 演示用Condition实现生产者消费者模式
 */
public class ConditionDemo2 {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();

                try {
                    while (queue.size() == 0) {
                        System.out.println("队列空，等待数据");
                        notEmpty.await();
                    }
                    // 拿出一个数据
                    queue.poll();
                    notFull.signalAll();
                    System.out.println("从队列中取走一个数据，队列中剩余" + queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Produce extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();

                try {
                    while (queue.size() == queueSize) {
                        System.out.println("队列满，等待有空余");
                        notFull.await();
                    }
                    // 拿出一个数据
                    queue.offer(1);
                    notEmpty.signalAll();
                    System.out.println("向队列中插入数据，队列中剩余空间" + (queueSize - queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ConditionDemo2 conditionDemo2 = new ConditionDemo2();
        Produce produce = conditionDemo2.new Produce();
        Consumer consumer = conditionDemo2.new Consumer();
        produce.start();
        consumer.start();
    }
}
