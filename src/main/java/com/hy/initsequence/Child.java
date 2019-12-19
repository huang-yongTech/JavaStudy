package com.hy.initsequence;

/**
 * Created by huangyong on 2019/2/21
 */
public class Child extends Parent {
    static {
        System.out.println("Child中静态代码快初始化");
    }

    {
        System.out.println("Child中非静态代码快初始化");
    }

    public String childStr = "Child中变量";

    public Child() {
        System.out.println("Child构造器初始化");
        System.out.println(childStr);
    }
}
