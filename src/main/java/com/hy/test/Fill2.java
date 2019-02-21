package com.hy.test;

/**
 * Created by huangyong on 2018/4/1
 */
public class Fill2 {
    public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++) {
            try {
                addable.add(classToken.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
