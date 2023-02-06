package com.test.effectivejava.entity;

public class Son extends Father {

    private String name = "son";

    public Son() {
        System.out.println("son");
    }

    public void play(){
        System.out.println("play ...");
    }

    @Override
    public void overrideMe() {
        System.out.println("override name: " + name);
    }
}
