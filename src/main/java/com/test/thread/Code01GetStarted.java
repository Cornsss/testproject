package com.test.thread;

import com.test.utils.TimeUtils;

public class Code01GetStarted {

    private static int i = 0;

    public synchronized void increment() {
        i++;
        System.out.println(i);
    }

    public static void main(String[] args) throws InterruptedException {
        Code01GetStarted code01GetStarted = new Code01GetStarted();
//        System.out.println("开始时间："+TimeUtils.getNow(TimeUtils.YYYY_MM_DD_HH_MM_SS));
//        for (int j = 0; j < 1000; j++) {
//            code01GetStarted.increment();
//        }
//        System.out.println(i);
//        System.out.println("结束时间："+TimeUtils.getNow(TimeUtils.YYYY_MM_DD_HH_MM_SS));

        System.out.println("开始时间："+TimeUtils.getNow(TimeUtils.YYYY_MM_DD_HH_MM_SS));
        for (int j = 0; j < 1000; j++) {
            new Thread(() -> code01GetStarted.increment()).start();
        }
//        System.out.println(i);
        System.out.println("结束时间："+TimeUtils.getNow(TimeUtils.YYYY_MM_DD_HH_MM_SS));

    }
}
