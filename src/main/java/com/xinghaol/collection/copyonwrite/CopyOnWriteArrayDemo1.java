package com.xinghaol.collection.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/8 4:41 下午
 * @description description
 */
public class CopyOnWriteArrayDemo1 {

    public static void main(String[] args) {
        // list在 迭代过程中修改，会报错 java.util.ConcurrentModificationException
        // List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("list is " + list);
            String next = iterator.next();
            System.out.println("next : " + next);

            if (next.equals("2")) {
                list.remove("5");
            }
            if (next.equals("3")) {
                list.add("3 found");
            }
        }
    }
}
