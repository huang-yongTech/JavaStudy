package com.hy.modifier1;

import com.hy.modifier.ProtectParent;

/**
 * Created by huangyong on 2019/3/15
 * 包外子类可以访问父类中的protect成员变量以及protect成员方法
 */
public class ChildProtectParent extends ProtectParent {
    public void set1() {
        address = "456";
    }
}
