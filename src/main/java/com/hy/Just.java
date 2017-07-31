package com.hy;

import com.hy.utils.LogUtil;

/**
 * HashMap的无序是指数据存入和取出来的顺序不一样，但是HashMap的数据取出还是按照一定顺序的
 */
public class Just {

    public static void main(String[] args) {
        LogUtil.getLogger().info("Info测试");
        System.out.println(test());
    }

    public int factorial(int n) {
        if (n < 0)
            throw new RuntimeException("负数没有阶乘");
        if (n <= 1)
            return 1;
        else {
            int sum = 1;
            for (int i = 2; i <= n; i++) {
                sum *= i;
            }
            return sum;
        }
    }

    /**
     * 斐波那契序列
     *
     * @param n 待计算数字
     * @return 斐波那契数
     */
    public int fibonacci(int n) {
        if (n <= 0)
            throw new RuntimeException("斐波那契序列从第一位开始");
        else if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
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
