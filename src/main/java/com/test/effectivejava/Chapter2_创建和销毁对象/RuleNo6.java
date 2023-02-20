package com.test.effectivejava.Chapter2_创建和销毁对象;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author tracymc_zhu
 * 减少创建不必要的对象
 */
public class RuleNo6 {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println(sumInteger());
        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println("------------------------");
        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println(sumInteger2());
        System.out.println(simpleDateFormat.format(new Date()));
    }

    private void keySetDemo() {
        Map<String, String> map = new HashMap<>();
        map.put("serialno", "100");
        Set<String> strings = map.keySet(); // 看似每次都创建了一个实例，但其实没有
        for (String string : strings) {
            System.out.println(string);
            System.out.println(map.get(string));
        }
    }

    /**
     * 这个方法存在自动装箱和拆箱的问题，所以效率会比较低
     * 要优先使用基本类型而不是装箱基本类型
     * 这里大概需要7、8s
     * @return
     */
    private static long sumInteger() {
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    /**
     * 这个方法只需要1s
     * @return
     */
    private static long sumInteger2() {
        long sum = 0;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
