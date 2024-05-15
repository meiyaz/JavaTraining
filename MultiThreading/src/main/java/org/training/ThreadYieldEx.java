package org.training;

public class ThreadYieldEx {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println("hello");
            Thread.yield();
        });
        t1.start();

        Thread t2 = new Thread(()->{
            System.out.println("hello");
            Thread.yield();
        });
        t2.start();
    }
}
