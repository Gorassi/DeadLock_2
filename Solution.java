package com.javarush.task.task30.task3008;

public class Solution {
    private final Object lock1 = new Object();
    private final Object lock = new Object();
    private static int count=0;

    public synchronized void firstMethod(){
        System.out.println("this1 = " + this);
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
        System.out.println("this2 = " + this);
        synchronized(lock1){
            try{
                Thread.sleep(10);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            synchronized(lock){
                doSomething();
            }
        }

    }

    public void thirdMethod() {
        System.out.println("this2 = " + this);
        synchronized(lock){
            try{
                Thread.sleep(10);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            synchronized(lock1){
                doSomething();
            }
        }

    }

    private void doSomething() {
        System.out.println(Thread.currentThread().getName() + " : this = " + this);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("Main started...");
        new Thread(new Runnable(){
            public void run(){
                solution.thirdMethod();
//                solution.secondMethod();
            }
        }).start();
        new Thread(new Runnable(){
            public void run(){
 //               solution.firstMethod();
               solution.secondMethod();
            }
        }).start();
    }
}
