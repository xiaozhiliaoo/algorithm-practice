package chapter7.graph;

/**
 * Created by lili on 2017/7/1.
 */
//【例7.2】  带权有向图的构造、插入及删除操作。

public class WeightedDirectedG4
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //带权有向图G4的顶点集合
        Edge edges[]={new Edge(0,1,5), new Edge(0,3,2),
                new Edge(1,0,6), new Edge(1,2,7),
                new Edge(2,4,3), new Edge(3,2,8), new Edge(3,4,9)};   //G4的出边集合
//        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
        AdjListGraph<String> graph = new AdjListGraph<String>(vertices, edges);
//        System.out.println("带权有向图G4，"+graph.toString());

/*        System.out.println("深度优先遍历非连通图：");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.DFSTraverse(i);
*/
        System.out.println("广度优先遍历非连通图：");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.BFSTraverse(i);

/*        System.out.println("插入顶点F，插入边<A,F,20>，删除顶点C，删除边<D,E>");
        int i=graph.insertVertex("F");
        graph.insertEdge(0,i,20);                          //插入边<A,F,20>
        graph.removeVertex(2);                             //删除顶点C
        graph.removeEdge(2,3);                             //删除边<D,E>
        System.out.println(graph.toString());
*/
//        for (int i=0; i<graph.vertexCount(); i++)
//            graph.shortestPath(i);               //顶点vi的单源最短路径，Dijkstra算法
    }
}

/*
程序运行结果如下：
带权有向图G4，顶点集合：(A, B, C, D, E)
邻接矩阵:
  0  5  ∞  2  ∞
  6  0  7  ∞  ∞
  ∞  ∞  0  ∞  3
  ∞  ∞  8  0  9
  ∞  ∞  ∞  ∞  0
插入顶点F，插入边(A,F,20)，删除顶点v2，删除边(v2,v3)
顶点集合：(A, B, D, E, F)
邻接矩阵:
  0  5  2  ∞  20
  6  0  ∞  ∞  ∞
  ∞  ∞  0  ∞  ∞
  ∞  ∞  ∞  0  ∞
  20  ∞  ∞  ∞  0

深度优先遍历非连通图：
{ A B C E D }
{ B A D C E }
{ C E } { D } { A B }
{ D C E } { A B }
{ E } { A B C D }
广度优先遍历非连通图：
{ A B D C E }
{ B A C D E }
{ C E } { D } { A B }
{ D C E } { A B }
{ E } { A B D C }

*/

/*
程序运行结果如下：
带权有向图G4，出边表：
(
A：((0,1,5), (0,3,2)),
B：((1,0,6), (1,2,7)),
C：((2,4,3)),
D：((3,2,8), (3,4,9)),
E：())

深度优先遍历非连通图：
{ A B C E D }
{ B A D C E }
{ C E } { D } { A B }
{ D C E } { A B }
{ E } { A B C D }
广度优先遍历非连通图：
{ A B D C E }
{ B A C D E }
{ C E } { D } { A B }
{ D C E } { A B }
{ E } { A B D C }
插入顶点F，插入边<A,F,20>，删除顶点C，删除边<D,E>
出边表：
(
A：((0,1,5), (0,2,2), (0,4,20)),
B：((1,0,6)),
D：(),
E：(),
F：())
*/

/*
 //最短路径
vset数组{1,0,0,0,0}	path数组{-1,0,-1,0,-1}	dist数组{0,5,2147483647,2,2147483647}
vset数组{1,0,0,1,0}	path数组{-1,0,3,0,3}	dist数组{0,5,10,2,11}
vset数组{1,1,0,1,0}	path数组{-1,0,3,0,3}	dist数组{0,5,10,2,11}
vset数组{1,1,1,1,0}	path数组{-1,0,3,0,3}	dist数组{0,5,10,2,11}
vset数组{1,1,1,1,1}	path数组{-1,0,3,0,3}	dist数组{0,5,10,2,11}
从顶点A到其他顶点的最短路径如下：
(A,B)长度为5
(A,D,C)长度为10
(A,D)长度为2
(A,D,E)长度为11

vset数组{0,1,0,0,0}	path数组{1,-1,1,-1,-1}	dist数组{6,0,7,2147483647,2147483647}
vset数组{1,1,0,0,0}	path数组{1,-1,1,0,-1}	dist数组{6,0,7,8,2147483647}
vset数组{1,1,1,0,0}	path数组{1,-1,1,0,2}	dist数组{6,0,7,8,10}
vset数组{1,1,1,1,0}	path数组{1,-1,1,0,2}	dist数组{6,0,7,8,10}
vset数组{1,1,1,1,1}	path数组{1,-1,1,0,2}	dist数组{6,0,7,8,10}
从顶点B到其他顶点的最短路径如下：
(B,A)长度为6
(B,C)长度为7
(B,A,D)长度为8
(B,C,E)长度为10

vset数组{0,0,1,0,0}	path数组{-1,-1,-1,-1,2}	dist数组{2147483647,2147483647,0,2147483647,3}
vset数组{0,0,1,0,1}	path数组{-1,-1,-1,-1,2}	dist数组{2147483647,2147483647,0,2147483647,3}
从顶点C到其他顶点的最短路径如下：
(C,A)长度为∞
(C,B)长度为∞
(C,D)长度为∞
(C,E)长度为3

vset数组{0,0,0,1,0}	path数组{-1,-1,3,-1,3}	dist数组{2147483647,2147483647,8,0,9}
vset数组{0,0,1,1,0}	path数组{-1,-1,3,-1,3}	dist数组{2147483647,2147483647,8,0,9}
vset数组{0,0,1,1,1}	path数组{-1,-1,3,-1,3}	dist数组{2147483647,2147483647,8,0,9}
从顶点D到其他顶点的最短路径如下：
(D,A)长度为∞
(D,B)长度为∞
(D,C)长度为8
(D,E)长度为9

vset数组{0,0,0,0,1}	path数组{-1,-1,-1,-1,-1}	dist数组{2147483647,2147483647,2147483647,2147483647,0}
从顶点E到其他顶点的最短路径如下：
(E,A)长度为∞
(E,B)长度为∞
(E,C)长度为∞
(E,D)长度为∞

*/

