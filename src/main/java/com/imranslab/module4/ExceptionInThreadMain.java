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
        public static void main(String[] args)
        {
            Thread thread2 = new Thread(()->
            {
                try{
                    // Simulating exception
                    throw new ArithmeticException("Division by zero");

                } catch (ArithmeticException e){
                    System.out.println(Thread.currentThread().getName() + " caught exception: " + e.getMessage());
                }
            }, "Thread-1");
            thread2.start();
        }
    }
}
