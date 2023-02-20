package com.test.effectivejava;

import java.util.EnumSet;
import java.util.Set;

/**
 * 使用EnumSet代替位域
 * 位域：使用int枚举模式，将2的不同倍数赋予每个常量
 * 当需要将多个操作（或变量）进行组合时，可以使用EnumSet来代替使用int枚举进行位域操作的方式
 */
public class RuleNo36 {
    public static void main(String[] args) {
        new Text2().applyStyles(EnumSet.of(Text2.Style.STYLE_BOLD, Text2.Style.STYLE_UNDERLINE));
    }
}

class Text {
    public static final byte STYLE_BOLD          = 1<<0; // 1
    public static final byte STYLE_ITALIC        = 1<<1; // 2
    public static final byte STYLE_UNDERLINE     = 1<<2; // 4
    public static final byte STYLE_STRIKETHROUGH = 1<<3; // 6

    // 使用int会出现一些问题，可以参考rule30
    public void applyStyles(int styles) {

    }
}

class Text2 {
    enum Style {STYLE_BOLD, STYLE_ITALIC, STYLE_UNDERLINE, STYLE_STRIKETHROUGH}

    // 使用int会出现一些问题，可以参考rule30
    public void applyStyles(Set<Style> styles) {
        System.out.println(styles.toString());
    }
}
