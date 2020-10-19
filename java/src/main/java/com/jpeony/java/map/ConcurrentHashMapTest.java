package com.jpeony.java.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap 线程安全，put时使用分段锁
 *
 * @author yihonglei
 */
public class ConcurrentHashMapTest {
    public static final Map<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        // 线程A
        Thread threadA = new Thread() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    map.put(i + "", i + "");
                }
            }
        };

        // 线程B
        Thread threadB = new Thread() {
            public void run() {
                for (int j = 1000; j < 2000; j++) {
                    map.put(j + "", j + "");
                }
            }
        };

        threadA.start();
        threadB.start();

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2000; i++) {
            System.out.println("第：" + i + " 元素，值：" + map.get(i + ""));

        }
    }
}
