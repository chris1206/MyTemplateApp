package com.yto.template.mytemplateapp;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by Chris on 2018/5/16.
 */

public class ExecutorTest {

    //创建一个线程池
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Test
    public void futureDemo(){
        try {
            futureWithRunnable();
            futureWithCallable();
            futureTask();
        }catch (Exception e){}
    }

    private void futureTask() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return fibc(20);
            }
        });
        executorService.submit(futureTask);
        System.out.println("future result from futureTask: " + futureTask.get());
    }

    private void futureWithCallable() throws ExecutionException, InterruptedException {
        Future<Integer> result = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return fibc(20);
            }
        });
        System.out.println("future result from callable:" + result.get());
    }

    private void futureWithRunnable() throws InterruptedException,
            ExecutionException {
        Future<?> result = executorService.submit(new Runnable() {
            @Override
            public void run() {
                fibc(20);
            }
        });
        System.out.println("future result from runnable:" + result.get());
    }

    private int fibc(int num) {
        if(num == 0) {
            return 0;
        }
        if(num ==1){
            return 1;
        }
        return fibc(num-1) + fibc(num-2);
    }


}
