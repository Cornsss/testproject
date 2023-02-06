package com.test.effectivejava.entity;

public class Father {

    private String name = "father";

    public Father() {
        overrideMe();
        System.out.println("father");
    }

    public void beOld() {
        System.out.println("beOld ...");
    }

    public void overrideMe() {
        System.out.println("override name: " + name);
    }
}
