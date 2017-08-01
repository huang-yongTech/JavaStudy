package com.hy.runnable;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangyong on 2017/8/1
 * 测试线程间交换数据的Exchanger
 */
public class ExchangerTest {
    private static Exchanger<String> exchanger = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String a = "银行流水A";
                try {
                    exchanger.exchange(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String b = "银行流水B";
                try {
                    String a = exchanger.exchange(b);
                    System.out.println("A和B之间数据是否一致：" + a.equals(b) + "，A录入的是：" + a + "，B录入的是：" + b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.shutdown();
    }
}
