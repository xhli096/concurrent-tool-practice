import java.util.HashMap;

/**
 * @author xinghaol
 * @version 1.0
 * @date 2021/2/8 10:56 上午
 * @description 需要再JDK1.7的版本下，演示hashmap在多线程情况下造成死循环的情况
 */
public class HashmapEndlessLoop {

    private static HashMap<Integer, String> map = new HashMap<Integer, String>(2, 1.5f);

    public static void main(String[] args) {
        // 目的在hash后落到同一个槽内
        map.put(5, "C");
        map.put(7, "B");
        map.put(3, "A");

        new Thread(new Runnable() {
            public void run() {
                map.put(15, "D");
                System.out.println(map);
            }
        }, "thread1").start();

        new Thread(new Runnable() {
            public void run() {
                map.put(1, "E");
                System.out.println(map);
            }
        }, "thread2").start();
    }
}
