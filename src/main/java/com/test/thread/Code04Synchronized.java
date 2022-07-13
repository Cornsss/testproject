package com.test.thread;

public class Code04Synchronized {
    public static void main(String[] args) {
        // 首先拿到锁，执行m1的代码块，
        // 在执行到m2()时，可以再次获取同一个锁资源，进入m2代码块
        // 所以synchronized是可重入锁
        new Thread(() -> m1()).start();
    }

    static synchronized void m1() {
        System.out.println("m1 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m2 end");
    }

    static synchronized void m2() {
        System.out.println("m2 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }
}
