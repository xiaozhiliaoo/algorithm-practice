package chapter7.graph;

import chapter2.seqlist.SeqList;

/**
 * Created by lili on 2017/7/1.
 */
//7.2   图的表示和实现
//7.2.1   图的邻接矩阵表示和实现
//邻接矩阵表示的图类

public class AdjMatrixGraph<T> extends AbstractGraph<T>    //邻接矩阵表示的图类，继承抽象图类
{
    protected SeqList<T> vertexlist;                       //顺序表存储图的顶点集合
    protected int[][] adjmatrix;                           //图的邻接矩阵
//    private static final int MAX_WEIGHT=99999;             //Integer.MAX_VALUE; //最大权值（表示无穷大∞）

    //构造空图，size指定顶点顺序表的容量和邻接矩阵二维数组的容量
    public AdjMatrixGraph(int size)
    {
        size = size<10 ? 10 : size;
        this.vertexlist = new SeqList<T>(size);            //构造容量为size的空顺序表，当前顶点数为0
        this.adjmatrix = new int[size][size];
        for (int i=0; i<size; i++)                         //初始化图的邻接矩阵
            for (int j=0; j<size; j++)
                this.adjmatrix[i][j]= (i==j) ? 0 : MAX_WEIGHT;  //边的权值为0或最大权值
    }

    //以顶点集合和边集合构造一个图。若vertices或edges为null，抛出空对象异常
    public AdjMatrixGraph(T[] vertices, Edge[] edges)
    {
        this(vertices.length);                             //构造指定容量的空图
        for (int i=0; i<vertices.length; i++)
            insertVertex(vertices[i]);                     //插入一个顶点
        for (int j=0; j<edges.length; j++)
            insertEdge(edges[j]);                          //插入一条边
    }
    public AdjMatrixGraph(T[] vertices)                    //构造指定顶点集合、没有边的空图
    {
        this(vertices, new Edge[0]);
    }

    public AdjMatrixGraph(SeqList<T> list, Edge[] edges)   //以顶点集合和边集合构造一个图
    {
        this(list.length());
        this.vertexlist = list;
        if (list==null)                                    //构造空图，没有顶点没有边
            return;
        if (edges!=null)                                   //当edges==null时，构造的图没有边
            for (int j=0; j<edges.length; j++)
                insertEdge(edges[j]);                      //插入一条边
    }

    public int vertexCount()                          //返回顶点数
    {
        return this.vertexlist.length();              //返回顶点顺序表的元素个数
    }
    public T get(int i)                               //返回顶点vi的数据元素，若i指定序号无效则返回null
    {
        return this.vertexlist.get(i);
    }
    public int getWeight(int i, int j)                //返回<vi,vj>边的权值
    {
        return this.adjmatrix[i][j];
    }
    public String toString()                          //返回图的顶点集合和邻接矩阵描述字符串
    {
        String str= "顶点集合："+this.vertexlist.toString()+"\n邻接矩阵:  \n";
        int n = this.vertexCount();                   //顶点数
        for (int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
                str += this.adjmatrix[i][j]==MAX_WEIGHT ? "  ∞" : "  "+this.adjmatrix[i][j];
            str+="\n";
        }
        return str;
    }

    //插入元素值为x的顶点，返回该顶点在顶点顺序表中的序号
    public int insertVertex(T x)
    {
        this.vertexlist.append(x);                         //顺序表追加元素，自动扩充容量
        if (this.vertexCount()>this.adjmatrix.length)      //若二维数组容量不足，则扩充
        {
            int temp[][]=adjmatrix, i, j;
            this.adjmatrix = new int[temp.length*2][temp.length*2]; //二维数组容量扩充2倍
            for (i=0; i<temp.length; i++)
            {
                for (j=0; j<temp.length; j++)              //复制原邻接矩阵
                    this.adjmatrix[i][j] = temp[i][j];
                for (j=temp.length; j<temp.length*2; j++)
                    this.adjmatrix[i][j] = MAX_WEIGHT;
            }
            for (i=temp.length; i<temp.length*2; i++)      //初始化扩充的邻接矩阵
                for (j=0; j<temp.length*2; j++)
                    this.adjmatrix[i][j] = (i==j) ? 0 : MAX_WEIGHT;
        }
        return this.vertexlist.length()-1;                 //返回插入顶点序号
    }

