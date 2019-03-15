package com.hy;

public class TestString {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        String s1 = "ab";
        //只有字符串参与运算，编译器自动优化，直接引用已经存在的缓存
        String s2 = "a" + "b";
        System.out.println(s1 == s2);
    }

    private static void test2() {
        String s1 = "ab";
        String s2 = "b";
        //有变量参与运算，产生新的对象
        String s3 = "a" + s2;
        System.out.println(s1 == s3);
    }
}
