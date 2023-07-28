package com.javarush.task.task30.task3008;

public class Solution {
    private final Object lock = new Object();
    private static int count=0;

    public synchronized void firstMethod(){
        try{
            Thread.sleep(10);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        synchronized (lock) {
            doSomething();
        }
    }

    public void secondMethod() {
        synchronized(lock){
            try{
                Thread.sleep(10);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            synchronized(this){
                doSomething();
            }
        }
    }

    private void doSomething() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("Main started...");
        new Thread(new Runnable(){
            public void run(){
                solution.firstMethod(); }
        }).start();
        new Thread(new Runnable(){
            public void run(){
               solution.secondMethod();
            }
        }).start();
    }
}
