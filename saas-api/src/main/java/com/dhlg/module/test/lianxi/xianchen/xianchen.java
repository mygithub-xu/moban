package com.dhlg.module.test.lianxi.xianchen;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;

public class xianchen {
    static class ThreadTest extends Thread {

        @Override
        public void run() {
            for(int i = 1; i <= 10; i++){
                System.out.println("ThreadTest"+"--"+i);
            }

        }
    }
    static class RunableTest implements Runnable {
        private CyclicBarrier cyclicBarrier ;
        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            for(int i = 1; i <= 10; i++){
                System.out.println("runableTest"+"--"+i);
            }

        }
    }
    static class CallableTest implements Callable {
        @Override
        public Object call()  {
            int aa = 1/0;
            for(int i = 1; i <= 10; i++){
                System.out.println("CallableTest"+"--"+i);
            }
            return null;
        }
    }
    public static void main(String[] args) {
        // ThreadTest
        ThreadTest threadTest = new ThreadTest();
        // RunableTest
        Thread runableTest = new Thread(new RunableTest());
        // CallableTest
        FutureTask futureTask = new FutureTask(new CallableTest());
        Thread callableTest = new Thread(futureTask);
        threadTest.interrupt();
        threadTest.start();
        runableTest.start();
        callableTest.start();
    }
}
