package chapter10.arrays.other.kruskal;

import chapter7.graph.Edge;

/**
 * Created by lili on 2017/7/1.
 */
//例10.4  以Kruskal算法构造图10.9所示带权无向图的最小生成树。

public class WeightedUndiG1010_Kruskal
{
    public static void main(String args[])
    {
        //图10.10所示带权无向图的边集合（每边只表示一次）
        Edge edges[]={new Edge(0,1,16),new Edge(0,2,18),new Edge(0,3,5),
                new Edge(1,2,7),new Edge(1,4,3),
                new Edge(2,3,15),new Edge(2,4,26),new Edge(2,5,4),
                new Edge(3,5,8),new Edge(4,5,6)};
        MinSpanTree mstree = new MinSpanTree(6, edges, new EdgeComparator());
        System.out.println("图10.10带权无向图，"+mstree.toString());
    }
}
/*
程序运行结果如下：
并查集：(-1, -1, -1, -1, -1, -1) ，最小堆：((1,4,3), (2,5,4), (2,3,15), (0,3,5), (4,5,6), (0,1,16), (2,4,26), (0,2,18), (3,5,8), (1,2,7))
插入边(1,4,3)，并查集：(-1, -2, -1, -1, 1, -1)
插入边(2,5,4)，并查集：(-1, -2, -2, -1, 1, 2)
插入边(0,3,5)，并查集：(-2, -2, -2, 0, 1, 2)
插入边(4,5,6)，并查集：(-2, -4, 1, 0, 1, 2)
插入边(3,5,8)，并查集：(-6, 0, 1, 0, 1, 2)
图10.10带权无向图，最小生成树的边集合：(1,4,3) (2,5,4) (0,3,5) (4,5,6) (3,5,8) ，最小代价为26


//按照折叠规则压缩路径
最小边(1,2,7)，最小边(3,5,8)，插入边(3,5,8)，并查集：(1, -6, 1, 0, 1, 1)
*/

