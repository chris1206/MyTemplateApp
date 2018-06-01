package com.chris.test_lib;

import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by Chris on 2018/5/18.
 */

public class QueueTest {

    @Test
    public void main1() {
        MyArrayBlockingQueue<Integer> aQueue = new MyArrayBlockingQueue<Integer>();
        aQueue.put(3);
        aQueue.put(24);
        for (int i = 0; i < 5; i++) {
            System.out.println(aQueue.take());
        }

        LinkedList<String> linkedList;
    }

}
