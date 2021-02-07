package com.xinghaol.immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/7 7:48 下午
 * @description 一个属性是对象，但是整体还是可以不可变。
 */
public class ImmutableDemo {
    private final Set<String> students = new HashSet<>();

    public ImmutableDemo() {
        students.add("李晓梅");
        students.add("王庄");
        students.add("徐福记");
    }

    public boolean isStudent(String name) {
        return students.contains(name);
    }
}
