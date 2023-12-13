package com.test.leekcode;

import cn.hutool.core.date.DateUtil;

public class CheckUtils01 {

    private static long startTime;
    private static long startMemory;

    public static void startRecording() {
        startTime = System.nanoTime();
        startMemory = getUsedMemory();
    }

    public static void stopRecording() {
        long endTime = System.nanoTime();
        long endTimeMemory = getUsedMemory();

        long elapsedTime = endTime - startTime;
        long usedMemory = endTimeMemory - startMemory;

        System.out.println("执行时间：" + DateUtil.formatBetween(elapsedTime/1000000));
        System.out.println("内存使用：" + usedMemory + "字节");
    }

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static void main(String[] args) throws InterruptedException {
        CheckUtils01 recorder = new CheckUtils01();

        recorder.startRecording();

        // 执行你的代码
        Thread.sleep(10000);

        recorder.stopRecording();
    }
}
