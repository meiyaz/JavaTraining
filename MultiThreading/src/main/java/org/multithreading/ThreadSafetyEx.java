package org.multithreading;

class ThreadSafety extends Thread {
    public void run() {

    }
}

public class ThreadSafetyEx {
    // avoid using global variables fot threads
    // ensure closing instances
    public static void main(String[] args) {
        ThreadSafety ts = new ThreadSafety();
        ts.start();
    }
}
