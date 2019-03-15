package com.hy.modifier;

/**
 * Created by huangyong on 2019/3/15
 * protect成员可以被同一个包中的其他类访问以及包外子类访问
 */
public class ProtectParent {
    protected String name;
    protected String address;

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getAddress() {
        return address;
    }

    protected void setAddress(String address) {
        this.address = address;
    }
}
