package com.xinghaol.threadpool;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2020/11/23 8:24 下午
 * @description description
 */
public class ForLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }

    static class Task implements Runnable {
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
