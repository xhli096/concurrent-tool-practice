package com.xinghaol.immutable;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/7 8:13 下午
 * @description description
 */
public class WukongDemo {

    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";
        String d = "wukong";
        String c = b + 2;
        String e = d + 2;
        // true
        System.out.println(a == c);
        // false
        System.out.println(a == e);

        // b是final的，在编译阶段会被当做常量处理，所以c的值就是确定的，不会再生成一个新的。d不是final，在运行期间才会确定值，e在运行时的值是在堆中生成的。
    }
}
