package com.chris.test_lib;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Chris on 2018/5/18.
 */

public class QueueTest {

    /**
     * 阻塞队列实现并测试
     */
    @Test
    public void arrayBlockingQueueTest() {
        MyArrayBlockingQueue<Integer> aQueue = new MyArrayBlockingQueue<Integer>();
        aQueue.put(3);
        aQueue.put(24);
        for (int i = 0; i < 5; i++) {
            System.out.println(aQueue.take());
        }

//        LinkedList<String> linkedList;
    }

    /**
     * Queue队列-相关常用方法：
     * LinkedList实现了Queue接口,因为LinkedList进行插入、删除操作效率较高
     boolean offer(E e):将元素追加到队列末尾,若添加成功则返回true。
     E poll():从队首删除并返回该元素。
     E peek():返回队首元素，但是不删除
     */
    @Test
    public void queue_test() {
        Queue<String> queue = new LinkedList<String>();
        //追加元素
        queue.offer("one");
        queue.offer("two");
        queue.offer("three");
        queue.offer("four");
        queue.add("five");
        System.out.println(queue);
        //从队首取出元素并删除
        String poll = queue.poll();
        System.out.println(poll);
        System.out.println(queue);
        //从队首取出元素但是不删除
        String peek = queue.peek();
        System.out.println(peek);
        System.out.println(queue);
        //遍历队列，这里要注意，每次取完元素后都会删除，整个
        //队列会变短，所以只需要判断队列的大小即可
        while(queue.size() > 0) {
            System.out.println(queue.poll());
        }
    }

    /**
     * 双向队列(Deque),是Queue的一个子接口，双向队列是指该队列两端的元素既能入队(offer)也能出队(poll),
     * 如果将Deque限制为只能从一端入队和出队，则可实现栈的数据结构。对于栈而言，有入栈(push)和出栈(pop)，遵循先进后出原则

     常用方法如下：
     void push(E e):将给定元素”压入”栈中。存入的元素会在栈首。即:栈的第一个元素
     E pop():将栈首元素删除并返回。
     */
    @Test
    public void deque_test() {
        Deque<String> deque = new LinkedList<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);
        //获取栈首元素后，元素不会出栈
        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);
        while(deque.size() > 0) {
            //获取栈首元素后，元素将会出栈
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }

}
