package chapter7.graph;

/**
 * Created by lili on 2017/7/1.
 */
//【例7.1】 带权无向图的构造、插入及删除操作。
//带权无向图G3 B和D的次序互换??
public class WeightedUndiG302
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //带权无向图G3的顶点集合
        Edge edges[]={new Edge(0,3,5), new Edge(0,1,2),    //G3的边集合
                new Edge(3,0,5), new Edge(3,2,7), new Edge(3,1,6),
                new Edge(2,3,7), new Edge(2,1,8), new Edge(2,4,3),
                new Edge(1,0,2), new Edge(1,3,6), new Edge(1,2,8), new Edge(1,4,9),
                new Edge(4,2,3), new Edge(4,1,9),
                new Edge(0,1,19)};   //重复边
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
//        AdjListGraph<String> graph = new AdjListGraph<String>(vertices, edges);
//        System.out.println("带权无向图G3，"+graph.toString());

/*        //7.3   图的遍历
        System.out.println("深度优先遍历非连通图：");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.DFSTraverse(i);
*/
        System.out.println("广度优先遍历非连通图：");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.BFSTraverse(i);
/*
        System.out.println("插入顶点F，插入边(A,F,20)，删除顶点C，删除边(D,E)");
        int i=graph.insertVertex("F");
        graph.insertEdge(0,i,20);                          //插入边(A,F,20)
        graph.insertEdge(i,0,20);                          //插入边(F,A,20)
        graph.removeVertex(2);                             //删除顶点C
        graph.removeVertex("C");                             //删除顶点C
        graph.removeEdge(2,3);                             //删除边(D,E)
        graph.removeEdge(3,2);                             //删除边(E,D)
        System.out.println(graph.toString());
*/
//        graph.minSpanTree_prim();

/*        for (int i=0; i<graph.vertexCount(); i++)
            graph.shortestPath(i);               //顶点vi的单源最短路径，Dijkstra算法

        /*        //习题7
        System.out.print("有"+graph.edgeCount()+"条边，");
        i=0;
        System.out.println("顶点"+graph.get(i)+"的入度是"+graph.indegree(i)+
                                               "，出度是"+graph.outdegree(i));
*/    }
}

/*
程序运行结果如下：
带权无向图G3，顶点集合：(A, B, C, D, E)
邻接矩阵:
  0  5  ∞  2  ∞
  5  0  7  6  ∞
  ∞  7  0  8  3
  2  6  8  0  9
  ∞  ∞  3  9  0

深度优先遍历非连通图：
{ A B C D E }
{ B A D C E }
{ C B A D E }
{ D A B C E }
{ E C B A D }
广度优先遍历非连通图：
{ A B D C E }
{ B A C D E }
{ C B D E A }
{ D A B C E }
{ E C D B A }
插入顶点F，插入边(A,F,20)，删除顶点C，删除边(D,E)
顶点集合：(A, B, D, E, F)
邻接矩阵:
  0  5  2  ∞  20
  5  0  6  ∞  ∞
  2  6  0  ∞  ∞
  ∞  ∞  ∞  0  ∞
  20  ∞  ∞  ∞  0

*/

/*
程序运行结果如下：
带权无向图G3，出边表：
(
A：((0,1,5), (0,3,2)),
B：((1,0,5), (1,2,7), (1,3,6)),
C：((2,1,7), (2,3,8), (2,4,3)),
D：((3,0,2), (3,1,6), (3,2,8), (3,4,9)),
E：((4,2,3), (4,3,9)))

深度优先遍历非连通图：
{ A B C D E }
{ B A D C E }
{ C B A D E }
{ D A B C E }
{ E C B A D }
广度优先遍历非连通图：
{ A B D C E }
{ B A C D E }
{ C B D E A }
{ D A B C E }
{ E C D B A }
插入顶点F，插入边(A,F,20)，删除顶点C，删除边(D,E)
出边表：
(
A：((0,1,5), (0,2,2), (0,4,20)),
B：((1,0,5), (1,2,6)),
D：((2,0,2), (2,1,6)),
E：(),
F：((5,0,20)))

*/

