package com.xinghaol.aqs;

import java.util.concurrent.Semaphore;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/19 3:13 下午
 * @description description
 */
public class AQSDemo {
    Semaphore semaphore = new Semaphore(10, true);
}
