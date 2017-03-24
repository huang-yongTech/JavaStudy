package com.hy.reflect;

/**
 * Created by huangyong on 2017/3/24.
 * 测试用
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
