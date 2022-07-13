package com.test.thread;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class Code05SynchronizedObj {
    public static void main(String[] args) {
        System.out.println(VM.current().details());
        Object o = new Object();
        System.out.println((ClassLayout.parseInstance(o).toPrintable()));
    }
}
