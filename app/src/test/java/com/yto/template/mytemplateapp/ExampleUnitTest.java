package com.yto.template.mytemplateapp;

import android.os.SystemClock;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        assertEquals(4, 2 + 2);

        Object softObj = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        SoftReference<Object> softReference = new SoftReference<Object>(softObj,queue);
        //引用队列
        System.out.println("soft:"+softReference.get());
        System.out.println("soft queue:" + queue.poll());
        //请求gc
        softObj = null;
        System.gc();
        Thread.sleep(1_000);

        System.out.println("soft:"+softReference.get());
        System.out.println("soft queue:" + queue.poll());

        Object weakObj = new Object();
        ReferenceQueue<Object> weakqueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(weakObj, weakqueue);
        //引用队列
        System.out.println("weak:"+weakReference.get());
        System.out.println("weak queue:" + weakqueue.poll());
        //请求gc
        weakObj = null;
        System.gc();
        Thread.sleep(1_000);

        System.out.println("weak:"+weakReference.get());
        System.out.println("weak queue:" + weakqueue.poll());

//        long uptimeMillis = SystemClock.uptimeMillis();
//        System.out.println("uptimeMillis"+uptimeMillis);
    }

    private static final Object sLockObject = new Object();

    @Test
    public void thread_sleep_wait_test(){
        System.out.println("主线程运行");
        Thread thread = new WaitThread();
        thread.start();
        System.out.println("开启线程结束");
        long startTime = System.currentTimeMillis();
        try {
            synchronized (sLockObject) {
                System.out.println("主线程等待");
                sLockObject.wait();//wait会释放锁
            }
        }catch (Exception e){}
        long timeMs = (System.currentTimeMillis() - startTime);
        System.out.println("主线程继续->等待耗时：" + timeMs);
    }

    static class WaitThread extends Thread{
        @Override
        public void run() {
            try {
                synchronized (sLockObject) {
                    Thread.sleep(3000);//sleep不会释放锁
                    sLockObject.notifyAll();
                }
            }catch (Exception e){
            }
        }
    }

    static class Worker extends Thread {

        public Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                synchronized (sLockObject) {
                    Thread.sleep(2000);//sleep不会释放锁
                }
            }catch (Exception e){
            }
            System.out.println("work in " + getName());
        }
    }

    @Test
    public void thread_join_test() {
        Worker worker1 = new Worker("worker-1");
        Worker worker2 = new Worker("worker-2");
        worker1.start();
        System.out.println("启动线程1");
        try{
            //调用worker1的join函数，主线程会阻塞直到worker1线程结束
            worker1.join();
            System.out.println("启动线程2");
            worker2.start();
            worker2.join();
        } catch (Exception e) {e.printStackTrace();}
        System.out.println("主线程继续执行。。。");
    }

    static class YieldThread extends Thread {

        public YieldThread(String name) {
            super(name);
        }

        @Override
        public synchronized void run() {
//            for (int i = 0; i < 5; i++) {
//                System.out.println(this.getName()+ " "+this.getPriority()+ " "+i);
//                if(i ==2) {//从第四个开始会让出当前线程的执行等待其它线程执行完后再执行
//                    Thread.yield();
//                }
//            }

            System.out.println(getName() + " hello");
            Thread.yield();
            System.out.println(getName() + " world");
        }
    }

    @Test
    public void thread_yield_test(){
        YieldThread t1 = new YieldThread("thread_1");
        YieldThread t2 = new YieldThread("threa_2");
        YieldThread t3 = new YieldThread("thread_3");
        YieldThread t4 = new YieldThread("threa_4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();

//        Future<String> future;
//        FutureTask<String> futureTask;
    }

    static class Thread_Test implements Runnable{
        int i = 1;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    // 先唤醒另外一个线程
                    notify();
                    try {
                        Thread.currentThread();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i <= 100) {
                        System.out.println(Thread.currentThread().getName() + ":"+ i);
                        i++;
                        try {
                            // 打印完之后，释放资源，等待下次被唤醒
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Test
    public void thread_test(){
        Thread_Test t1 = new Thread_Test();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t1);
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }


}