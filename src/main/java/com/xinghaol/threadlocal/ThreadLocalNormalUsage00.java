package com.xinghaol.threadlocal;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2020/11/28 11:03 下午
 * @description description
 */
public class ThreadLocalNormalUsage00 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                String date = new ThreadLocalNormalUsage00().dateFormat(10002332);
                System.out.println(date);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                String date = new ThreadLocalNormalUsage00().dateFormat(10021);
                System.out.println(date);
            }
        }).start();
    }

    private String dateFormat(int milliSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        int date = 1000 * milliSeconds;

        return simpleDateFormat.format(date);
    }

}
