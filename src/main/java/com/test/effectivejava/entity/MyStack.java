package com.test.effectivejava.entity;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack implements Cloneable{

    // 存放元素的容器
    private Object[] elements;

    // 元素个数
    private int size = 0;

    // 初始化容器大小
    private final int INITIAL_CAPACITY_SIZE = 16;

    public MyStack() {
        this.elements = new Object[INITIAL_CAPACITY_SIZE];
    }

    public void push(Object element) {
        // 判断是否需要扩容
        ensureSize();
        // 加入元素
        elements[size++] = element;
    }

    public int size() {
        return this.size;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        // 消除无用的对象引用
        Object obj = elements[--size];
        elements[size] = null;
        return obj;
    }

    private void ensureSize() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, 2*size+1);
        }
    }

    @Override
    public MyStack clone() {
        try {
            // 因该对象存在可变域，需要遍历并调用clone方法
            MyStack clone = (MyStack) super.clone();
            clone.elements = elements.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
