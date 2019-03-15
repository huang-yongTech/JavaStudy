package com.hy.modifier;

/**
 * Created by huangyong on 2019/3/15
 */
public class ParentTest {
    /**
     * protect 和 private不可以被用来修饰类，他们只能用来修饰类的成员
     */
    public static void main(String[] args) {
        PublicParent publicParent = new PublicParent();
        publicParent.setAddress("111");

        PrivateParent privateParent = new PrivateParent();

        ProtectParent protectParent = new ProtectParent();
        protectParent.setAddress("shanghai");

        Parent parent = new Parent();
        parent.setAddress("234");
    }
}
