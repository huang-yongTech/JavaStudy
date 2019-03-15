package com.hy.modifier;

/**
 * Created by huangyong on 2019/3/15
 * 默认权限可以在同一个包中的其他类访问
 */
public class Parent {
    String name;
    String address;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }
}
