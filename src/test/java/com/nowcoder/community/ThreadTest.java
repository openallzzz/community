package com.nowcoder.community;

import io.lettuce.core.protocol.CompleteableCommand;
import javafx.concurrent.Task;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    /*@Test
    public void test() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("加载图片1....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "图片1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("加载图片2....");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "图片2";
        });

        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2);
        Object o = null;
        try {
            o = future.get();
            System.out.println("最终结果：" + o);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }*/

    Integer stock = new Integer(1);

    class Task implements Runnable {

        String out;

        public Task(String out) {
            this.out = out;
        }

        @Override
        public void run() {
            synchronized (stock) {
                while(true) {
                    System.out.println(out);
                    try {
                        Thread.sleep(100);

                        // 先唤醒其他线程
                        stock.notify();
                        // 自己进入等待，此处会释放 sync 锁
                        stock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Test
    public void test() {
        CompletableFuture.runAsync(new Task("A"));
        CompletableFuture.runAsync(new Task("B"));

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testList() {
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2);

        String s = list.toString();

        // List<String> strings = Arrays.asList(s.substring(1, s.length() - 1).split(", "));
        // System.out.println(s);
        //
        // for(String t :strings) {
        //     System.out.println(Integer.parseInt(t));
        // }

        for (String s1 : s.substring(1, s.length() - 1).split(", ")) {
            System.out.println(s1);
            System.out.println(Integer.parseInt(s1));
        }

        Stack<Integer> stack = new Stack<>();

        stack.pop();

        // System.out.println(strings.size());
        //
        // System.out.println(strings.toString());
    }

}
