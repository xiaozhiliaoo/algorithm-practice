package chapter7.graph;

import chapter2.singlylinkedlist.SortedSinglyLinkedList;

/**
 * Created by lili on 2017/7/1.
 */
//7.2.2   图的邻接表表示和实现
//顶点表元素类

public class Vertex<T>                                     //顶点表元素
{
    public T data;                                         //顶点数据域
    public SortedSinglyLinkedList<Edge> adjlink;           //该顶点的边单链表

    public Vertex(T data)
    {
        this.data = data;
        this.adjlink = new SortedSinglyLinkedList<Edge>(); //构造顶点时创建空单链表
    }
    public String toString()                               //返回顶点及其边单链表的描述字符串
    {
        return "\n"+this.data.toString()+"："+this.adjlink.toString();
    }
}
/*不行，浅拷贝
 *     public Vertex(T data, SortedSinglyLinkedList<Edge> adjlink)
{
    this.data = data;
    this.adjlink = adjlink;       //引用赋值
}
    public Vertex(T data)
    {
        this(data, new SortedSinglyLinkedList<Edge>());   //构造结点时创建空单链表
    }
*/

