package com.hy.modifier1;

import com.hy.modifier.PrivateParent;
import com.hy.modifier.PublicParent;

/**
 * Created by huangyong on 2019/3/15
 */
public class ChildParentTest {
    public static void main(String[] args) {
        PublicParent publicParent = new PublicParent();
        publicParent.setAddress("111");

        PrivateParent privateParent = new PrivateParent();

        ChildProtectParent protectParent = new ChildProtectParent();
//        protectParent.setAddress("shanghai");

        ChildParent parent = new ChildParent();
//        parent.setAddress("234");
    }
}
