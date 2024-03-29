package com.test.effectivejava.Chapter2_创建和销毁对象;

/**
 * @author tracymc_zhu
 *
 * 如果需要复习，可以阅读：https://cl0610.github.io/effective-java-learning
 * 更加易懂一些
 *
 * @apiNote 用静态工厂方法替代构造器
 * 1. 使返回的方法更有标识性
 * 2. 不用每次都new对象
 * 3. 静态方法可以有返回值，可以返回该对象的任何子类对线
 */
public class RuleNo1 {

    private int x;
    private int y;

    private RuleNo1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static RuleNo1 getSumObj(int x, int sum) {
        return new RuleNo1(x, x+sum);
    }

    public static void main(String[] args) {
        System.out.println(RuleNo1.getSumObj(1,2).y);

        String a = "xxx";
        String b = "xxx";
        String c = new String("xxx");
//        String b = a.toUpperCase(Locale.ROOT);
        System.out.println(a==c);
    }
}
