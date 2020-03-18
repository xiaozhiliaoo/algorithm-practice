package chapter10.arrays.other.kruskal;

/**
 * Created by lili on 2017/7/1.
 */

import chapter10.arrays.other.minheap.MinHeap;
import chapter7.graph.Edge;

import java.util.Comparator;
//最小生成树类，存储一个带权无向图最小生成树的边集合，以及最小代价
public class MinSpanTree
{
    private Edge[] mst;                                    //存储最小生成树的边集合
    private int cost=0;                                    //最小生成树代价

    //以Kruskal算法构造带权图的最小生成树并求代价，使用最小堆和并查集
    //参数n指定顶点数，edges数组指定图的所有边（每边只表示一次），comparator指定边的比较器对象
//    public MinSpanTree(AbstractGraph<T> graph, Comparator<Edge> comparator)
    public MinSpanTree(int n, Edge[] edges, Comparator<Edge> comparator)
    {
        MinHeap<Edge> minheap = new MinHeap<Edge>(edges, comparator);  //使用最小堆存储一个图的所有边，边按权值比较大小
        mst = new Edge[n-1];                               //mst存储最小生成树的边集合，边数为顶点数-1
        UnionFindSet ufset = new UnionFindSet(n);          //并查集
        System.out.println("并查集："+ufset.toString()+"，最小堆："+minheap.toString());
        int i=0;                                           //最小生成树中当前边的序号
        for (int j=0; j<n; j++)                            //共选出“顶点数-1”条边
        {
            Edge minedge = minheap.removeMin();            //删除最小堆的根，返回最小权值的边
            System.out.print("最小边"+minedge.toString()+"，");
            if (ufset.union(minedge.start, minedge.dest))  //若最小权值边的起点和终点所在的两个集合合并
            {
                this.mst[i++]=minedge;                     //该边加入最小生成树
                this.cost+=minedge.weight;                 //计算最小生成树的代价
                System.out.println("插入边"+minedge.toString()+"，"+"并查集："+ufset.toString());
            }
        }
    }

    public String toString()                               //返回最小生成树边集合的描述字符串
    {
        String str="最小生成树的边集合：";
        for (int i=0; i<mst.length; i++)
            str+=mst[i]+" ";
        return str+"，最小代价为"+this.cost;
    }
    private static void print(boolean table[])                          //输出一维数组，用于输出一个解
    {
        System.out.print("("+table[0]);
        for (int i=1; i<table.length; i++)
            System.out.print(","+table[i]);
        System.out.println(")");
    }
}
/*

 ??   //以Prim算法构造带权图的最小生成树并求代价，//使用最小堆
    //参数n指定顶点数, edges数组指定图的所有边
    public MinSpanTree(int n, Edge[] edges, Comparator<Edge> comp)
    {
        MinHeap<Edge> minheap = new MinHeap<Edge>(edges, comp);  //使用最小堆存储一个图的所有边，边按权值比较大小
        System.out.println("最小堆："+minheap.toString());
        mst = new Edge[n-1];                               //mst存储最小生成树的边集合，边数为顶点数-1
        boolean vertmst[]=new boolean[n];
        print(vertmst);
        int i=0;                                           //最小生成树中当前边的序号
        vertmst[i]=true;   //查找最小权值边的起点所在的集合
        for (int j=1; j<n; j++)                            //共选出“顶点数-1”条边
        {
            Edge minedge = minheap.removeMin();            //删除最小堆的根，返回最小权值的边
            if (!vertmst[minedge.dest])   //最小权值边的起点和终点不在一个集合
            {
               //vertmst[minedge.start]=true;   //查找最小权值边的起点所在的集合
                vertmst[minedge.dest]=true;   //查找最小权值边的起点所在的集合
                this.mst[i++]=minedge;                     //该边加入最小生成树
                this.cost+=minedge.weight;                 //计算最小生成树的代价
                System.out.print("插入边"+minedge.toString()+"，");
                print(vertmst);
            }
        }
    }

*/

