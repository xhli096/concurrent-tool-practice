package com.xinghaol.threadlocal;

import java.text.SimpleDateFormat;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2020/11/28 11:03 下午
 * @description description
 */
public class ThreadLocalNormalUsage01 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    String date = new ThreadLocalNormalUsage01().dateFormat(finalI * 10 + 10002332);
                    System.out.println(date);
                }
            }).start();
        }
    }

    private String dateFormat(int milliSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int date = 1000 * milliSeconds;

        return simpleDateFormat.format(date);
    }

}
