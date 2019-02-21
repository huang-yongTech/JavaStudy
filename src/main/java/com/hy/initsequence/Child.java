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

    private String childStr = "Child中变量";

    public Child() {
        System.out.println("Child构造器初始化");
        System.out.println(childStr);
    }

    /**
     * 在继承体系中类初始化顺序
     * 类的初始化从new关键字调用构造方法开始（下面的是按照顺序执行的）：
     * 先初始化父类静态代码块
     * 初始化子类静态代码块
     * 初始化父类非静态代码块
     * 初始化父类构造方法
     * 初始化子类非静态代码快
     * 初始化子类构造方法
     */
    public static void main(String[] args) {
        new Child();
    }
}
