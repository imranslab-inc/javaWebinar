package com.imranslab.module4;
/*
* Exception handling in a multithreaded environment can be a bit tricky, as each thread runs independently of the
* others. This means that a 'try-catch' block in the 'main' method will not catch exception thrown in other threads. In
* Java, you can handle exceptions in multiple threads using several methods.
* */
public class ExceptionInThreadMain {
    /*
    * 1. Using 'try-catch' within the 'run' method
    * One straightforward way is to use a 'try-catch' block within the 'run' method of the thread.
    * This allows you to handle exceptions for each thread independently.
    * */

    public static class Example1{
        public static void main(String[] args)
        {
            Thread thread1 = new Thread(()->
            {
               try{
                   // Simulating exception
                   throw new ArithmeticException("Division by zero");

               } catch (ArithmeticException e){
                   System.out.println(Thread.currentThread().getName() + " caught exception: " + e.getMessage());
               }
            }, "Thread-1");
            thread1.start();
        }
    }

    public static class Example2{
        /*
        * Another approach is to use 'Thread.UncaughtExceptionHandler', which is an interface that you can use to create
        * a handler for uncaught exceptions for each Thread.
        * */
        public static void main(String[] args)
        {
            Thread thread2 = new Thread(()->
            {
                    // Simulating exception
                    throw new ArithmeticException("Division by zero");
            }, "Thread-2");

                    // here, we set an 'UncaughtExceptionHandler' for 'thread2'. If 'thread2' throws an uncaught exception
                    // the uncaughtException method of the handler will be invoked, allowing you to handle the exception
                    // as you see fit.

                    thread2.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println(t.getName() + " caught exception: " + e.getMessage());
                }
            });

            thread2.start();
        }
    }
}
