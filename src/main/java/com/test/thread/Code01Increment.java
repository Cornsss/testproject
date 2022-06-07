package com.test.thread;

import ch.qos.logback.core.util.TimeUtil;
import com.test.utils.TimeUtils;

import java.util.concurrent.TimeUnit;

public class Code01Increment {
    private static int i = 0;

    public static void increment() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (Code01Increment.class) {
            i++;
        }
    }

    public static void main(String[] args) {
//        System.out.println("开始时间："+TimeUtils.getNow(TimeUtils.YYYY_MM_DD_HH_MM_SS));
//        for (int j = 0; j < 1000; j++) {
//            increment();
//        }
//        System.out.println(i);
//        System.out.println("结束时间："+TimeUtils.getNow(TimeUtils.YYYY_MM_DD_HH_MM_SS));

        System.out.println("开始时间："+TimeUtils.getNow(TimeUtils.YYYY_MM_DD_HH_MM_SS));
        for (int j = 0; j < 1000; j++) {
            new Thread(() -> increment()).start();
        }
        System.out.println(i);
        System.out.println("结束时间："+TimeUtils.getNow(TimeUtils.YYYY_MM_DD_HH_MM_SS));

    }
}
