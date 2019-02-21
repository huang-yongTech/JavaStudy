package com.hy.initsequence;

/**
 * Created by huangyong on 2019/2/21
 */
public class Parent {
    static {
        System.out.println("Parent中静态代码快初始化");
    }

    {
        System.out.println("Parent中非静态代码快初始化");
    }

    private String str = "Parent中变量";

    public Parent() {
        System.out.println("Parent构造器初始化");
        System.out.println(str);
    }
}
