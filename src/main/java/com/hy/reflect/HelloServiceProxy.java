package com.hy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by huangyong on 2017/2/7.
 * 测试
 */
public class HelloServiceProxy implements InvocationHandler {
    private Object target;

    Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我准备说hello");
        method.invoke(target, args);
        System.out.println("我说过hello了");
        return null;
    }
}
