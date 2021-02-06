package com.xinghaol.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/6 2:12 下午
 * @description 演示LongAccumulatorDemo的用法
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) {
        // identity是对x的第一次定义
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 11);
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        IntStream.range(1, 10).forEach(i -> executorService.submit(() -> accumulator.accumulate(i)));
        System.out.println(accumulator.getThenReset());
    }
}
