package com.hy.Test;

/**
 * Created by huangyong on 2017/5/3.
 * 测试加载顺序用
 */
public class Test extends X{
    Y y;

    static {
        System.out.println("Z static block");
    }

    {
        y = new Y();
        y.show();
    }

    Test() {
        //super
        System.out.println("Z");
    }

    public static void main(String[] args) {
        System.out.println(new Test().xVar);
    }
}

class X {
    static String xVar = "x value";
    Y b = new Y();

    static {
        System.out.println(xVar);
        System.out.println("X static block");
        xVar = "static value";
    }

    X() {
        System.out.println("X");
        System.out.println(xVar);
        xVar = "x value changed!";
    }
}

class Y {
    String yVar = "Y value";

    Y() {
        System.out.println("Y");
        System.out.println(yVar);
        yVar = "y value changed!";
    }

    void show() {
        System.out.println(yVar);
    }
}

