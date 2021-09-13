package com.dhlg.module.test.lianxi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Xianchenchi {
    static class TempThread implements Runnable{
        @Override
        public void run() {
            // 打印正在执行的缓存线程信息
            System.out.println(Thread.currentThread().getName() + "正在被执行");
            try {
                // sleep一秒保证3个任务在分别在3个线程上执行
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        // 队列
        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
        // corePoolSize(核心线程数) maximumPoolSize（最大线程数）keepAliveTime（当线程数大于核心数时，这是多余空闲线程在终止前等待新任务的最长时间）unit（时间单位）（workQueue）用于在执行任务之前保存任务的队列
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3,6,50, TimeUnit.MILLISECONDS,bq);

        // 创建3个任务
        Runnable t1 = new TempThread();
        Runnable t2 = new TempThread();
        Runnable t3 = new TempThread();

        // 3个任务在分别在3个线程上执行
        tpe.execute(t1);
        tpe.execute(t2);
        tpe.execute(t3);

        // 关闭自定义线程池
        tpe.shutdown();
    }
}
