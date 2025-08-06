package com.example.synchronisation;


import java.util.concurrent.Semaphore;

public class Threads {

    private static volatile Object lock = new Object();
    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        new Print(0).start();
        new Print(1).start();
    }


    static class Print extends Thread {

        private int current = 0;

        public Print(int initial) {
            this.current = initial;
        }

        @Override
        public void run() {
            while (true) {
                semaphore.tryAcquire();
                System.out.println(current);
                this.current += ++current;
                semaphore.release();
            }
        }
    }


}
