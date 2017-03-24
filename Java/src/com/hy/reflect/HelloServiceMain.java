package com.hy.reflect;

/**
 * Created by huangyong on 2017/3/24.
 * 测试用
 */
public class HelloServiceMain {
    public static void main(String[] args){
        HelloServiceProxy proxy = new HelloServiceProxy();
        HelloService helloService = (HelloService) proxy.bind(new HelloServiceImpl());
        helloService.sayHello("张三");
    }
}
