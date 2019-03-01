package com.hy.singleton;

/**
 * Created by huangyong on 2019/3/1
 */
public class SingletonTest {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.INSTANCE;
        Singleton singleton2 = Singleton.INSTANCE;

        System.out.println("测试是不是单例：" + (singleton1 == singleton2));
    }
}
