package chapter10.arrays.other.kruskal;

import chapter7.graph.Edge;

/**
 * Created by lili on 2017/7/1.
 */
//EdgeComparator类实现比较器接口，提供图的边类Edge按权值比较大小的方法
class EdgeComparator implements java.util.Comparator<Edge>
{
    public int compare(Edge e1, Edge e2)         //图的边按权值比较大小，升序排序
    {
        return (int)(e1.weight - e2.weight);
    }
}

