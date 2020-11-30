package com.xinghaol.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2020/11/28 11:03 下午
 * @description description
 */
public class ThreadLocalNormalUsage03 {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Set<String> set = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            executorService.execute(
                    () -> {
                        String date = new ThreadLocalNormalUsage03().dateFormat(finalI * 100 + 10002332);
                        set.add(date);
                        System.out.println(date + "  " + set.size());
                    });
        }
        executorService.shutdown();
    }

    private String dateFormat(int milliSeconds) {
        int date = 1000 * milliSeconds;

        return simpleDateFormat.format(date);
    }

}
