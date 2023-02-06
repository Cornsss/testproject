package com.test.effectivejava;

import com.sun.tools.javac.util.List;

/**
 * 谨慎并用泛型和可变参数
 *  1. 当一个参数化类型的变量指向一个不是该类型对象时，会产生堆污染(heap pollution)
 *  2. SafeVarargs注解，它让带泛型vararg参数的方法的设计者能够自动禁止客户端的警告。通过方法的设计者承诺声明这是类型安全的。
 */
public class RuleNo32 {
    public static void main(String[] args) {

    }

    public static void deangerous(List<String>... stringLists){
        List<Integer> intList = List.of(42);
        Object[] objects = stringLists;
        objects[0] = intList;    // heap pollution
        String s = stringLists[0].get(0);  // ClassCastException
    }
}
