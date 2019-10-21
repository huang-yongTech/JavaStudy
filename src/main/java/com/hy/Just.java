package com.hy;


import java.util.Comparator;
import java.util.TreeSet;

/**
 * HashMap的无序是指数据存入和取出来的顺序不一样，但是HashMap的数据取出还是按照一定顺序的
 */
public class Just {

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(4);
        treeSet.add(5);
        treeSet.add(6);
        treeSet.add(7);

        for (Integer integer : treeSet) {
            System.out.println(integer);
        }
    }
}
