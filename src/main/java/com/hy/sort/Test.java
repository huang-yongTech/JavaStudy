package com.hy.sort;

/**
 * Created by huangyong on 2017/6/5.
 * 排序测试代码
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {79, 61, 90, 12, 54, 25, 48, 36, 87, 25, 10, 7, 2, 10, 8, 102, 56};
        SortFun.quickSort(arr);

        System.out.print("排序后结果：");
        for (int anArr : arr) {
            System.out.print(" " + anArr);
        }
        System.out.println();
    }
}
