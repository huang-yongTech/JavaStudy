package com.hy.singleton;

/**
 * Created by huangyong on 2019/3/1
 * 枚举单例模式
 */
public enum Singleton {
    INSTANCE;

    public void print(String s) {
        System.out.println("这是用枚举模式的单例打印的方法：" + s);
    }
}
