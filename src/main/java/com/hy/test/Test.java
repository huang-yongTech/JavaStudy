package com.hy.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyong on 2018/4/1
 */
public class Test<T> {
    private T item;

    public Test(T item) {
        this.item = item;
    }

    private void writeTo(List<? super T> list) {
        list.add(item);
    }

    public static void main(String[] args) {
        Test<Fruit> fruitTest = new Test<>(new Orange());
        List<Fruit> list = new ArrayList<>();
        fruitTest.writeTo(list);
    }

    private void get(List<? extends Apple> list) {
        Fruit fruit = list.get(0);
        Orange orange = (Orange) list.get(0);//并不知道具体是哪一个子类，如果要想使用，必须进行类型转换
    }

    private void add(List<? super Apple> list) {
        list.add((Apple) new Fruit());//所有apple的父类均可以放进去，但是并不知道具体是哪一个父类，如果要想使用，必须进行类型转换
        list.add(new Orange());
    }
}
