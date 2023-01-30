package com.test.effectivejava;

import com.test.effectivejava.entity.MyStack;

import java.util.*;

/**
 * @author tracymc_zhu
 * 慎用clone
 * 当对象有可变的域，使用clone可能造成灾难
 * 1. 实际上，clone方法就是另一个构造器，必须确保它不会伤害到原始的对象
 * 2. clone方法不能给final域赋新值
 * 3. 编写线程安全的类，clone方法也要得到严格的同步
 * 4. 拷贝更好的实现方法是拷贝器或是拷贝工厂，更好的说法是转换构造器，因为这个构造器可以让用户自由的转换拷贝的对象
 *    例如，通用集合都有一个拷贝器，参数是Collection或者Map
 */
public class RuleNo13 {
    public static void main(String[] args) {
//        Point1 point1 = new Point1(1, 2);
//        Point1 clone = point1.clone();
//        System.out.println(point1.getClass() == clone.getClass());
//        System.out.println(point1.equals(clone));

        // 当对象有可变域时,修改clone对象，看看是否对原对象有影响
        // 这里的结果是null，因为修改了clone对象的可变域，同时把原对象的可变域也修改了
        MyStack myStack = new MyStack();
        myStack.push(11);
        myStack.push(12);
        myStack.push(13);

        MyStack clone = myStack.clone();
        Object pop = clone.pop();
        System.out.println(myStack.pop());

        // 类型转换的拷贝
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        List<String> list2 = new LinkedList<>(list); //ArrayList 转换为 LinkedList
        System.out.println(list2.get(0));
    }
}

class Point1 implements Cloneable {

    private int x;
    private int y;

    public Point1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Point1 clone() {
        try {
            return (Point1) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}