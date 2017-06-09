package com.hy.grath;

import java.util.Scanner;

/**
 * Created by huangyong on 2016/12/29.
 * 最小生成树prim算法
 */
public class Prim {
    private int votes;//图的顶点数量
    private int edges;//图的边数量
    private Edge[] edge;//保存最小生成树的每条边的数组

    private Prim() {
    }

    private Prim(int votes, int edges, Edge[] edge) {
        this.votes = votes;
        this.edges = edges;
        this.edge = edge;
    }

    public static void main(String[] args) {
        Prim prim = new Prim();
        prim.init();
    }

    private void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("最小生成树prim算法：");
        System.out.println("请输入图的顶点数和边数：");

        while (scanner.hasNext()) {
            votes = scanner.nextInt();//顶点数
            edges = scanner.nextInt();//边数
            if (votes == 0 || edges == 0)
                break;

            edge = new Edge[edges];

            for (int i = 0; i < edges; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int weight = scanner.nextInt();
                edge[i] = new Edge((char) (from + 97), (char) (to + 97), weight);
            }

            if ("finish".equals(scanner.next()))
                break;
        }

        Prim prim = new Prim(votes, edges, edge);
        Edge[] ee = prim.prim();//运行prim算法

        if (ee != null) {
            for (int i = 0; i < votes - 1; i++) {
                System.out.println("start:" + ee[i].start + " end:" + ee[i].end + " weight:" + ee[i].weight);
            }
        }
    }

    //计算边的权值
    private int weight(int a, int b) {
        char start = (char) (a + 97);
        char end = (char) (b + 97);
        for (int i = 0; i < edges; i++) {//循环必须要小于边的总数
            if (edge[i].start == start && edge[i].end == end) {
                return edge[i].weight;
            }
            if (edge[i].start == end && edge[i].end == start) {
                return edge[i].weight;
            }
        }
        return Integer.MAX_VALUE;//如果权值无法计算，则返回最大值，表示没有连通
    }

    //最小生成树prim算法
    private Edge[] prim() {
        Edge[] edge = new Edge[votes];//存放最小生成树的 votes-1 条边
        //下面表示加入最小生成树的节点，从数组元素0到n-1分别对应a到(char)(n-1+97)
        //vote_smt[i]=1表示已经加入最小生成树，为0表示未加入
        int[] vote_smt = new int[votes];

        for (int i = 0; i < votes; i++) {
            vote_smt[i] = 0;
        }
        vote_smt[0] = 1;//设置初始节点 a 并加入最小生成树

        for (int i = 0; i < votes - 1; i++) {//最外面的循环只是用来保存最小生成树的边，里面的两个循环是判断符合prim算法的边
            //加入一条边，这条边的一个节点在smt中，另一个节点不在smt中而且具有最小权值
            int add_vote = 0;//保存选中的节点，初始为0表示该节点未被选中
            int min_weight = Integer.MAX_VALUE;//设置边的最小权值，初始表示为无穷大
            Edge e = new Edge(' ', ' ', 0);//用于保存符合最小生成树的边
            for (int j = 0; j < votes; j++) {
                if (vote_smt[j] == 1) {
                    for (int k = 0; k < votes; k++) {
                        //如果k节点未被选中，并且 边 j-k 的权值小于最小权值
                        if (vote_smt[k] == 0 && weight(j, k) < min_weight) {
                            add_vote = k;
                            min_weight = weight(j, k);
                            e.start = (char) (j + 97);
                            e.end = (char) (k + 97);
                            e.weight = weight(j, k);
                        }
                    }
                }
            }
            vote_smt[add_vote] = 1;
            edge[i] = e;//将符合条件的边保存到数组中
        }

        return edge;
    }

    private static class Edge {
        private char start;//起点
        private char end;//终点
        private int weight;//权值

        private Edge(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
