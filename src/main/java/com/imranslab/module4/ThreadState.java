package com.imranslab.module4;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        // New State
        Thread myThread = new Thread(new MyRunnable(), "t1");
        System.out.println("Thread t1 state :" + myThread.getState());

        // Runnable state
        myThread.start();
        System.out.println("Thread t1 state :" + myThread.getState());

        // Timed waiting state, thread will sleep
        Thread.sleep(500);
        System.out.println("Thread t1 state :" + myThread.getState());

        // These two Println functions are not showing the right states. Bug!
        // Waiting State
        myThread.join();
        System.out.println("Thread t1 state :" + myThread.getState());

        // Terminated state
        Thread.sleep(1000);
        System.out.println("Thread t1 state :" + myThread.getState());
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Count: " + i);
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
