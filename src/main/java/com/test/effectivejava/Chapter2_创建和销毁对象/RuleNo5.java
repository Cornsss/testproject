package com.test.effectivejava.Chapter2_创建和销毁对象;

import java.util.Dictionary;
import java.util.Objects;

/**
 * 优先考虑用依赖注入来引用资源
 */
public class RuleNo5 {
    public static void main(String[] args) {

    }
}

class SpellCheck {
    private Dictionary dictionary;
    public SpellCheck(Dictionary dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
}
