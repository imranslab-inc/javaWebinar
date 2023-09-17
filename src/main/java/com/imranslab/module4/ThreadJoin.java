package com.imranslab.module4;

public class ThreadJoin {
    public static void main(String[] args) {
        Thread myThread = new Thread(new Runnable() {
            // A new thread 'myThread' is created, and its 'run()' method is overriden to include a loop that prints
            // values from 0 to 9. The thread also sleeps for half a second between each iteration.
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().threadId() + " Value " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // InterruptedException: The join() method can throw an 'InterruptedException' if another thread
                        // interrupts the current thread while it's waiting for the joined thread to complete
                        e.printStackTrace();
                    }
                }
            }
        });

        // Let's start the new Thread
        myThread.start();

        // Wait for myThread to finish execution
        try {
            myThread.join();
            // We can also specify a time limit for which the current thread will wait for the joined thread to complete,
            // like so: 'myThread.join(1000);' waits upto 1 second for 'myThread' to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread execution resumes after myThread has completed its execution");
    }
}
