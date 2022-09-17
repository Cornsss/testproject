package com.test.mysql;


import cn.hutool.core.util.RandomUtil;

public class MysqlInnoDBMasterThreadDemo {

    // 上1s发生的IO次数
    private static int last_second_io_times;

    // 上10s发生的IO次数
    private static int last_10second_io_times;

    // 当前缓冲池中的脏页比例
    private static int buf_get_modified_ratio_pct;


    public static void main(String[] args) throws InterruptedException {
        task1S();
        task10s();
    }

    private static void task1S() throws InterruptedException {
        // 每1s的任务
        for (int i =0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("================================start each second task===========================================");
            System.out.println("1.1 刷新日志到磁盘中");
            last_second_io_times = RandomUtil.randomInt(5);
            if (last_second_io_times < 5) {
                System.out.println("1.2 上1s的IO次数大于5次，进行合并插入缓存操作");
            }
            buf_get_modified_ratio_pct = RandomUtil.randomInt(85, 100);
            if (buf_get_modified_ratio_pct > InnoDBParam.innodb_max_dirty_pages_pct) {
                System.out.println("1.3 当前的脏页比例已大于配置的脏页阈值，刷新100个脏页到磁盘");
            }
            System.out.println("后台线程..");
        }
    }

    private static void task10s() {
        System.out.println("================================start 10 sencond task===========================================");
        last_10second_io_times = RandomUtil.randomInt(180, 200);
        if (last_10second_io_times < 200) { // 当前系统有足够的磁盘IO能力
            System.out.println("2.1 刷新100个脏页到磁盘");
        }
        System.out.println("2.2 合并插入缓冲（至多5个）");
        System.out.println("2.3 刷新日志缓冲");
        System.out.println("2.4 回收Undo页（至多20页）");
        buf_get_modified_ratio_pct = RandomUtil.randomInt(65, 75);
        if (buf_get_modified_ratio_pct > 70) {
            System.out.println("2.5 当前脏页比例过多，刷新100个脏页");
        } else {
            System.out.println("2.5 刷新脏页比例合理，刷新" + buf_get_modified_ratio_pct*0.1 + "个脏页");
        }

    }

    private static void shutdowntask() {
        System.out.println("end task1: 删除Undo页");
        System.out.println("end task2: 合并插入缓冲");
    }
}
