package com.hy.innerclass;

/**
 * Created by huangyong on 2020/4/16
 */
public class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.print();
    }
}
