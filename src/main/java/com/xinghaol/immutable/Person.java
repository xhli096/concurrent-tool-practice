package com.xinghaol.immutable;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/6 5:33 下午
 * @description 不可变的对象，演示其他类无法修改这个对象，public也不行
 */
public class Person {
    private final int age = 18;
    private final String name = "Alice";
}
