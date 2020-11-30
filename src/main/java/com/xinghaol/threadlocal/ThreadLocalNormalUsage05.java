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
 * @description 利用threadLocal，给每个线程分配自己的simpleFormat对象，保证线程安全，高效利用内存
 */
public class ThreadLocalNormalUsage05 {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static Set<String> set = Collections.synchronizedSet(new HashSet<>());


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            executorService.submit(
                    () -> {
                        String date = new ThreadLocalNormalUsage05().dateFormat(finalI * 100 + 10002332);
                        set.add(date);
                        System.out.println(date + "  " + set.size());
                    });
        }
        executorService.shutdown();
    }

    private String dateFormat(int milliSeconds) {
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        int date = 1000 * milliSeconds;
        return simpleDateFormat.format(date);
    }

}

class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
