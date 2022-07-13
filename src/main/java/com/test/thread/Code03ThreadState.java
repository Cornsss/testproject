package com.test.thread;

/**
 * 线程状态迁移
 * 1.NEW 2.RUNNABLE 3.就绪 4.WAIT 5.BLOCKED 6.TERMINATED
 * 线程创建（1） ---->
 */
public class Code03ThreadState {
    private static Thread t = null;
    public static void main(String[] args) {
        testState();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3. 线程运行结束 TERMINATED
        System.out.println(t.getState());
    }

    public static void testState() {
        t = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread " + i);
            }
        });
        // 1. 初始化状态new
        System.out.println(t.getState());
        t.start();
        // 2. 线程启动后为就绪状态
        System.out.println(t.getState());

    }
}
