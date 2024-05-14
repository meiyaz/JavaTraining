package org.training;

import java.util.concurrent.atomic.AtomicInteger;

class SynchronizedCounter {
    private int count = 0;

    public synchronized void increaseCount() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increaseCount() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}

public class SynchronizedThreadEx {
    public static void main(String[] args) {
        AtomicCounter c = new AtomicCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                c.increaseCount();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                c.increaseCount();
            }
        });

        t1.start();
        t2.start();

        try {
            // join(): wait for thread to finish execution
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("err: " + e);
        }

        System.out.println(c.getCount());
    }
}
