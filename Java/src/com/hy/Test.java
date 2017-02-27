package com.hy;

/**
 * Created by huangyong on 2017/2/7.
 * 测试
 */
public class Test {
    private void test() {
        int i, j;
        if ((i = 1) == 0) {
            System.out.println("i is 0");
        } else {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}
