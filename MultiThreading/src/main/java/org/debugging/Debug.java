package org.debugging;

import java.util.Scanner;

public class Debug {
    public static int divide(int n) {
        int res = n == 0 ? 0 : 100 / n;
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = divide(n);
        System.out.println(result);
    }
}
