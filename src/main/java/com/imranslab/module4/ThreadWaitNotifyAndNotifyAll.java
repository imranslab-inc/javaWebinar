package com.imranslab.module4;

public class ThreadWaitNotifyAndNotifyAll {
    static class Message {
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

