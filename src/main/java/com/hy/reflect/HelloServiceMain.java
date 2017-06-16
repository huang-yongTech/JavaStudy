package com.hy.reflect;

/**
 * Created by huangyong on 2017/3/24.
 * 测试用
 */
public class HelloServiceMain {
    public static void main(String[] args){
        HelloServiceProxy proxy = new HelloServiceProxy();
        HelloService service = (HelloService) proxy.bind(new HelloServiceImpl());
        service.sayHello("张三");
    }
}
