package com.hy.lock;

/**
 * Created by huangyong on 2020/7/14
 * description :
 */
public class TestLock {

    public static void main(String[] args) {
        SharedRes res1 = new SharedRes();
        SharedRes res2 = new SharedRes();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                res1.print2();
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                res1.print2();
            }
        };

        new Thread(runnable1).start();
        new Thread(runnable2).start();
    }
}
