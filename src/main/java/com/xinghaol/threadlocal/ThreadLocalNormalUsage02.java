package com.xinghaol.threadlocal;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2020/11/28 11:03 下午
 * @description description
 */
public class ThreadLocalNormalUsage02 {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            executorService.execute(
                    () -> {
                        String date = new ThreadLocalNormalUsage02().dateFormat(finalI * 100 + 10002332);
                        System.out.println(date);
                    });
        }
    }

    private String dateFormat(int milliSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int date = 1000 * milliSeconds;

        return simpleDateFormat.format(date);
    }

}
