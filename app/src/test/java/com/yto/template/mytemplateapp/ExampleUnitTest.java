package com.yto.template.mytemplateapp;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

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
    }
}