package chapter7.graph;

import chapter2.seqlist.SeqList;
import chapter2.singlylinkedlist.Node;
import chapter2.singlylinkedlist.SortedSinglyLinkedList;

/**
 * Created by lili on 2017/7/1.
 */
//7.2.2   图的邻接表表示和实现
//图的邻接表类

public class AdjListGraph<T> extends AbstractGraph<T>      //邻接表表示的图类 ，继承抽象图类
{
    protected SeqList<Vertex<T>> vertexlist;               //顶点顺序表

    public AdjListGraph(int size)                          //构造方法，size指定顶点顺序表容量
    {
        size = size<10 ? 10 : size;
        this.vertexlist = new SeqList<Vertex<T>>(size);
    }

    public AdjListGraph(T[] vertices, Edge[] edges)        //以顶点集合和边集合构造一个图
    {
        this(vertices.length*2);                           //构造指定容量的空图，若vertices==null，抛出空对象异常
        for (int i=0; i<vertices.length; i++)
            insertVertex(vertices[i]);                     //插入一个顶点
        if (edges!=null)                                   //当edges==null时，构造的图没有边
            for (int j=0; j<edges.length; j++)
                insertEdge(edges[j]);                      //插入一条边
    }

    public int vertexCount()                               //返回顶点数
    {
        return this.vertexlist.length();
    }

    public T get(int i)                                    //返回顶点vi的数据元素，若i指定序号无效则返回null
    {
        return this.vertexlist.get(i).data;
    }
    public int getWeight(int i, int j)                     //返回<vi,vj>边的权值
    {
        int n=this.vertexCount();
        if (i>=0 && i<n && j>=0 && j<n)
        {
            if (i==j)
                return 0;
            Node<Edge> p=this.vertexlist.get(i).adjlink.head.next; //获得第i条边单链表的第一个结点
            while (p!=null)                                //寻找下一个邻接顶点
            {
                if (p.data.dest==j)
                    return p.data.weight;                  //返回<vi,vj>边的权值
                p = p.next;
            }
            return MAX_WEIGHT;
        }
        throw new IndexOutOfBoundsException("i="+i+", j="+j);//抛出序号越界异常
    }

    public String toString()                          //返回图的顶点集合和邻接表描述字符串
    {
        return "出边表：\n"+this.vertexlist.toString()+"\n ";
    }

    //插入元素值为x的顶点，返回该顶点在顶点顺序表中的序号
    public int insertVertex(T x)
    {
        this.vertexlist.append(new Vertex<T>(x));     //顺序表追加元素，自动扩充容量
        return this.vertexlist.length()-1;
    }

    //插入一条权值为weight的边〈vi,vj〉，若该边已存在，则不插入。
    //算法将指定边结点根据其dest域值插入到第i条边单链表的合适位置。
    public void insertEdge(int i, int j, int weight)
    {
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j)
        {
            Edge edge=new Edge(i,j,weight);
            SortedSinglyLinkedList<Edge> adjlink=this.vertexlist.get(i).adjlink;//获得第i条边单链表
            Node<Edge> front=adjlink.head, p=front.next;
            while (p!=null && p.data.compareTo(edge)<0)    //寻找插入位置
            {
                front = p;
                p = p.next;
            }
            if (p!=null && p.data.compareTo(edge)==0)      //若该边已存在，则不插入
                return;
            front.next = new Node<Edge>(edge, p);          //将edge边结点插入到front结点之后
        }
    }
    public void insertEdge(Edge edge)                      //插入一条边
    {
        insertEdge(edge.start, edge.dest, edge.weight);
    }
    //【思考题】上述插入算法为什么不能直接调用执行以下排序单链表的插入元素语句？
    //this.vertexlist.get(i).adjlink.insert(edge);
    //【答】插入要求不同。此处要求不插入相同值，而排序单链表的插入方法会插入相同值。

    public void removeEdge(int i, int j)         //删除边〈vi,vj〉，i、j指定顶点序号
    {
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j)
            this.vertexlist.get(i).adjlink.remove(new Edge(i,j,1));//在第i条边单链表中删除指定值结点
        //调用排序单链表的删除操作，查找依据是Edge的compareTo(e)方法返回0
    }
    public void removeEdge(Edge edge)            //删除边
    {
        removeEdge(edge.start, edge.dest);
    }

    public void removeVertex(int i)                        //删除序号为vi的顶点及其关联的边
    {
        int n=vertexCount();                               //删除之前的顶点数
        if (i<0 || i>n)
            return;
        for (int j=0; j<n-1; j++)                          //未删除的边结点更改某些顶点序号
        {
            Node<Edge> front=this.vertexlist.get(j).adjlink.head;
            Node<Edge> p=front.next;                       //获得第j条边单链表的第一个结点
            while (p!=null)
            {
                Edge e=p.data;
                if (e.start==i || e.dest==i)
                {
                    front.next = p.next;                   //删除与vi关联的边
                    p=front.next;
                }
                else
                {
                    if (e.start>i)
                        e.start--;                         //顶点序号减一
                    if (e.dest>i)
                        e.dest--;
                    front = p;
                    p = p.next;
                }
            }
        }
        this.vertexlist.remove(i);                         //删除顶点vi，顶点数已减一
    }

    //7.3   图的遍历
    //返回vi在vj后的下一个邻接顶点的序号，当j=-1时，返回第一个邻接顶点的序号，若不存在下一个邻接顶点，则返回-1
    public int getNextNeighbor(int i, int j)
    {
        int n=this.vertexCount();
        if (i>=0 && i<n && j>=-1 && j<n && i!=j)
            for (Node<Edge> p=this.vertexlist.get(i).adjlink.head.next;  p!=null;  p=p.next)
                //在第i条边单链表中寻找下一个邻接顶点
                if (p.data.dest>j)                         //j=-1时，返回第一个邻接顶点的序号
                    return p.data.dest;                    //返回下一个邻接顶点的序号
        return -1;
    }
}

/*
    //基于单链表的迭代器
    //返回vi在vj后的下一个邻接顶点的序号，当j=-1时，返回第一个邻接顶点的序号，若不存在下一个邻接顶点，则返回-1
    public int getNextNeighbor(int i, int j)
    {
        int n=this.vertexCount();
        if (i>=0 && i<n && j>=-1 && j<n && i!=j)
        {
            SortedSinglyLinkedList<Edge> slink = this.vertexlist.get(i).adjlink;  //获得第i条边单链表
            java.util.Iterator<Edge> it = slink.iterator();//it是单链表的迭代器
            while (it.hasNext())                           //若有后继元素
            {
                Edge edge = it.next();                     //返回单链表的第一条边，相当于slink.get(0)
//                System.out.print(edge.toString()+" ");     //访问该顶点
                if (edge.dest>j)
                    return edge.dest;                      //返回下一个邻接顶点的序号
            }
        }
        return -1;
    }
}*/
