package com.test.effectivejava;

/**
 * 实例域代替序数
 * 因为如果使用ordinal方法返回序数，如果枚举值发生顺序变化，他会跟着变化，
 * 所以如果需要使用序数，就要用实例域代替序数
 */
public class RuleNo35 {
    public static void main(String[] args) {
        System.out.println(Operation.MINUS.numberOfEnum());
    }
}
