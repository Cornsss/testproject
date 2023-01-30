package com.test.effectivejava;

import com.test.effectivejava.entity.PhoneNumber;

import java.math.BigDecimal;
import java.util.*;

/**
 * 考虑实现Comparable接口
 * 1. compareTo方法也遵循equals的特性
 * 2. compareTo比较不同类型对象会抛出ClassCastException
 * 3. 如果一个对象多个域需要比较，Comparable接口提供了静态比较器构造方法:
 */
public class RuleNo14 {
    public static void main(String[] args) {
        // 依赖于实现了Comparable接口的String类进行排序
        Set<String> set = new TreeSet<String>();
        Collections.addAll(set, args);
        System.out.println(set);

        // 演示bigdecimal
        BigDecimal d1 = new BigDecimal("1.0");
        BigDecimal d2 = new BigDecimal("1.00");
        System.out.println(d1.compareTo(d2)); // 相等，使用TreeSet添加这两个元素会有1个元素
        System.out.println(d1.equals(d2)); // 不相等，使用HashSet添加这两个元素会有2个元素

        // 演示比较构造器：查看代码 PhoneNumber.java
        List<PhoneNumber> phoneNumbers = Arrays.asList(new PhoneNumber(710, 867, 229), new PhoneNumber(709, 867, 122), new PhoneNumber(706, 867, 56), new PhoneNumber(707, 867, 12)
                , new PhoneNumber(34, 867, 5309), new PhoneNumber(68, 867, 43534), new PhoneNumber(211, 867, 31323), new PhoneNumber(707, 867, 5309));
        System.out.println("sort before ---");
        for (PhoneNumber phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }
        Collections.sort(phoneNumbers);
        System.out.println("sort after ---");
        for (PhoneNumber phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }
    }
}

class Person implements Comparable{

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
