package com.test.effectivejava.Chapter3_所有对象通用的方法;


import com.test.effectivejava.entity.PhoneNumber;

/**
 * @author tracymc_zhu
 * 覆盖equals方法时必须覆盖hashcode方法
 * Object类的规范：
 *  1. 未被修改equals方法的对象，无论多少次调用hashCode方法都相同
 *  2. 两个对象的equals返回相同，则hashCode方法也返回相同
 *  3. 两个对象的equals返回不相同，则hashCode方法可以相同，也可以不相同；但不相同的情况散列性能更高
 *  为什么覆盖equals就必须要覆盖hashCode呢？
 *  因为如果没有覆盖hashCode方法，会破坏依赖于散列的类对象
 */
public class RuleNo11 {
    public static void main(String[] args) {
//        Map<PhoneNumber, String> map = new HashMap<>();
//        map.put(new PhoneNumber(707, 867, 5309), "Jenny");
//        System.out.println(map.get(new PhoneNumber(707, 867, 5309)));

        System.out.println(new PhoneNumber(707, 867, 5309).hashCode());
        System.out.println(new PhoneNumber(708, 867, 5309).hashCode());
        System.out.println(new PhoneNumber(709, 867, 5309).hashCode());
        System.out.println(new PhoneNumber(710, 867, 5309).hashCode());
    }
}


