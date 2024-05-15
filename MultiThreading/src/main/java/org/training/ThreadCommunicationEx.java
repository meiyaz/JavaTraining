package org.training;

public class ThreadCommunicationEx {
    private final Object lock = new Object();

    public void pauseThread() throws InterruptedException {
        synchronized (lock) {
            System.out.println("thread waiting");
            lock.wait(); // wait until notify or notifyAll is getting triggered
            System.out.println("thread resumed");
        }
    }

    public void resumeThread() {
        synchronized (lock) {
            lock.notify();
            System.out.println("allowing thread to resume");
        }
    }

    public static void main(String[] args) {
        ThreadCommunicationEx tc = new ThreadCommunicationEx();

        Thread t1 = new Thread(() -> {
            try {
                tc.pauseThread();
            } catch (Exception e) {
                System.out.println("Exception on wait");
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1500);
                tc.resumeThread();
            } catch (Exception e) {
                System.out.println("Exception on resume");
            }
        });

        t1.start();
        t2.start();
    }
}
