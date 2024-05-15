package org.multithreading;

public class NormalExecution {

    public static void printHello(int i) {
        // 10s execution
        System.out.println("hello, i: " + i);
    }

    public static void main(String[] args) {
        // single thread, printHello will get triggered 5 times sequentially
        for (int i=0; i<5; i++) {
            printHello(i);
        }
        // 50s
    }
}
