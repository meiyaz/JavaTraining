package org.training;

class ThreadOne extends Thread {
    public void run() {
        System.out.println("hello, my thread id is " + Thread.currentThread().getId()
                + " and my thread name is " + Thread.currentThread().getName());
    }
}

class ThreadTwo implements Runnable {
    public void run() {
        System.out.println("hello, my thread id is " + Thread.currentThread().getId()
                + " and my thread name is " + Thread.currentThread().getName());
    }
}

public class ThreadCreation {
    public static void main(String[] args) {
        ThreadOne d = new ThreadOne();
        d.start();

        Thread t = new Thread(new ThreadTwo());
        t.start();

        Thread l = new Thread(() -> {
            System.out.println("hello");
        });
        l.start();
    }
}
