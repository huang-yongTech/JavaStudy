package com.hy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by huangyong on 2017/1/6.
 * 拓扑排序算法
 */
public class TopologicalSort {
    //邻接表中表对应的链表的顶点
    private class EdgeNode {
        private int indexVertex;//该边所指向的顶点的位置
        private EdgeNode nextEdge;//指向下一条边的指针
    }

    //邻接表中表的顶点
    private class VertexNode {
        private char vertexData;//顶点数据
        private EdgeNode firstEdge;//第一条由该顶点引出的边
    }

    private VertexNode[] mVertexNode;//顶点数组

    /**
     * 拓扑排序
     * <p>
     * 返回值：
     * -1 -- 失败(由于内存不足等原因导致)
     * 0 -- 成功排序，并输入结果
     * 1 -- 失败(该有向图是有环的)
     */
    private int topoSort() {
        int index = 0;
        int num = mVertexNode.length;
        int[] indegrees = new int[num];//入度数组
        char[] topoOuts = new char[num];//拓扑排序结果数组，保存每个节点的序号
        Queue<Integer> queue = new LinkedList<>();//辅助队列

        //统计每个顶点的入度数
        for (VertexNode aMVertexNode : mVertexNode) {
            EdgeNode edgeNode = aMVertexNode.firstEdge;
            if (edgeNode != null) {
                indegrees[edgeNode.indexVertex]++;
                edgeNode = edgeNode.nextEdge;
            }
        }

        //将所有入度为0的顶点入队列
        for (int i = 0; i < num; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
            //如果队列非空
            while (!queue.isEmpty()) {
                int j = queue.poll();//出队列，j是顶点的序号
                topoOuts[index++] = mVertexNode[i].vertexData;//将该顶点添加到topoOuts中，topoOuts是排序结果
                EdgeNode node = mVertexNode[i].firstEdge;//获取以该顶点为起点的出边队列

                //将与node节点关联的顶点入度减1
                //若减1后，该节点的入度为0，则将该顶点加入到排序结果队列中
                while (node != null) {
                    //将节点（序号为node.indexVertex）的入度减一
                    indegrees[node.indexVertex]--;
                    //若节点的入度为0，则将其加入到队列中
                    if (node.indexVertex == 0) {
                        queue.offer(node.indexVertex);
                    }

                    node = node.nextEdge;
                }
            }
        }

        if (index != num) {
            System.out.println("该图有环路，拓扑排序失败！");
            return 1;
        }

        return 0;
    }
}
