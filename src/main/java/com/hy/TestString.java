package com.hy;

public class TestString {
    public static void main(String[] args) {
        IntegerCompare compare = (a, b) -> {
            return a - b > 0;
        };
        System.out.println(compare.compare(1, 2));

        IntegerCompare compare1 = TestString::compare;
        compare1.compare(2, 3);
    }

    private void test1() {
        String s1 = "ab";
        //只有字符串参与运算，编译器自动优化，直接引用已经存在的缓存
        String s2 = "a" + "b";
        System.out.println(s1 == s2);
    }

    private static boolean compare(int a, int b) {
        return a - b > 0;
    }

    private void test2() {
        String s1 = "ab";
        String s2 = "b";
        //有变量参与运算，产生新的对象
        String s3 = "a" + s2;
        System.out.println(s1 == s3);
    }

    public interface IntegerCompare {
        boolean compare(int a, int b);
    }
}
