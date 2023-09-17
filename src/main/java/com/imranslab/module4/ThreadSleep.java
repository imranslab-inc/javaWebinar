package com.imranslab.module4;

public class ThreadSleep {
    /*
    The 'Thread.sleep()' method in Java is used to pause the execution of the current thread for a specified period of
    time. It can be helpful when simulating delays or for controlling the timing of thread execution. The sleep method
    takes a single argument, the amount of time to sleep, specified in milliseconds
     */

    public static void main(String[] args)
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Awake again!");
    }
}
