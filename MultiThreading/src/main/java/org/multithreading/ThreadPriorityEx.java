package org.multithreading;

class PriorityThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getPriority());
    }
}

public class ThreadPriorityEx {
    public static void main(String[] args) {
        // priority ranges from 1 to 10
        PriorityThread p1 = new PriorityThread();
        p1.setPriority(1);

        PriorityThread p2 = new PriorityThread();
        p2.setPriority(Thread.MAX_PRIORITY);

        PriorityThread p3 = new PriorityThread();
        p3.setPriority(6);

        p1.start();
        p2.start();
        p3.start();
    }
}
