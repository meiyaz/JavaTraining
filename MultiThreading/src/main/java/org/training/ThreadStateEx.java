package org.training;

class StateThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getState());
    }
}

public class ThreadStateEx {
    public static void main(String[] args) {
        StateThread p = new StateThread();
        System.out.println(p.getState());
        p.start();
    }
}
