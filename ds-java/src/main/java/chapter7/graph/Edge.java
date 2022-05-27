package chapter7.graph;

/**
 * Created by lili on 2017/7/1.
 */
//7.2   图的表示和实现
//7.2.1   图的邻接矩阵表示和实现
//7.2.2   图的邻接表表示和实现
//带权值的边类

public class Edge implements Comparable<Edge>    //带权值的边类
{
    public int start,dest,weight;                //边的起点序号、终点序号和权值

    public Edge(int start, int dest, int weight)
    {
        this.start = start;
        this.dest = dest;
        this.weight = weight;
    }

    public String toString()                     //返回边描述字符串，形式为“(起点序号,终点序号,权值)”
    {
        return "("+start+","+dest+","+weight+")";
    }

    //比较两条边大小，约定边排序按起点优先规则，只比较起点和终点，不比较权值
    public int compareTo(Edge e)
    {
        if (this.start!=e.start)
            return this.start - e.start;         //当起点不相同时，返回起点的差值
        return this.dest - e.dest;               //当起点相同时，返回终点的差值
    }
}
