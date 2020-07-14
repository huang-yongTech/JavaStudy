package com.hy.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by huangyong on 2020/7/14
 * description : 模拟共享资源
 */
public class SharedRes {
    public synchronized void print1() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("this is print1!");
    }

    private final Object lock = new Object();

    public void print2() {
        synchronized (lock) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this is print2!");
        }
    }
}
