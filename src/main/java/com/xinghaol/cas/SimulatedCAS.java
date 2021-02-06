package com.xinghaol.cas;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/6 2:28 下午
 * @description 模拟CAS操作，等价代码
 */
public class SimulatedCAS {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

}
