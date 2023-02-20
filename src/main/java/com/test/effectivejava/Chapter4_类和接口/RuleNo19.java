package com.test.effectivejava.Chapter4_类和接口;

import com.test.effectivejava.entity.Son;

/**
 * @author tracymc_zhu
 * 要么禁用继承，要么设计继承并提供文档说明
 */
public class RuleNo19 {
    public static void main(String[] args) {
        // 如果在构造方法中调用可被覆盖的方法，可能会造成空指针的情况
        // 因为在构造器调用之前，域还没被初始化，在调用可被覆盖的方法后就会出现null的情况
        Son son = new Son();
        son.overrideMe();
    }
}
