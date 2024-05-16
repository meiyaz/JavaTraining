package org.testcases;

public class Calculator {
    public static int add(int a, int b) {
        return a + b + subtract(a, b);
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static void main(String[] adrs) {
        int a = 10;
        int b = 20;
        System.out.println(add(a, b));
        System.out.println(subtract(a, b));
    }
}
