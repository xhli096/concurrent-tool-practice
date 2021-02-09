package com.xinghaol.collection.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/8 12:11 下午
 * @description description
 */
public class OptionsNotSafe implements Runnable {
    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            while (true) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
//                scores.put("小明", newScore);
                boolean replace = scores.replace("小明", score, newScore);
                if (replace) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);

        Thread thread1 = new Thread(new OptionsNotSafe());
        Thread thread2 = new Thread(new OptionsNotSafe());

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(scores);
    }
}
