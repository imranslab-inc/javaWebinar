package com.imranslab.module4;

public class ThreadSafetyAndSynchronization {
    // Bank Account Class with synchronized methods for thread safety
    public static class BankAccount {
        private int balance;

        public BankAccount(int balance) {
            this.balance = balance;
        }

        // Synchronized method to withdraw money
        public synchronized void withdraw(int amount) {
            if (balance >= amount) {
                System.out.println("Withdrawing: " + amount);
                balance -= amount;
                System.out.println("Remaining Balance :" + balance);
            } else {
                System.out.println("Not Enough balance for withdrawl!");
            }
        }

        public synchronized void deposit(int amount) {
            System.out.println("Depositing :" + amount);
            balance += amount;
            System.out.println("New Balance :" + balance);
        }
    }

    static class AccountService implements Runnable {
        private final BankAccount account;
        private final boolean withdraw;
        private final int amount;

        public AccountService(BankAccount account, boolean withdraw, int amount) {
            this.account = account;
            this.withdraw = withdraw;
            this.amount = amount;
        }


        @Override
        public void run() {
            if (withdraw) {
                account.withdraw(amount);
            } else {
                account.deposit(amount);
            }

        }
    }

    public static void main(String[] args) {
        ThreadSafetyAndSynchronization.BankAccount account = new ThreadSafetyAndSynchronization.BankAccount(1000); // Initial balance is 1000

        // Two threads trying to withdraw and deposit money concurrently
        Thread t1 = new Thread(new ThreadSafetyAndSynchronization.AccountService(account, true, 500), "Thread-1");
        Thread t2 = new Thread(new ThreadSafetyAndSynchronization.AccountService(account, false, 300), "Thread-2");

        t1.start();
        t2.start();
    }
}

