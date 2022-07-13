package com.test.thread;

public class Code02WhatIsThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("extends thread");
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("implements runnable");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
    }
}
