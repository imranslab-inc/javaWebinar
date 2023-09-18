package com.imranslab.module4;

public class ThreadWaitNotifyAndNotifyAll {
    static class Message {
        // Message class is used to hold a string message that will be shared between 'Waiter' and 'Notifier' threads.
        private String msg;

        public Message(String str) {
            this.msg = str;
        }

        public String getMessage() {
            return msg;
        }

        public void setMessage(String str) {
            this.msg = str;
        }
    }

    static class Waiter implements Runnable {
        // The 'Waiter' thread waits for a notification by calling msg.wait() inside a synchronized block
        private final Message msg;

        public Waiter(Message m) {
            this.msg = m;
        }

        @Override
        public void run() {
            synchronized (msg) {
                try {
                    System.out.println("Waiting for message to be set by notifier thread.");
                    msg.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Received message: " + msg.getMessage());
            }
        }
    }

    static class Notifier implements Runnable {
        // The Notifier thread sets a new message and then calls 'msg.notify()' to wake up the waiting thread ('Waiter')
        // When the 'Waiter' thread wakes up, it proceeds to execute the remaining lines of code after the 'wait()' call.
        private final Message msg;

        public Notifier(Message m) {
            this.msg = m;
        }

        @Override
        public void run() {
            synchronized (msg) {
                msg.setMessage("Notifier has sent a message");
                msg.notify();
            }
        }
    }

    public static class Main{
        public static void main(String[] args)
        {
            Message message = new Message("Initial Message");

            Thread waiterThread = new Thread(new Waiter(message), "waiterThread");
            Thread notifierThread = new Thread(new Notifier(message), "notifierThread");

            waiterThread.start();
            notifierThread.start();
        }
    }
}

