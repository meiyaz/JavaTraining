package org.multithreading;

class DaemonThread extends Thread {
    // background tasks
    // lifecycle - user thread (low priority)
    public void run() {
        System.out.println(Thread.currentThread().isDaemon());
        if(Thread.currentThread().isDaemon()) {
            // run bg tasks
            System.out.println("daemon thread");
        } else {
            // normal thread
            System.out.println("user thread");
        }
    }
}

public class DaemonThreadsEx {
    public static void main(String[] args) {

        // daemon thread
        DaemonThread d1 = new DaemonThread();
        d1.setDaemon(true);
        d1.start();

        // user thread
        DaemonThread d2 = new DaemonThread();
        d2.start();
    }
}
