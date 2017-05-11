package com.hy.Test;

import java.util.ArrayList;

/**
 * Created by huangyong on 2017/5/11.
 * 测试Set的添加
 */
public class Test1 {
    private final Object object = new Object();

    private void addAndShow(){
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(object);
        arrayList.add(object);
        arrayList.add(object);

        System.out.println(arrayList);
    }

    public static void main(String[] args){
        Test1 test1 = new Test1();
        test1.addAndShow();
    }
}
