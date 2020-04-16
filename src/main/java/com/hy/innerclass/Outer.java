package com.hy.innerclass;

/**
 * Created by huangyong on 2020/4/16
 */
public class Outer {
    private String name = "outer name";

    public class Inner implements IClass {

        @Override
        public void print() {
            System.out.println(name);
        }
    }
}
