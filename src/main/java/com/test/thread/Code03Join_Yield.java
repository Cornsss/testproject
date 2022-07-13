package com.test.thread;

public class Code03Join_Yield {

    public static void main(String[] args) {
        // join就是等待加入线程运行结束再执行当前线程
        testJoin();
    }

    public static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread t2 = new Thread(() -> {
//            try {
//                t1.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B" + i);
            }
        });

        Thread t3 = new Thread(() -> {
//            try {
//                t2.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C" + i);
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
