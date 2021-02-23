package com.xinghaol.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/22 11:49 上午
 * @description 演示get的超时方法，需要注意超时处理，调用future.cancel()，演示cancel传入true和false的区别。
 * 代表是否中断正在执行的任务。
 */
public class GetTimeout {

    private static final Ad DEFAULT_AD = new Ad("无广告时的默认广告");

    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    static class Ad {

        private String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" + "name='" + name + '\'' + '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep 期间被中断了");
                return new Ad("被中断时的默认广告");
            }
            return new Ad("高途课堂");
        }
    }

    public void printAd() {
        Future<Ad> future = exec.submit(new FetchAdTask());
        Ad ad;

        try {
            ad = future.get(2000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("发生异常时的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时时的默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = future.cancel(false);
            System.out.println("cancel = " + cancel);
        }
        exec.shutdown();
        System.out.println(ad);
    }
}
