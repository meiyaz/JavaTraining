package org.training;

class SleepingThreadOne implements Runnable {
    public void run() {
        try{
            if (Thread.currentThread().getName().equals("2"))
                Thread.sleep(2000);
            System.out.println("hello, my thread id is " + Thread.currentThread().getId()
                    + " and my thread name is " + Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class SleepingThreadTwo extends Thread {
    public void run() {
        try {
            for(int i=0; i<5; i++) {
                Thread.sleep(2000);
                System.out.println("SleepingThreadTwo " + Thread.currentThread().getId());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class ThreadSleepEx {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new SleepingThreadOne());
            t.setName(String.valueOf(i));
            t.start();
        }

        SleepingThreadTwo t = new SleepingThreadTwo();
        t.start();
    }
}
