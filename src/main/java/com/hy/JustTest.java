package com.hy;

import java.util.Random;

/**
 * HashMap的无序是指数据存入和取出来的顺序不一样，但是HashMap的数据取出还是按照一定顺序的
 */
public class JustTest {

    public static void main(String[] args) {
        System.out.println(test());
    }

    //计算阶乘
    private static void fun() {
        Random random = new Random(1000);
        for (int i = 0; i < 4; i++) {
            int j = random.nextInt();
            System.out.println(j);
        }
    }


    private static int test() {
        int a = 1;
        try {
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            a = 2;
        }
        System.out.println("执行到2");
        return a;
    }
}