/*

带权无向图G3，顶点集合：(A, B, C, D, E)
邻接矩阵:
  0  5  ∞  2  ∞
  5  0  7  6  ∞
  ∞  7  0  8  3
  2  6  8  0  9
  ∞  ∞  3  9  0

mst数组初值：(0,1,5)(0,2,2147483647)(0,3,2)(0,4,2147483647)
mst数组：(0,3,2)(3,2,8)(0,1,5)(3,4,9)
mst数组：(0,3,2)(0,1,5)(1,2,7)(3,4,9)
mst数组：(0,3,2)(0,1,5)(1,2,7)(2,4,3)
mst数组：(0,3,2)(0,1,5)(1,2,7)(2,4,3)
最小生成树的边集合：(0,3,2) (0,1,5) (1,2,7) (2,4,3) ，最小代价为17
*/

/*
              //顶点vi的单源最短路径，Dijkstra算法
带权无向图G3，顶点集合：(A, B, C, D, E)
邻接矩阵:
  0  5  ∞  2  ∞
  5  0  7  6  ∞
  ∞  7  0  8  3
  2  6  8  0  9
  ∞  ∞  3  9  0

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
vset数组{0,1,0,0,0}	path数组{1,-1,1,1,-1}	dist数组{5,0,7,6,2147483647}
vset数组{1,1,0,0,0}	path数组{1,-1,1,1,-1}	dist数组{5,0,7,6,2147483647}
vset数组{1,1,0,1,0}	path数组{1,-1,1,1,3}	dist数组{5,0,7,6,15}
vset数组{1,1,1,1,0}	path数组{1,-1,1,1,2}	dist数组{5,0,7,6,10}
vset数组{1,1,1,1,1}	path数组{1,-1,1,1,2}	dist数组{5,0,7,6,10}
从顶点B到其他顶点的最短路径如下：
(B,A)长度为5
(B,C)长度为7
(B,D)长度为6
(B,C,E)长度为10
vset数组{0,0,1,0,0}	path数组{-1,2,-1,2,2}	dist数组{2147483647,7,0,8,3}
vset数组{0,0,1,0,1}	path数组{-1,2,-1,2,2}	dist数组{2147483647,7,0,8,3}
vset数组{0,1,1,0,1}	path数组{1,2,-1,2,2}	dist数组{12,7,0,8,3}
vset数组{0,1,1,1,1}	path数组{3,2,-1,2,2}	dist数组{10,7,0,8,3}
vset数组{1,1,1,1,1}	path数组{3,2,-1,2,2}	dist数组{10,7,0,8,3}
从顶点C到其他顶点的最短路径如下：
(C,D,A)长度为10
(C,B)长度为7
(C,D)长度为8
(C,E)长度为3
vset数组{0,0,0,1,0}	path数组{3,3,3,-1,3}	dist数组{2,6,8,0,9}
vset数组{1,0,0,1,0}	path数组{3,3,3,-1,3}	dist数组{2,6,8,0,9}
vset数组{1,1,0,1,0}	path数组{3,3,3,-1,3}	dist数组{2,6,8,0,9}
vset数组{1,1,1,1,0}	path数组{3,3,3,-1,3}	dist数组{2,6,8,0,9}
vset数组{1,1,1,1,1}	path数组{3,3,3,-1,3}	dist数组{2,6,8,0,9}
从顶点D到其他顶点的最短路径如下：
(D,A)长度为2
(D,B)长度为6
(D,C)长度为8
(D,E)长度为9
vset数组{0,0,0,0,1}	path数组{-1,-1,4,4,-1}	dist数组{2147483647,2147483647,3,9,0}
vset数组{0,0,1,0,1}	path数组{-1,2,4,4,-1}	dist数组{2147483647,10,3,9,0}
vset数组{0,0,1,1,1}	path数组{3,2,4,4,-1}	dist数组{11,10,3,9,0}
vset数组{0,1,1,1,1}	path数组{3,2,4,4,-1}	dist数组{11,10,3,9,0}
vset数组{1,1,1,1,1}	path数组{3,2,4,4,-1}	dist数组{11,10,3,9,0}
从顶点E到其他顶点的最短路径如下：
(E,D,A)长度为11
(E,C,B)长度为10
(E,C)长度为3
(E,D)长度为9

*/
