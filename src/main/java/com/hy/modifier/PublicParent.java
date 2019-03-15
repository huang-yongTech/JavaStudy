package com.hy.modifier;

/**
 * Created by huangyong on 2019/3/15
 * <p>
 * 修饰符权限学习
 * <p>
 * public成员可以在任意位置访问
 */
public class PublicParent {
    public String name;
    public String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
