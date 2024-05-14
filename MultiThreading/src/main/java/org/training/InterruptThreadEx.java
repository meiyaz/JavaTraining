package org.training;

public class InterruptThreadEx {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("start");
                Thread.sleep(3000);
                System.out.println("end");
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        });
        t1.start();

        // to interrupt existing thread
//        t1.interrupt();
    }
}
