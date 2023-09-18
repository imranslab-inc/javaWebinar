package com.imranslab.module4;

public class Counter implements Runnable {
    /*
    Implementing 'Runnable' interface: The 'Runnable' interface represents a task that can be executed asynchronously.
    This approach is more flexible than extending 'Thread' because your class can still extend other classes, since
    Java allows implementing multiple interfaces.
     */

    private String name;
    private int limit;

    public Counter(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }

    @Override
    public void run() {
        for (int i = 1; i <= limit; i++) {
            System.out.println(name + " count: " + i);
        }
    }

    public static void main(String[] args) {
        // Creating two counter threads
        Thread counter1 = new Thread(new Counter("Counter1", 5));
        Thread counter2 = new Thread(new Counter("Counter2", 5));

//         Creating a watcher thread
        Thread watcher = new Thread(() -> {
            try {
                System.out.println("Watcher: Waiting for counters to finish.");
                counter1.join();
                counter2.join();
                System.out.println("Watcher: Both counters are done!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Starting all threads
        counter1.start();
        counter2.start();
        watcher.start();
    }
}