    //插入一条权值为weight的边〈vi,vj〉，若该边已存在，则不插入
    public void insertEdge(int i, int j, int weight)
    {
        int n=this.vertexCount();
        if (i>=0 && i<n && j>=0 && j<n && i!=j && adjmatrix[i][j]==MAX_WEIGHT)
            this.adjmatrix[i][j]=weight;
    }
    public void insertEdge(Edge edge)                      //插入一条边
    {
        this.insertEdge(edge.start, edge.dest, edge.weight);
    }

    public void removeEdge(int i, int j)                   //删除边〈vi,vj〉，i、j指定顶点序号
    {
        int n=this.vertexCount();
        if (i>=0 && i<n && j>=0 && j<n && i!=j)
            this.adjmatrix[i][j]=MAX_WEIGHT;               //设置该边的权值为无穷大
    }

    public void removeVertex(int i)                        //删除顶点vi及其关联的边
    {
        int n=this.vertexCount();                          //顶点数
        if (i<0 || i>n)
            return;
        this.vertexlist.remove(i);                         //删除顺序表的第i个元素，顶点数已减一
        for (int j=0; j<i; j++)
            for (int k=i+1; k<n; k++)
                this.adjmatrix[j][k-1] = this.adjmatrix[j][k];  //元素左移一列
        for (int j=i+1; j<n; j++)
            for (int k=0; k<i; k++)
                this.adjmatrix[j-1][k] = this.adjmatrix[j][k];  //元素上移一行
        for (int j=i+1; j<n; j++)
            for (int k=i+1; k<n; k++)
                this.adjmatrix[j-1][k-1] = this.adjmatrix[j][k]; //元素左上移一行一列
    }
    public void removeVertex(T vertex)           //删除顶点vertex及其关联的边
    {
        int i=this.vertexlist.indexOf(vertex);   //在顺序表中查找值为vertex的元素，返回序号，算法见第8章
        this.removeVertex(i);                    //删除顶点vi及其关联的边
    }

    //7.3   图的遍历
    //返回vi在vj后的下一个邻接顶点序号 ，当j=-1时，vi的第一个邻接顶点序号，若不存在下一个邻接顶点，则返回-1
    public int getNextNeighbor(int i, int j)
    {
        int n=this.vertexCount();
        if (i>=0 && i<n && j>=-1 && j<n && i!=j)
            for (int k=j+1; k<n; k++)                      //当j=-1时，k从0开始寻找下一个邻接顶点
                if (this.adjmatrix[i][k]>0 && this.adjmatrix[i][k]<MAX_WEIGHT)
                    return k;
        return -1;
    }

    //习题7
    public int edgeCount()                                 //返回边数
    {
        int n=this.vertexlist.length();                    //顶点数
        int count=0;
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++)
                if (i!=j && this.adjmatrix[i][j]!=MAX_WEIGHT)
                    count++;
        return count;
    }

    public int indegree(int i)                             //返回顶点vi的入度
    {
        int n=this.vertexlist.length();                    //顶点数
        int degree=0;
        for (int j=0; j<n; j++)                            //第i列上各元素之和是顶点vi的入度
            if (i!=j && this.adjmatrix[j][i]!=MAX_WEIGHT)
                degree++;
        return degree;
    }
    public int outdegree(int i)                            //返回顶点vi的出度
    {
        int n=this.vertexlist.length();                    //顶点数
        int degree=0;
        for (int j=0; j<n; j++)                            //第i行上各元素之和是顶点vi的出度
            if (i!=j && this.adjmatrix[i][j]!=MAX_WEIGHT)
                degree++;
        return degree;
    }
}

/*
 * 未实现
//求带权图的所有边的权值之和??
    public AdjMatrixGraph(T[] vertices)               //以顶点集合构造完全图
*/


