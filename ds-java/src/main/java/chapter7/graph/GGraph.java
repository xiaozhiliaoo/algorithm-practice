package chapter7.graph;

/**
 * Created by lili on 2017/7/1.
 */
//7.1.2   图抽象数据类型
//图接口

public interface GGraph<T>                       //图接口，图抽象数据类型
{
    public static final int MAX_WEIGHT=99999;    //最大权值（表示无穷大∞）
    int vertexCount();                           //返回顶点数
    T get(int i);                                //返回顶点vi的数据元素
    int getWeight(int i, int j);                 //返回<vi,vj>边的权值
    int insertVertex(T x);                       //插入元素值为x的顶点，返回顶点序号
    void insertEdge(int i, int j, int weight);   //插入一条权值为weight的边〈vi,vj〉
    void removeVertex(int i);                    //删除顶点v及其关联的边
    void removeEdge(int i, int j);               //删除边〈vi,vj〉
    int getNextNeighbor(int i, int j);           //返回vi在vj后的下一个邻接顶点序号
    void DFSTraverse(int i);                     //从顶点vi出发对图进行一次深度优先搜索遍历
    void BFSTraverse(int i);                     //从顶点vi出发对图进行一次广度优先搜索遍历
}
//int getFirstNeighbor(int i);                 //返回顶点vi的第一个邻接顶点的序号

