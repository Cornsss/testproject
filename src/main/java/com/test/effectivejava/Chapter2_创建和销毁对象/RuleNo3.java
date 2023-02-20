package com.test.effectivejava.Chapter2_创建和销毁对象;

/**
 * @author tracymc_zhu
 * 使用私有构造器或枚举类型强化Singleton属性
 */
public class RuleNo3 {
    public static void main(String[] args) {
        System.out.println(Elvis.getInstance());
        System.out.println(Elvis2.INSTANCE);
    }
}

class Elvis {

    private static final Elvis INSTANCE = new Elvis();

    // 私有化构造器
    private Elvis(){}

    public static final Elvis getInstance() {
        return INSTANCE;
    }
}

enum Elvis2 {
    INSTANCE;
}
