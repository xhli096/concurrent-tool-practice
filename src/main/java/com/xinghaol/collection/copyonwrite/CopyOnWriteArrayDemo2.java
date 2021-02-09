package com.xinghaol.collection.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/9 12:13 下午
 * @description 数据过期
 */
public class CopyOnWriteArrayDemo2 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[] { 1, 2, 3 });
        System.out.println(list);

        Iterator<Integer> iterator1 = list.iterator();
        list.add(4);
        System.out.println(list);

        Iterator<Integer> iterator2 = list.iterator();
        iterator1.forEachRemaining(System.out::println);
        iterator2.forEachRemaining(System.out::println);
    }
}
