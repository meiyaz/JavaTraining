package org.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadPool extends Thread {
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        System.out.println("thread pool");
    }
}

public class ThreadPoolsEx {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(5);
        ThreadPool tp = new ThreadPool();
        // tp.start();
        ex.execute(tp); // to call thread

        for(int i=0; i<=200; i++) {
            ThreadPool t = new ThreadPool();
            ex.submit(t);
//            t.start();
        }

        ex.shutdown();

    }
}
