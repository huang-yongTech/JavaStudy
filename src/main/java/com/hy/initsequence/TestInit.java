package com.hy.initsequence;

/**
 * Created by huangyong on 2019/12/19
 * 测试集成体系中初始化顺序
 */
public class TestInit {
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
//        System.out.println(Child.str);//只执行父类中的静态代码块
//        new Child();//都执行
//        Parent[] parents = new Parent[10];//什么都不执行，说明父类没有触发初始化阶段
//        System.out.println(Parent.HELLO);//只打印这个常量

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d); //true
        System.out.println(e == f); //false
        System.out.println(c == (a + b)); //true
        System.out.println(c.equals(a + b)); //true
        System.out.println(g == (a + b)); //true
        System.out.println(g.equals(a + b)); //false equals方法不会自动拆箱与装箱，因此两个不同类型的equals比较是直接返回false的
    }
}
