package com.test.effectivejava.Chapter2_创建和销毁对象;

/**
 * @author tracymc_zhu
 * 使用私有构造器强化不可实例化的属性
 */
public class RuleNo4 {
    public static void main(String[] args) {
        System.out.println();
    }
}

class UtilityClass {
    private UtilityClass() {
        throw new AssertionError();
    }
}
