package com.xinghaol.threadpool;

import java.util.concurrent.*;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2020/11/25 11:08 下午
 * @description description
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        // executorService.schedule(new Task(), 5, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    }
}
