package com.imranslab.module4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    public static class Example3{
        /*
        * If you are using an 'ExecutorService' to manage threads, you can use 'Future' objects to catch exceptions.
        * In this example, we use 'future.get()' to retrieve the result of the computation. If an exception is thrown
        * within the thread, it will be wrapped in an 'ExecutionException', which we can catch and handle.
        *
        * These are some ways to handle exceptions in a multithreaded environment in Java. Each method has its own pros
        * and cons, so you should choose the one that best fits your specific needs.
        * */

       public static void main(String[] args){
           ExecutorService executor = Executors.newSingleThreadExecutor();

           Future<?> future = executor.submit(()-> {
              // This will throw an exception
              throw new IllegalStateException("Illegal state");
           });

           try{
               future.get();
           } catch (ExecutionException | InterruptedException e) {
               Throwable cause = e.getCause();
               System.out.println("Caught exception: " + cause.getMessage());
           }
           executor.shutdown();
       }
    }
}
