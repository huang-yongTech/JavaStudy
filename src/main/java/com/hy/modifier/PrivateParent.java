package com.hy.modifier;

/**
 * Created by huangyong on 2019/3/15
 * <p>
 * private成员只能在本类的内部访问
 */
public class PrivateParent {
    private String name;
    private String address;

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }
}
