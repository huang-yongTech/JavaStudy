package com.hy.grath;

import java.util.ArrayList;

/**
 * Created by huangyong on 2016/12/30.
 * 最小生成树算法之kruskal算法
 */
public class Kruskal {
    private int[] node;//该数组用来顶点组成的集合，用数字表示

    public static void main(String[] args) {
        int n = 6;
        EdgeNode e1 = new EdgeNode(1, 2, 6);
        EdgeNode e2 = new EdgeNode(1, 3, 1);
        EdgeNode e3 = new EdgeNode(1, 4, 5);
        EdgeNode e4 = new EdgeNode(2, 3, 5);
        EdgeNode e5 = new EdgeNode(3, 4, 5);
        EdgeNode e6 = new EdgeNode(2, 5, 3);
        EdgeNode e7 = new EdgeNode(3, 5, 6);
        EdgeNode e8 = new EdgeNode(5, 6, 6);
        EdgeNode e9 = new EdgeNode(3, 6, 4);
        EdgeNode e10 = new EdgeNode(4, 6, 2);

        ArrayList<EdgeNode> nodeArrayList = new ArrayList<>();
        nodeArrayList.add(e10);
        nodeArrayList.add(e9);
        nodeArrayList.add(e8);
        nodeArrayList.add(e7);
        nodeArrayList.add(e6);
        nodeArrayList.add(e5);
        nodeArrayList.add(e4);
        nodeArrayList.add(e3);
        nodeArrayList.add(e2);
        nodeArrayList.add(e1);
        //在声明arrayList时，在容量确定的情况下，最好直接声明其容量大小
        ArrayList<EdgeNode> targetArrayList = new ArrayList<>(n);

        Kruskal kruskal = new Kruskal();
        kruskal.kruskal(n, nodeArrayList, targetArrayList);
    }

    /**
     * kruskal最小生成树算法
     *
     * @param n            顶点的数量
     * @param originalList 边的集合
     * @param targetList   保存最小生成树的目标集合
     */
    private void kruskal(int n, ArrayList<EdgeNode> originalList, ArrayList<EdgeNode> targetList) {
        UnionFind unionFind = new UnionFind(n);//初始化UnionFind类，主要是初始化各个顶点所在的集合

        while (originalList.size() > 0) {//最小生成树的n个顶点共有n-1条边
            float minWeight = Float.MAX_VALUE;
            EdgeNode tempNode = null;
            for (EdgeNode edgeNode : originalList) {
                if (edgeNode.weight < minWeight) {
                    minWeight = edgeNode.weight;
                    tempNode = edgeNode;
                }
            }

            if (tempNode != null) {
                int left = unionFind.find(tempNode.left);
                int right = unionFind.find(tempNode.right);

                if (left != right) {
                    targetList.add(tempNode);
                    //将符合条件的边的顶点归属到同一个集合中
                    unionFind.union(n, left, right);
                }
            }

            originalList.remove(tempNode);
        }

        System.out.println("最小生成树大小：" + targetList.size());
        for (EdgeNode edgeNode : targetList) {
            System.out.println("左顶点：" + edgeNode.left + "--右顶点：" + edgeNode.right + "--边的权值：" + edgeNode.weight);
        }
    }

    /**
     * 由联通的边和顶点组成的集合
     */
    private class UnionFind {
        private UnionFind(int n) {
            node = new int[n + 1];//为什么要大于6呢？
            for (int i = 1; i <= n; i++) {
                node[i] = i;//初始化，开始每个顶点所在的集合为为自己的顶点数字
            }
        }

        //找到顶点所属的集合
        private int find(int n) {
            return node[n];
        }

        //将第二个顶点归属到第一个顶点所在的集合（这样做是为了最终将最小生成树的所有顶点归属于同一个集合中）
        private void union(int n, int x, int y) {
            for (int i = 1; i <= n; i++) {
                if (node[i] == x) {
                    node[i] = y;
                }
            }
        }
    }

    private static class EdgeNode {
        private int left;//左顶点
        private int right;//右顶点
        private float weight;//边的权重

        private EdgeNode(int left, int right, float weight) {
            this.left = left;
            this.right = right;
            this.weight = weight;
        }
    }
}
