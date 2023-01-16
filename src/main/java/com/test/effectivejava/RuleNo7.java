package com.test.effectivejava;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author tracymc_zhu
 * 消除过期的对象引用
 * WeakHashMap是个好东西
 * Heap剖析工具：Heap Profile
 */
public class RuleNo7 {
    public static void main(String[] args) {

    }

}

class MyStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public MyStack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        /**
         * 如果这里不消除引用就会产生很多垃圾
         */
        // start 改造
        Object obj = elements[--size];
        elements[size] = null;
        return obj;
        // end
//        return elements[--size];
    }

    public void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2*size+1);
        }
    }
}
